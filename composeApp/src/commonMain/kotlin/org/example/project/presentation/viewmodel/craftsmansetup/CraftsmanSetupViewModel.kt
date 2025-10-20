package org.example.project.presentation.viewmodel.craftsmansetup

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.project.util.AppLogger
import org.example.project.domain.usecase.GetCategoriesUseCase
import org.example.project.domain.usecase.craftsman.CreateCraftsmanProfileUseCase
import org.example.project.domain.usecase.craftsman.UploadIdCardsUseCase
import org.example.project.domain.usecase.craftsman.UploadWorkPortfolioUseCase
import org.example.project.presentation.model.ImageData
import org.example.project.presentation.model.PersonalInfoUiModel
import org.example.project.presentation.viewmodel.base.BaseViewModel
import org.example.project.presentation.viewmodel.base.ErrorUiState
import org.example.project.presentation.viewmodel.mapper.toDomain
import org.example.project.presentation.viewmodel.mapper.toUi
import org.example.project.presentation.viewmodel.mapper.toWorkImages

class CraftsmanSetupViewModel(
    private val createCraftsmanUseCase: CreateCraftsmanProfileUseCase,
    private val uploadIdCardsUseCase: UploadIdCardsUseCase,
    private val uploadWorkPortfolioUseCase: UploadWorkPortfolioUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : BaseViewModel<CraftsmanSetupUiState, CraftsmanRegistrationEffect>(
    CraftsmanSetupUiState()
), CraftsmanSetupInteractionListener {
    override fun onUserTypeSelected(userType: UserType) {
        when (userType) {
            UserType.CRAFTSMAN -> {
                updateState {
                    it.copy(
                        userType = userType,
                        canNavigateNext = true
                    )
                }
            }
            UserType.CUSTOMER -> {
                TODO("Implement Customer setup flow – redirect to CustomerSetupScreen when ready")
            }
        }
    }

    init {
        validateCurrentPage()
        fetchCategories()

        viewModelScope.launch {
            isLoading.collect { loading ->
                updateState { it.copy(isLoading = loading) }
            }
        }
    }

    override fun onCategoryToggled(categoryId: Int) {
        updateState { state ->
            val newSelection = if (categoryId in state.selectedCategoryIds) {
                state.selectedCategoryIds - categoryId
            } else {
                state.selectedCategoryIds + categoryId
            }
            state.copy(
                selectedCategoryIds = newSelection,
                canNavigateNext = newSelection.isNotEmpty()
            )
        }
    }

    override fun onPersonalInfoChanged(personalInfo: PersonalInfoUiModel) {
        updateState {
            it.copy(
                personalInfo = personalInfo,
                canNavigateNext = validatePersonalInfo(personalInfo)
            )
        }
    }

    override fun onIdCardSelected(
        isFront: Boolean,
        imageData: ImageData
    ) {
        updateState { state ->
            if (isFront) {
                state.copy(
                    idCardFront = imageData,
                    canNavigateNext = state.idCardBack != null
                )
            } else {
                state.copy(
                    idCardBack = imageData,
                    canNavigateNext = state.idCardFront != null
                )
            }
        }
    }

    override fun onUploadIdCards() {
        val craftsmanId = state.value.craftsmanId ?: return
        val frontCard = state.value.idCardFront ?: return
        val backCard = state.value.idCardBack ?: return

        updateState { it.copy(isSwipeEnabled = false) }

        tryToCall(
            call = {
                uploadIdCardsUseCase(
                    craftsmanId = craftsmanId,
                    idCardFront = frontCard.byteArray,
                    idCardFrontFileName = frontCard.fileName,
                    idCardBack = backCard.byteArray,
                    idCardBackFileName = backCard.fileName
                )
            },
            onSuccess = { verificationDocs ->
                updateState {
                    it.copy(isSwipeEnabled = true)
                }
                sendNewEffect(CraftsmanRegistrationEffect.RegistrationComplete)
            },
            onError = { error ->
                updateState {
                    it.copy(
                        error = error,
                        isSwipeEnabled = true
                    )
                }
            },
            showLoading = true
        )
    }

    override fun onSkipIdentityVerification() {
        sendNewEffect(CraftsmanRegistrationEffect.RegistrationComplete)
    }

    override fun onPortfolioImagesAdded(images: List<ImageData>) {
        updateState { state ->
            val currentImages = state.portfolioImages
            val totalImages = currentImages + images
            val limitedImages = totalImages.take(4)

            state.copy(
                portfolioImages = limitedImages,
                canAddMoreImages = limitedImages.size < 4,
                canNavigateNext = limitedImages.isNotEmpty()
            )
        }
    }

    override fun onPortfolioImageRemoved(index: Int) {
        updateState { state ->
            val newImages = state.portfolioImages.toMutableList().apply { removeAt(index) }
            state.copy(
                portfolioImages = newImages,
                canAddMoreImages = true,
                canNavigateNext = newImages.isNotEmpty()
            )
        }
    }

    override fun onWorkDescriptionChanged(description: String) {
        updateState { state ->
            state.copy(workDescription = description)
        }
    }

    override fun onUploadPortfolio() {
        val craftsmanId = state.value.craftsmanId
        if (craftsmanId == null) {
            updateState { it.copy(error = ErrorUiState("Profile not created yet")) }
            return
        }

        updateState { it.copy(isSwipeEnabled = false) }

        tryToCall(
            call = {
                uploadWorkPortfolioUseCase(
                    craftsmanId = craftsmanId,
                    workImages = state.value.portfolioImages.toWorkImages()
                )
            },
            onSuccess = { uploadedUrls ->
                updateState {
                    it.copy(
                        isSwipeEnabled = true,
                        // Store uploaded URLs if needed
                    )
                }
                // Navigate to ID verification
                navigateNext()
            },
            onError = { error ->
                updateState {
                    it.copy(
                        error = error,
                        isSwipeEnabled = true
                    )
                }
            },
            showLoading = true
        )
    }

    fun clearError() {
        updateState { it.copy(error = null) }
    }

    fun navigateNext() {
        val currentIndex = state.value.currentPageIndex
        when (state.value.currentStep) {
            RegistrationStep.PERSONAL_INFO -> {
                if (!state.value.isProfileCreated) {
                    AppLogger.d("CraftsmanSetupViewModel", "Creating profile")
                    createCraftsmanProfile()
                    return
                }
            }
            RegistrationStep.PORTFOLIO_UPLOAD -> {
                if (state.value.portfolioImages.isNotEmpty()) {
                    onUploadPortfolio()
                    return // Don't navigate yet, wait for success
                }
            }
            else -> {
                // Normal navigation for other steps
            }
        }
        if (currentIndex < state.value.totalPages - 1 && state.value.canNavigateNext) {
            updateState { it.copy(currentPageIndex = currentIndex + 1) }
        }
    }

    fun navigateBack() {
        val currentIndex = state.value.currentPageIndex
        if (currentIndex > 0) {
            // Prevent going back after profile creation
            val targetIndex = if (state.value.isProfileCreated && currentIndex == 3) {
                currentIndex // Stay on current page
            } else {
                currentIndex - 1
            }
            updateState { it.copy(currentPageIndex = targetIndex) }
        }
    }

    fun onPageChanged(pageIndex: Int) {
        updateState {
            it.copy(
                currentPageIndex = pageIndex,
                canNavigateBack = pageIndex > 0,
            )
        }
        validateCurrentPage()
    }

    private fun fetchCategories() {
        tryToCall(
            call = { getCategoriesUseCase() },
            onSuccess = { categories ->
                val categoryUiList = categories.map { it.toUi() }
                updateState { it.copy(availableCategories = categoryUiList) }
            },
            onError = { error ->
                updateState { it.copy(error = error) }
            },
            showLoading = true
        )
    }

    private fun validateCurrentPage() {
        val canProceed = when (state.value.currentStep) {
            RegistrationStep.USER_TYPE -> state.value.userType != null
            RegistrationStep.SERVICE_SELECTION -> state.value.selectedCategoryIds.isNotEmpty()
            RegistrationStep.PERSONAL_INFO -> validatePersonalInfo(state.value.personalInfo)
            RegistrationStep.PORTFOLIO_UPLOAD -> state.value.portfolioImages.isNotEmpty()
            RegistrationStep.IDENTITY_VERIFICATION -> true // Optional step
        }

        updateState { it.copy(canNavigateNext = canProceed) }
    }

    private fun createCraftsmanProfile() {
        val selectedCategoryTitles = state.value.availableCategories
            .filter { it.id in state.value.selectedCategoryIds }
            .map { it.title }
        updateState { it.copy(isSwipeEnabled = false) }

        tryToCall(
            call = {
                AppLogger.d("CraftsmanSetupViewModel", "call createCraftsmanUseCase")
                createCraftsmanUseCase(
                    personalInfo = state.value.personalInfo.toDomain(),
                    categories = selectedCategoryTitles
                )
            },
            onSuccess = { craftsmanId ->
                AppLogger.d("CraftsmanSetupViewModel", "call onSuccess")
                updateState {
                    it.copy(
                        craftsmanId = craftsmanId,
                        isProfileCreated = true,
                        isSwipeEnabled = true
                    )
                }
                // Auto navigate to portfolio after successful creation
                navigateNext()
            },
            onError = { error ->
                AppLogger.d("CraftsmanSetupViewModel", error.message)
                updateState {
                    it.copy(
                        error = error,
                        isSwipeEnabled = true
                    )
                }
            },
            showLoading = true
        )
    }
}


private fun validatePersonalInfo(info: PersonalInfoUiModel): Boolean {
    return info.firstName.length>=3 &&
            info.lastName.length>=3 &&
            info.phoneNumber.length>=10 &&
            info.phoneNumber.matches(Regex("^\\+?[1-9]\\d{1,14}$")) &&
            info.address.isNotBlank()
}

