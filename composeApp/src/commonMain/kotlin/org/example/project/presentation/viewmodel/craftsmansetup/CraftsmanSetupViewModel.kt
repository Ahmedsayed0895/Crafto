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

    override fun onIdCardSelected(isFront: Boolean, imageData: ImageData) {
        AppLogger.d("IDCard", " ID Card selected")
        AppLogger.d("IDCard", "   Front: $isFront")
        AppLogger.d("IDCard", "   FileName: ${imageData.fileName}")
        AppLogger.d("IDCard", "   URI: ${imageData.uri}")
        AppLogger.d("IDCard", "   Size: ${imageData.byteArray.size} bytes")

        // Extract and validate extension
        val extension = imageData.fileName.substringAfterLast('.', "").lowercase()
        AppLogger.d("IDCard", "   Extension: '$extension'")

        if (extension.isEmpty()) {
            AppLogger.e("IDCard", " No extension found in filename!")
        }

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

        AppLogger.d("IDCard", " onUploadIdCards() called")
        AppLogger.d("IDCard", "   CraftsmanId: $craftsmanId")

        if (craftsmanId == null) {
            AppLogger.e("IDCard", " CraftsmanId is null!")
            updateState { it.copy(error = ErrorUiState("Profile not created yet")) }
            return
        }

        if (frontCard == null || backCard == null) {
            AppLogger.e("IDCard", " Missing ID cards!")
            updateState { it.copy(error = ErrorUiState("Please upload both ID card images")) }
            return
        }

        AppLogger.d("IDCard", "   Front card:")
        AppLogger.d("IDCard", "     - FileName: ${frontCard.fileName}")
        AppLogger.d("IDCard", "     - Size: ${frontCard.byteArray.size} bytes")
        AppLogger.d("IDCard", "     - Extension: ${frontCard.fileName.substringAfterLast('.', "")}")

        AppLogger.d("IDCard", "   Back card:")
        AppLogger.d("IDCard", "     - FileName: ${backCard.fileName}")
        AppLogger.d("IDCard", "     - Size: ${backCard.byteArray.size} bytes")
        AppLogger.d("IDCard", "     - Extension: ${backCard.fileName.substringAfterLast('.', "")}")

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
                AppLogger.d("IDCard", " ID Cards uploaded successfully!")
                updateState { it.copy(isSwipeEnabled = true) }
                sendNewEffect(CraftsmanRegistrationEffect.RegistrationComplete)
            },
            onError = { error ->
                AppLogger.e("IDCard", " Upload failed: ${error.message}")
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

            AppLogger.d("Portfolio", "Added ${images.size} images. Total: ${limitedImages.size}")

            // Log each image details
            limitedImages.forEachIndexed { index, img ->
                AppLogger.d("Portfolio", "Image $index: ${img.fileName}, ${img.byteArray.size} bytes")
            }

            state.copy(
                portfolioImages = limitedImages,
                canAddMoreImages = limitedImages.size < 4,
                canNavigateNext = limitedImages.isNotEmpty()
            )
        }
    }

    override fun onPortfolioImageRemoved(index: Int) {
        updateState { state ->
            val newImages = state.portfolioImages.toMutableList().apply {
                removeAt(index)
            }
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
            AppLogger.e("Portfolio", "CraftsmanId is null!")
            updateState { it.copy(error = ErrorUiState("Profile not created yet")) }
            return
        }

        val portfolioImages = state.value.portfolioImages
        if (portfolioImages.isEmpty()) {
            AppLogger.d("Portfolio", "No images to upload, skipping to next page")
            updateState { it.copy(currentPageIndex = it.currentPageIndex + 1) }
            return
        }

        // Check if already uploaded
        if (state.value.uploadedPortfolioUrls.isNotEmpty()) {
            AppLogger.d("Portfolio", "Portfolio already uploaded, skipping")
            updateState { it.copy(currentPageIndex = it.currentPageIndex + 1) }
            return
        }

        AppLogger.d("Portfolio", "Starting upload of ${portfolioImages.size} images for craftsman $craftsmanId")

        portfolioImages.forEachIndexed { index, image ->
            AppLogger.d("Portfolio", "Image $index: fileName=${image.fileName}, size=${image.byteArray.size} bytes")
        }

        updateState { it.copy(isSwipeEnabled = false, isUploadingPortfolio = true) }

        tryToCall(
            call = {
                val workImages = portfolioImages.toWorkImages()
                AppLogger.d("Portfolio", "Converted to ${workImages.size} WorkImage objects - calling API")
                uploadWorkPortfolioUseCase(
                    craftsmanId = craftsmanId,
                    workImages = workImages
                )
            },
            onSuccess = { uploadedUrls ->
                AppLogger.d("Portfolio", " SUCCESS! Received ${uploadedUrls.size} URLs")
                uploadedUrls.forEach { url ->
                    AppLogger.d("Portfolio", "   - $url")
                }

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
                AppLogger.e("Portfolio", " FAILED: ${error.message}")
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

    fun clearError() {
        updateState { it.copy(error = null) }
    }

    fun navigateNext() {
        val currentIndex = state.value.currentPageIndex

        when (state.value.currentStep) {
            RegistrationStep.PERSONAL_INFO -> {
                if (!state.value.isProfileCreated) {
                    AppLogger.d("Navigation", "Creating profile before proceeding")
                    createCraftsmanProfile()
                    return // createCraftsmanProfile will navigate on success
                }
            }
            RegistrationStep.PORTFOLIO_UPLOAD -> {
                val hasImages = state.value.portfolioImages.isNotEmpty()
                val alreadyUploaded = state.value.uploadedPortfolioUrls.isNotEmpty()
                val isCurrentlyUploading = state.value.isUploadingPortfolio

                if (isCurrentlyUploading) {
                    AppLogger.d("Navigation", "Upload already in progress, ignoring navigation")
                    return
                }

                if (hasImages && !alreadyUploaded) {
                    AppLogger.d("Navigation", "Portfolio needs to be uploaded")
                    onUploadPortfolio()
                    return // onUploadPortfolio will navigate on success
                }

                AppLogger.d("Navigation", "Portfolio already uploaded or no images, proceeding")
            }
            else -> {
                // Normal navigation for other steps
            }
        }

        // Normal navigation
        if (currentIndex < state.value.totalPages - 1 && state.value.canNavigateNext) {
            AppLogger.d("Navigation", "Navigating from page $currentIndex to ${currentIndex + 1}")
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
                AppLogger.d("CraftsmanSetupViewModel", "Calling createCraftsmanUseCase")
                createCraftsmanUseCase(
                    personalInfo = state.value.personalInfo.toDomain(),
                    categories = selectedCategoryTitles
                )
            },
            onSuccess = { craftsmanId ->
                AppLogger.d("CraftsmanSetupViewModel", "Profile created successfully: $craftsmanId")
                updateState {
                    it.copy(
                        craftsmanId = craftsmanId,
                        isProfileCreated = true,
                        isSwipeEnabled = true,
                        // Navigate to portfolio page
                        currentPageIndex = it.currentPageIndex + 1
                    )
                }
            },
            onError = { error ->
                AppLogger.e("CraftsmanSetupViewModel", "Profile creation failed: ${error.message}")
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

