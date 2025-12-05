package org.example.project.presentation.screens.setup.craftsmansetup

import org.example.project.presentation.model.ImageData
import org.example.project.presentation.model.CraftsmanPersonalInfoUiModel
import org.example.project.presentation.shared.base.ErrorUiState

interface CraftsmanSetupInteractionListener {
    fun onCategoryToggled(categoryId: Int)
    fun onPersonalInfoChanged(personalInfo: CraftsmanPersonalInfoUiModel)

    fun onIdCardSelected(isFront: Boolean, imageData: ImageData)
    fun onUploadIdCards()
    fun onSkipIdentityVerification()

    fun onPortfolioImagesAdded(images: List<ImageData>)
    fun onPortfolioImageRemoved(index: Int)
    fun onProfilePictureRemoved()
    fun onFrontIdCardRemoved()
    fun onBackIdCardRemoved()
    fun onWorkDescriptionChanged(description: String)
    fun onUploadPortfolio()

    fun onProfilePictureSelected(imageData: ImageData)
    fun onImagePickerError(error: ErrorUiState)

}