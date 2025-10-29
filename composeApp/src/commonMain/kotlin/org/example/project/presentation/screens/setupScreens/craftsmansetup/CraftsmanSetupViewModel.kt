package org.example.project.presentation.screens.setupscreens.craftsmansetup

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.project.domain.usecase.GetCategoriesUseCase
import org.example.project.domain.usecase.craftsman.CreateCraftsmanProfileUseCase
import org.example.project.domain.usecase.craftsman.UploadIdCardsUseCase
import org.example.project.domain.usecase.craftsman.UploadProfilePictureUseCase
import org.example.project.domain.usecase.craftsman.UploadWorkPortfolioUseCase
import org.example.project.domain.util.AppConstants.FileUpload.MAX_PORTFOLIO_IMAGES
import org.example.project.presentation.model.ImageData
import org.example.project.presentation.model.PersonalInfoUiModel
import org.example.project.presentation.shared.base.BaseViewModel
import org.example.project.presentation.shared.base.ErrorUiState
import org.example.project.presentation.mapper.toDomain
import org.example.project.presentation.mapper.toUi
import org.example.project.presentation.mapper.toWorkImages
import org.example.project.util.AppLogger

class CraftsmanSetupViewModel(
    private val createCraftsmanUseCase: CreateCraftsmanProfileUseCase,
    private val uploadIdCardsUseCase: UploadIdCardsUseCase,
    private val uploadWorkPortfolioUseCase: UploadWorkPortfolioUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val uploadProfilePictureUseCase: UploadProfilePictureUseCase,
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

    override fun onIdCardSelected(isFront: Boolean, imageData: ImageData) {
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
        val craftsmanId = state.value.craftsmanId
        val frontCard = state.value.idCardFront
        val backCard = state.value.idCardBack

        if (craftsmanId == null) {
            updateState { it.copy(error = ErrorUiState("Profile not created yet")) }
            return
        }

        if (frontCard == null || backCard == null) {
            updateState { it.copy(error = ErrorUiState("Please upload both ID card images")) }
            return
        }
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
                updateState { it.copy(isSwipeEnabled = true) }
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
        //sendNewEffect(CraftsmanRegistrationEffect.RegistrationComplete)
        navigateNext()
    }

    override fun onPortfolioImagesAdded(images: List<ImageData>) {
        updateState { state ->
            val currentImages = state.portfolioImages
            val totalImages = currentImages + images
            val limitedImages = totalImages.take(MAX_PORTFOLIO_IMAGES)

            limitedImages.forEachIndexed { index, img ->
                AppLogger.d("Portfolio", "Image $index: ${img.fileName}, ${img.byteArray.size} bytes")
            }

            state.copy(
                portfolioImages = limitedImages,
                canAddMoreImages = limitedImages.size < MAX_PORTFOLIO_IMAGES,
                canNavigateNext = limitedImages.isNotEmpty()
            )
        }
    }

    override fun onPortfolioImageRemoved(index: Int) {
        updateState { state ->
            val newImages = state.portfolioImages.toMutableList().apply {
                if (index < this.size) {
                    removeAt(index)
                }
            }
            val newImagesUrl= state.uploadedPortfolioUrls.toMutableList().apply {
                if (index < this.size) {
                    removeAt(index)
                }
            }
            state.copy(
                uploadedPortfolioUrls = newImagesUrl,
                portfolioImages = newImages,
                canAddMoreImages = true,
                canNavigateNext = newImages.isNotEmpty()
            )
        }
    }

    override fun onProfilePictureRemoved() {
        updateState { state ->
            state.copy(
                profilePicture = null,
                profilePictureUrl = null
            )
        }
    }

    override fun onFrontIdCardRemoved() {
        updateState { state ->
            state.copy(
                idCardFront = null,
                canNavigateNext = false
            )
        }
    }

    override fun onBackIdCardRemoved() {
        updateState { state ->
            state.copy(
                idCardBack = null,
                canNavigateNext = false
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

        val portfolioImages = state.value.portfolioImages
        if (portfolioImages.isEmpty()) {
            return
        }

        if (state.value.uploadedPortfolioUrls.isNotEmpty()) {
            updateState { it.copy(currentPageIndex = it.currentPageIndex + 1) }
            return
        }

        updateState { it.copy(isSwipeEnabled = false, isUploadingPortfolio = true) }

        tryToCall(
            call = {
                val workImages = portfolioImages.toWorkImages()
                uploadWorkPortfolioUseCase(
                    craftsmanId = craftsmanId,
                    workImages = workImages
                )
            },
            onSuccess = { uploadedUrls ->
                updateState {
                    it.copy(
                        isSwipeEnabled = true,
                        isUploadingPortfolio = false,
                        uploadedPortfolioUrls = uploadedUrls,
                        currentPageIndex = it.currentPageIndex + 1
                    )
                }
            },
            onError = { error ->
                updateState {
                    it.copy(
                        error = error,
                        isSwipeEnabled = true,
                        isUploadingPortfolio = false
                    )
                }
            },
            showLoading = true
        )
    }

    override fun onProfilePictureSelected(imageData: ImageData) {
        updateState { state ->
            state.copy(profilePicture = imageData)
        }
    }

    override fun onImagePickerError(error: ErrorUiState) {
        updateState { it.copy(error = error) }
    }

    private fun uploadProfilePicture(craftsmanId: String, profilePicture: ImageData) {
        updateState { it.copy(isUploadingProfilePicture = true) }

        tryToCall(
            call = {
                uploadProfilePictureUseCase(
                    craftsmanId = craftsmanId,
                    profilePicture = profilePicture.byteArray,
                    profilePictureFileName = profilePicture.fileName
                )
            },
            onSuccess = { profilePictureUrl ->
                updateState {
                    it.copy(
                        profilePictureUrl = profilePictureUrl,
                        isUploadingProfilePicture = false
                    )
                }
                updateState {
                    it.copy(
                        isSwipeEnabled = true,
                        currentPageIndex = it.currentPageIndex + 1
                    )
                }
            },
            onError = { error ->
                updateState {
                    it.copy(
                        error = error,
                        isUploadingProfilePicture = false
                    )
                }
                updateState {
                    it.copy(
                        isSwipeEnabled = true,
                        currentPageIndex = it.currentPageIndex + 1
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
                    createCraftsmanProfile()
                    return // createCraftsmanProfile will navigate on success
                }
            }
            RegistrationStep.PORTFOLIO_UPLOAD -> {
                val hasImages = state.value.portfolioImages.isNotEmpty()
                val alreadyUploaded = state.value.uploadedPortfolioUrls.isNotEmpty()
                val isCurrentlyUploading = state.value.isUploadingPortfolio

                if (isCurrentlyUploading) {
                    return
                }

                if (hasImages && !alreadyUploaded) {
                    onUploadPortfolio()
                    return // onUploadPortfolio will navigate on success
                }

            }
            else -> {
                // Normal navigation for other steps
            }
        }

        if (currentIndex < state.value.totalPages - 1 && state.value.canNavigateNext) {
            AppLogger.d("Navigation", "Navigating from page $currentIndex to ${currentIndex + 1}")
            updateState { it.copy(currentPageIndex = currentIndex + 1) }
        }
    }

    fun navigateBack() {
        val currentIndex = state.value.currentPageIndex
        if (currentIndex > 0) {
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
                createCraftsmanUseCase(
                    personalInfo = state.value.personalInfo.toDomain(),
                    categories = selectedCategoryTitles
                )
            },
            onSuccess = { craftsmanId ->
                updateState {
                    it.copy(
                        craftsmanId = craftsmanId,
                        isProfileCreated = true
                    )
                }

                val profilePicture = state.value.profilePicture
                if (profilePicture != null) {
                    uploadProfilePicture(craftsmanId, profilePicture)
                } else {
                    updateState {
                        it.copy(
                            isSwipeEnabled = true,
                            currentPageIndex = it.currentPageIndex + 1
                        )
                    }
                }
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

    private fun validatePersonalInfo(info: PersonalInfoUiModel): Boolean {
        return info.firstName.length >= 3 &&
                info.lastName.length >= 3 &&
                info.phoneNumber.length >= 10 &&
                info.phoneNumber.matches(Regex("^\\+?[1-9]\\d{1,14}$")) &&
                info.address.isNotBlank()
    }
}