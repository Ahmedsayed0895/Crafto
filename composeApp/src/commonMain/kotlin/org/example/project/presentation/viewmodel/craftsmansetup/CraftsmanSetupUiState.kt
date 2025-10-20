package org.example.project.presentation.viewmodel.craftsmansetup

import org.example.project.domain.entity.VerificationDocuments
import org.example.project.presentation.model.CategoryUi
import org.example.project.presentation.model.ImageData
import org.example.project.presentation.model.PersonalInfoUiModel
import org.example.project.presentation.viewmodel.base.BaseScreenState
import org.example.project.presentation.viewmodel.base.ErrorUiState

data class CraftsmanSetupUiState(
    override val isLoading: Boolean = false,
    override val error: ErrorUiState? = null,

    // Pager state
    val currentPageIndex: Int = 0,
    val totalPages: Int = 5,
    val canNavigateNext: Boolean = false,
    val canNavigateBack: Boolean = false,
    val isSwipeEnabled: Boolean = true,

    val isUploadingProfile: Boolean = false,
    val isUploadingPortfolio: Boolean = false,
    val isUploadingIdCards: Boolean = false,
    val uploadedPortfolioUrls: List<String> = emptyList(),
    val verificationDocuments: VerificationDocuments? = null,

    // User type selection
    val userType: UserType? = null,

    // Service selection
    val availableCategories: List<CategoryUi> = emptyList(),
    val selectedCategoryIds: Set<Int> = emptySet(),

    // Personal info
    val personalInfo: PersonalInfoUiModel = PersonalInfoUiModel("", "", "", ""),

    // Portfolio
    val portfolioImages: List<ImageData> = emptyList(),
    val workDescription: String = "",
    val canAddMoreImages: Boolean = true,

    // Identity verification
    val idCardFront: ImageData? = null,
    val idCardBack: ImageData? = null,

    // Flow state
    val craftsmanId: String? = null,
    val isProfileCreated: Boolean = false,
): BaseScreenState {

    val currentStep: RegistrationStep
        get() = RegistrationStep.fromIndex(currentPageIndex)

    val progress: Float
        get() = (currentPageIndex + 1) / totalPages.toFloat()

    val nextButtonText: String
        get() = when (currentStep) {
            RegistrationStep.USER_TYPE -> "Next"
            RegistrationStep.SERVICE_SELECTION -> "Next"
            RegistrationStep.PERSONAL_INFO -> if (isLoading) "Creating Profile..." else "Next"
            RegistrationStep.PORTFOLIO_UPLOAD -> "Next"
            RegistrationStep.IDENTITY_VERIFICATION -> "See Nearby Requests"
        }
}

enum class RegistrationStep(val index: Int) {
    USER_TYPE(0),              // Choose Customer/Craftsman
    SERVICE_SELECTION(1),      // What services do you offer
    PERSONAL_INFO(2),          // Collect personal information
    PORTFOLIO_UPLOAD(3),       // Show your work
    IDENTITY_VERIFICATION(4);  // Upload ID (Optional)

    companion object {
        fun fromIndex(index: Int): RegistrationStep =
            entries.firstOrNull { it.index == index } ?: USER_TYPE
    }
}

enum class UserType {
    CUSTOMER,
    CRAFTSMAN
}