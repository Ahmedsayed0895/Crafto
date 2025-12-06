package org.example.project.presentation.screens.setup.customersetup

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.project.domain.usecase.GetCategoriesUseCase
import org.example.project.domain.usecase.customer.CreateCustomerProfileUseCase
import org.example.project.domain.usecase.customer.UploadCustomerProfilePictureUseCase
import org.example.project.domain.usecase.location.GetDistrictsByGovernorateUseCase
import org.example.project.domain.usecase.location.GetGovernoratesUseCase
import org.example.project.presentation.mapper.toDomain
import org.example.project.presentation.mapper.toUi
import org.example.project.presentation.model.CustomerPersonalInfoUiModel
import org.example.project.presentation.model.DistrictUiModel
import org.example.project.presentation.model.GovernoratesUiModel
import org.example.project.presentation.model.ImageData
import org.example.project.presentation.model.LocationUiModel
import org.example.project.presentation.shared.base.BaseViewModel
import org.example.project.presentation.shared.base.ErrorUiState
import org.example.project.util.AppLogger

class CustomerSetupViewModel(
    private val createCustomerUseCase: CreateCustomerProfileUseCase,
    private val uploadCustomerProfilePictureUseCase: UploadCustomerProfilePictureUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getGovernoratesUseCase: GetGovernoratesUseCase,
    private val getDistrictsByGovernorateUseCase: GetDistrictsByGovernorateUseCase
) : BaseViewModel<CustomerSetupUiState, CustomerRegistrationEffect>(
    CustomerSetupUiState()
), CustomerSetupInteractionListener {

    init {
        fetchCategories()
        fetchGovernorates()
        validateCurrentPage()

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

    override fun onPersonalInfoChanged(personalInfo: CustomerPersonalInfoUiModel) {
        updateState {
            it.copy(
                personalInfo = personalInfo,
                canNavigateNext = validatePersonalInfo(personalInfo)
            )
        }
    }

    override fun onProfilePictureSelected(imageData: ImageData) {
        updateState { it.copy(profilePicture = imageData) }
    }

    override fun onProfilePictureRemoved() {
        updateState {
            it.copy(
                profilePicture = null,
                profilePictureUrl = null
            )
        }
    }

    override fun onGovernorateSheetOpen() {
        updateState { it.copy(showGovernorateSheet = true) }
    }

    override fun onGovernorateSheetClose() {
        updateState { it.copy(showGovernorateSheet = false) }
    }

    override fun onGovernorateSelected(governorate: GovernoratesUiModel) {
        updateState {
            it.copy(
                selectedGovernorate = governorate,
                selectedDistrict = null, // Reset district when governorate changes
                districts = emptyList(),
                showGovernorateSheet = false,
                canNavigateNext = true
            )
        }
        fetchDistricts(governorate.id)
    }

    override fun onDistrictSheetOpen() {
        if (state.value.districts.isNotEmpty()) {
            updateState { it.copy(showDistrictSheet = true) }
        }
    }

    override fun onDistrictSheetClose() {
        updateState { it.copy(showDistrictSheet = false) }
    }

    override fun onDistrictSelected(district: DistrictUiModel) {
        updateState {
            it.copy(
                selectedDistrict = district,
                showDistrictSheet = false,
                canNavigateNext = true
            )
        }
    }

    override fun onDetailedLocationChanged(location: String) {
        updateState {
            it.copy(
                detailedLocation = location,
                canNavigateNext = location.isNotBlank()
            )
        }
    }

    override fun onImagePickerError(error: ErrorUiState) {
        updateState { it.copy(error = error) }
    }

    override fun clearError() {
        updateState { it.copy(error = null) }
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

    fun navigateNext() {
        val currentIndex = state.value.currentPageIndex

        when (state.value.currentStep) {
            CustomerRegistrationStep.PERSONAL_INFO -> {
                if (!state.value.isProfileCreated) {
                    createCustomerProfile()
                    return
                }
            }
            else -> {
                // Normal navigation
            }
        }

        if (currentIndex < state.value.totalPages - 1 && state.value.canNavigateNext) {
            AppLogger.d("Navigation", "Customer: Navigating from page $currentIndex to ${currentIndex + 1}")
            updateState { it.copy(currentPageIndex = currentIndex + 1) }
            validateCurrentPage()
        }
    }

    fun navigateBack() {
        val currentIndex = state.value.currentPageIndex
        if (currentIndex > 0) {
            updateState { it.copy(currentPageIndex = currentIndex - 1) }
            validateCurrentPage()
        }
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
            showLoading = false
        )
    }

    private fun fetchGovernorates() {
        updateState { it.copy(isLoadingGovernorates = true) }

        tryToCall(
            call = { getGovernoratesUseCase() },
            onSuccess = { governorates ->
                updateState { uiState ->
                    uiState.copy(
                        governorates = governorates.map { it.toUi() },
                        isLoadingGovernorates = false
                    )
                }
            },
            onError = { error ->
                updateState {
                    it.copy(
                        error = error,
                        isLoadingGovernorates = false
                    )
                }
            },
            showLoading = false
        )
    }

    private fun fetchDistricts(governorateId: String) {
        updateState { it.copy(isLoadingDistricts = true) }

        tryToCall(
            call = { getDistrictsByGovernorateUseCase(governorateId) },
            onSuccess = { districts ->
                updateState { uiState ->
                    uiState.copy(
                        districts = districts.map { it.toUi() },
                        isLoadingDistricts = false
                    )
                }
            },
            onError = { error ->
                updateState {
                    it.copy(
                        error = error,
                        isLoadingDistricts = false
                    )
                }
            },
            showLoading = false
        )
    }

    private fun createCustomerProfile() {
        val selectedCategoryTitles = state.value.availableCategories
            .filter { it.id in state.value.selectedCategoryIds }
            .map { it.title }

        val location = LocationUiModel(
            governorate = state.value.selectedGovernorate?.name ?: "",
            district = state.value.selectedDistrict?.name ?: "",
            detailedLocation = state.value.detailedLocation
        ).toDomain()

        updateState {
            it.copy(
                isSwipeEnabled = false,
                isCreatingProfile = true
            )
        }

        tryToCall(
            call = {
                createCustomerUseCase(
                    customerPersonalInfo = state.value.personalInfo.toDomain(),
                    categories = selectedCategoryTitles,
                    location = location
                )
            },
            onSuccess = { customerId ->
                updateState {
                    it.copy(
                        customerId = customerId,
                        isProfileCreated = true,
                        isCreatingProfile = false
                    )
                }

                val profilePicture = state.value.profilePicture
                if (profilePicture != null) {
                    uploadProfilePicture(customerId, profilePicture)
                } else {
                    updateState { it.copy(isSwipeEnabled = true) }
                    sendNewEffect(CustomerRegistrationEffect.RegistrationComplete)
                }
            },
            onError = { error ->
                updateState {
                    it.copy(
                        error = error,
                        isSwipeEnabled = true,
                        isCreatingProfile = false
                    )
                }
            },
            showLoading = true
        )
    }

    private fun uploadProfilePicture(customerId: String, profilePicture: ImageData) {
        updateState { it.copy(isUploadingProfilePicture = true) }

        tryToCall(
            call = {
                uploadCustomerProfilePictureUseCase(
                    customerId = customerId,
                    profilePicture = profilePicture.byteArray,
                    profilePictureFileName = profilePicture.fileName
                )
            },
            onSuccess = { profilePictureUrl ->
                updateState {
                    it.copy(
                        profilePictureUrl = profilePictureUrl,
                        isUploadingProfilePicture = false,
                        isSwipeEnabled = true
                    )
                }
                sendNewEffect(CustomerRegistrationEffect.RegistrationComplete)
            },
            onError = { error ->
                // Even if upload fails, profile is created
                AppLogger.e("CustomerSetup", "Profile picture upload failed: ${error.message}")
                updateState {
                    it.copy(
                        isUploadingProfilePicture = false,
                        isSwipeEnabled = true
                    )
                }
                sendNewEffect(CustomerRegistrationEffect.RegistrationComplete)
            },
            showLoading = false
        )
    }

    private fun validateCurrentPage() {
        val canProceed = when (state.value.currentStep) {
            CustomerRegistrationStep.PERSONAL_INFO ->
                validatePersonalInfo(state.value.personalInfo)

            CustomerRegistrationStep.CATEGORY_SELECTION ->
                state.value.selectedCategoryIds.isNotEmpty()

            CustomerRegistrationStep.LOCATION_GOVERNORATE ->
                state.value.selectedGovernorate != null

            CustomerRegistrationStep.LOCATION_DISTRICT ->
                state.value.selectedDistrict != null

            CustomerRegistrationStep.LOCATION_DETAILED ->
                state.value.detailedLocation.isNotBlank()
        }

        updateState { it.copy(canNavigateNext = canProceed) }
    }

    private fun validatePersonalInfo(info: CustomerPersonalInfoUiModel): Boolean {
        return info.name.length >= 2 &&
                info.phoneNumber.length >= 10 &&
                info.phoneNumber.matches(Regex("^\\+?[1-9]\\d{1,14}$"))
    }

}