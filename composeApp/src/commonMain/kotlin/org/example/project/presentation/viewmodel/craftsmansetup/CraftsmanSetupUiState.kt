package org.example.project.presentation.viewmodel.craftsmansetup

import org.example.project.presentation.model.ImageData
import org.example.project.presentation.model.PersonalInfoUiModel
import org.example.project.presentation.viewmodel.base.BaseScreenState
import org.example.project.presentation.viewmodel.base.ErrorUiState

data class CraftsmanSetupUiState(
    override val isLoading: Boolean = false,
    override val error: ErrorUiState? = null,
    val currentStep: RegistrationStep = RegistrationStep.USER_TYPE,
    val userType: UserType = UserType.CRAFTSMAN,
    val selectedServices: Set<String> = emptySet(),
    val personalInfo: PersonalInfoUiModel,
    val idCardFront: ImageData? = null,
    val idCardBack: ImageData? = null,
    val portfolioImages: List<ImageData> = emptyList(),
    val workDescription: String = "",

    ): BaseScreenState

enum class RegistrationStep {
    USER_TYPE,           // Choose Customer/Craftsman
    SERVICE_SELECTION,   // What services do you offer
    IDENTITY_VERIFICATION,// Upload ID (Optional)
    PORTFOLIO_UPLOAD,    // Show your work
}

enum class UserType {
    CUSTOMER,
    CRAFTSMAN
}
