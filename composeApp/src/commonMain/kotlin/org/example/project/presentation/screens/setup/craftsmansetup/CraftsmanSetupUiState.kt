package org.example.project.presentation.screens.setup.craftsmansetup

import org.example.project.domain.entity.VerificationDocuments
import org.example.project.presentation.model.CategoryUi
import org.example.project.presentation.model.ImageData
import org.example.project.presentation.model.PersonalInfoUiModel
import org.example.project.presentation.shared.base.BaseScreenState
import org.example.project.presentation.shared.base.ErrorUiState

data class CraftsmanSetupUiState(
    override val isLoading: Boolean = false,
    override val error: ErrorUiState? = null,

    val currentPageIndex: Int = 0,
    val totalPages: Int = 4,
    val canNavigateNext: Boolean = false,
    val canNavigateBack: Boolean = false,
    val isSwipeEnabled: Boolean = true,

    val isUploadingProfile: Boolean = false,
    val isUploadingPortfolio: Boolean = false,
    val isUploadingIdCards: Boolean = false,
    val uploadedPortfolioUrls: List<String> = emptyList(),
    val verificationDocuments: VerificationDocuments? = null,

    val availableCategories: List<CategoryUi> = emptyList(),
    val selectedCategoryIds: Set<Int> = emptySet(),

    val personalInfo: PersonalInfoUiModel = PersonalInfoUiModel("", "", "", ""),

    val portfolioImages: List<ImageData> = emptyList(),
    val workDescription: String = "",
    val canAddMoreImages: Boolean = true,

    val idCardFront: ImageData? = null,
    val idCardBack: ImageData? = null,

    val profilePicture: ImageData? = null,
    val isUploadingProfilePicture: Boolean = false,
    val profilePictureUrl: String? = null,

    val craftsmanId: String? = null,
    val isProfileCreated: Boolean = false,
): BaseScreenState {

    val currentStep: RegistrationStep
        get() = RegistrationStep.fromIndex(currentPageIndex)

    val hasUploadedIdCards: Boolean
        get() = idCardFront != null && idCardBack != null

    val nextButtonText: String
        get() = when (currentStep) {
            RegistrationStep.SERVICE_SELECTION -> "Next"
            RegistrationStep.PERSONAL_INFO -> if (isLoading) "Creating Profile..." else "Next"
            RegistrationStep.PORTFOLIO_UPLOAD -> "Next"
            RegistrationStep.IDENTITY_VERIFICATION -> "See Nearby Requests"
        }
}

enum class RegistrationStep(val index: Int) {
    SERVICE_SELECTION(0),
    PERSONAL_INFO(1),
    PORTFOLIO_UPLOAD(2),
    IDENTITY_VERIFICATION(3);

    companion object {
        fun fromIndex(index: Int): RegistrationStep =
            entries.firstOrNull { it.index == index } ?: SERVICE_SELECTION
    }
}