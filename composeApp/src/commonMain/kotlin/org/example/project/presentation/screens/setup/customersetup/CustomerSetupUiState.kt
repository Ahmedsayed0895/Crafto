package org.example.project.presentation.screens.setup.customersetup

import org.example.project.presentation.model.CategoryUi
import org.example.project.presentation.model.CustomerPersonalInfoUiModel
import org.example.project.presentation.model.DistrictUiModel
import org.example.project.presentation.model.GovernoratesUiModel
import org.example.project.presentation.model.ImageData
import org.example.project.presentation.model.LocationUiModel
import org.example.project.presentation.shared.base.BaseScreenState
import org.example.project.presentation.shared.base.ErrorUiState

data class CustomerSetupUiState(
    override val isLoading: Boolean = false,
    override val error: ErrorUiState? = null,

    val currentPageIndex: Int = 0,
    val totalPages: Int = 5,
    val canNavigateNext: Boolean = false,
    val canNavigateBack: Boolean = false,
    val isSwipeEnabled: Boolean = true,

    val personalInfo: CustomerPersonalInfoUiModel = CustomerPersonalInfoUiModel(
        name = "",
        phoneNumber = ""
    ),
    val profilePicture: ImageData? = null,
    val isUploadingProfilePicture: Boolean = false,
    val profilePictureUrl: String? = null,

    val availableCategories: List<CategoryUi> = emptyList(),
    val selectedCategoryIds: Set<Int> = emptySet(),

    val governorates: List<GovernoratesUiModel> = emptyList(),
    val selectedGovernorate: GovernoratesUiModel? = null,
    val showGovernorateSheet: Boolean = false,
    val isLoadingGovernorates: Boolean = false,

    val districts: List<DistrictUiModel> = emptyList(),
    val selectedDistrict: DistrictUiModel? = null,
    val showDistrictSheet: Boolean = false,
    val isLoadingDistricts: Boolean = false,

    val detailedLocation: String = "",

    val customerId: String? = null,
    val isProfileCreated: Boolean = false,
    val isCreatingProfile: Boolean = false

) : BaseScreenState {

    val currentStep: CustomerRegistrationStep
        get() = CustomerRegistrationStep.fromIndex(currentPageIndex)

    val progress: Float
        get() = (currentPageIndex + 1) / totalPages.toFloat()

    val nextButtonText: String
        get() = when (currentStep) {
            CustomerRegistrationStep.CATEGORY_SELECTION -> "Next"
            CustomerRegistrationStep.LOCATION_GOVERNORATE -> "Next"
            CustomerRegistrationStep.LOCATION_DISTRICT -> "Next"
            CustomerRegistrationStep.LOCATION_DETAILED -> "Next"
                CustomerRegistrationStep.PERSONAL_INFO -> {
                if (isCreatingProfile) "Creating Profile..." else "Complete Setup"
            }
        }

    val locationDisplayText: String
        get() {
            val parts = listOfNotNull(
                selectedGovernorate?.name,
                selectedDistrict?.name
            )
            return if (parts.isNotEmpty()) parts.joinToString(", ") else "Select Location"
        }

    val locationModel: LocationUiModel
        get() = LocationUiModel(
            governorate = selectedGovernorate?.name ?: "",
            district = selectedDistrict?.name ?: "",
            detailedLocation = detailedLocation
        )

    val isLocationComplete: Boolean
        get() = selectedGovernorate != null &&
                selectedDistrict != null &&
                detailedLocation.isNotBlank()
}

enum class CustomerRegistrationStep(val index: Int) {
    CATEGORY_SELECTION(0),
    LOCATION_GOVERNORATE(1),
    LOCATION_DISTRICT(2),
    LOCATION_DETAILED(3),
    PERSONAL_INFO(4);

    companion object {
        fun fromIndex(index: Int): CustomerRegistrationStep =
            entries.firstOrNull { it.index == index } ?: CATEGORY_SELECTION
    }
}