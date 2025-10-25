package org.example.project.presentation.screens.setupscreens.craftsmansetup

import org.example.project.presentation.model.ImageData
import org.example.project.presentation.model.PersonalInfoUiModel
import org.example.project.presentation.screens.shared.base.ErrorUiState

interface CraftsmanSetupInteractionListener {
    fun onUserTypeSelected(userType: UserType)

    fun onCategoryToggled(categoryId: Int)
    fun onPersonalInfoChanged(personalInfo: PersonalInfoUiModel)

    fun onIdCardSelected(isFront: Boolean, imageData: ImageData)
    fun onUploadIdCards()
    fun onSkipIdentityVerification()

    fun onPortfolioImagesAdded(images: List<ImageData>)
    fun onPortfolioImageRemoved(index: Int)
    fun onWorkDescriptionChanged(description: String)
    fun onUploadPortfolio()

    fun onProfilePictureSelected(imageData: ImageData)  // ADD THIS
    fun onImagePickerError(error: ErrorUiState)  // ADD THIS

}