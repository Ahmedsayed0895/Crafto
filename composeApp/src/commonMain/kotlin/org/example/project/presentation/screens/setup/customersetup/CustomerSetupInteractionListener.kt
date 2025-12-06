package org.example.project.presentation.screens.setup.customersetup

import org.example.project.presentation.model.CustomerPersonalInfoUiModel
import org.example.project.presentation.model.DistrictUiModel
import org.example.project.presentation.model.GovernoratesUiModel
import org.example.project.presentation.model.ImageData
import org.example.project.presentation.shared.base.ErrorUiState

interface CustomerSetupInteractionListener {
    fun onCategoryToggled(categoryId: Int)
    fun onPersonalInfoChanged(personalInfo: CustomerPersonalInfoUiModel)
    fun onProfilePictureSelected(imageData: ImageData)

    fun onProfilePictureRemoved()

    fun onGovernorateSheetOpen()
    fun onGovernorateSheetClose()
    fun onGovernorateSelected(governorate: GovernoratesUiModel)

    fun onDistrictSheetOpen()
    fun onDistrictSheetClose()
    fun onDistrictSelected(district: DistrictUiModel)

    fun onDetailedLocationChanged(location: String)

    fun onImagePickerError(error: ErrorUiState)
    fun clearError()
}