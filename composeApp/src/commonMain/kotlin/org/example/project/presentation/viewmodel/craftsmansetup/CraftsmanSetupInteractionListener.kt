package org.example.project.presentation.viewmodel.craftsmansetup

import org.example.project.presentation.model.ImageData
import org.example.project.presentation.model.PersonalInfoUiModel

interface CraftsmanSetupInteractionListener {
    fun onUserTypeSelected(userType: UserType)

    // Service Selection
    fun onServiceToggled(service: String)
    fun onServicesNextClicked(personalInfo: PersonalInfoUiModel)

    // Identity Verification
    fun onIdCardSelected(isFront: Boolean, imageData: ImageData)
    fun onUploadIdCards()
    fun onSkipIdentityVerification()

    // Portfolio
    fun onPortfolioImagesAdded(images: List<ImageData>)
    fun onPortfolioImageRemoved(index: Int)
    fun onWorkDescriptionChanged(description: String)
    fun onUploadPortfolio()

    // Common
    fun onBackPressed()
}