package org.example.project.presentation.screens.setup.customersetup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.account_setup_craftsman_category_title
import crafto.composeapp.generated.resources.account_setup_customer_category_description
import crafto.composeapp.generated.resources.customer_description
import crafto.composeapp.generated.resources.location_description
import crafto.composeapp.generated.resources.location_title
import crafto.composeapp.generated.resources.personal_info
import crafto.composeapp.generated.resources.service_selection
import org.example.project.presentation.components.DropdownBottomSheet
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.screens.setup.composable.ErrorSnackBar
import org.example.project.presentation.screens.setup.composable.SetupScreenScaffold
import org.example.project.presentation.screens.setup.composable.page.CustomerPersonalInfoPage
import org.example.project.presentation.screens.setup.composable.page.LocationDetailedPage
import org.example.project.presentation.screens.setup.composable.page.LocationDistrictPage
import org.example.project.presentation.screens.setup.composable.page.LocationGovernoratePage
import org.example.project.presentation.screens.setup.composable.page.ServiceSelectionPage
import org.example.project.presentation.shared.base.ErrorUiState
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun CustomerSetupScreen(
    viewModel: CustomerSetupViewModel = koinViewModel(),
    onComplete: () -> Unit={},
    onClose: () -> Unit={}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(
        initialPage = state.currentPageIndex,
        pageCount = { state.totalPages }
    )

    LaunchedEffect(pagerState.currentPage) {
        viewModel.onPageChanged(pagerState.currentPage)
    }

    LaunchedEffect(state.currentPageIndex) {
        pagerState.animateScrollToPage(state.currentPageIndex)
    }


    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                CustomerRegistrationEffect.RegistrationComplete -> onComplete()
            }
        }
    }

    AnimatedVisibility(
        visible = true,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {
        CustomerSetupContent(
            state = state,
            viewModel = viewModel,
            pagerState = pagerState,
            onClose = onClose
        )
    }

    AnimatedVisibility(
        visible = state.error != null,
        enter = slideInVertically { it } + fadeIn(),
        exit = slideOutVertically { it } + fadeOut()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            state.error?.let { error ->
                ErrorSnackBar(
                    error = error,
                    onDismiss = viewModel::clearError
                )
            }
        }
    }

    DropdownBottomSheet(
        show = state.showGovernorateSheet,
        items = state.governorates,
        itemLabel = { it.name },
        onDismiss = viewModel::onGovernorateSheetClose,
        onSelect = viewModel::onGovernorateSelected
    )

    DropdownBottomSheet(
        show = state.showDistrictSheet,
        items = state.districts,
        itemLabel = { it.name },
        onDismiss = viewModel::onDistrictSheetClose,
        onSelect = viewModel::onDistrictSelected
    )
}

@Composable
private fun CustomerSetupContent(
    state: CustomerSetupUiState,
    viewModel: CustomerSetupViewModel,
    pagerState: PagerState,
    onClose: () -> Unit
) {
    SetupScreenScaffold(
        currentPageNumber = state.currentPageIndex + 1,
        totalPages = state.totalPages,
        nextButtonText = state.nextButtonText,
        nextButtonEnabled = state.canNavigateNext && !state.isLoading && !state.isCreatingProfile,
        nextButtonState = when {
            state.isLoading || state.isCreatingProfile -> ButtonState.LOADING
            state.canNavigateNext -> ButtonState.Enable
            else -> ButtonState.DISABLED
        },
        onBackButtonClick = {
            if (state.currentPageIndex == 0) onClose()
            else viewModel.navigateBack()
        },
        onNextButtonClick = {
            viewModel.navigateNext()
        },
        title = when (state.currentStep) {
            CustomerRegistrationStep.PERSONAL_INFO ->
                stringResource(Res.string.personal_info)
            CustomerRegistrationStep.CATEGORY_SELECTION ->
                stringResource(Res.string.account_setup_craftsman_category_title)
            CustomerRegistrationStep.LOCATION_GOVERNORATE ->
                stringResource(Res.string.location_title)
            CustomerRegistrationStep.LOCATION_DISTRICT ->
                stringResource(Res.string.location_title)
            CustomerRegistrationStep.LOCATION_DETAILED ->
                stringResource(Res.string.location_title)
        },
        description = when (state.currentStep) {
            CustomerRegistrationStep.PERSONAL_INFO ->
                stringResource(Res.string.customer_description)
            CustomerRegistrationStep.CATEGORY_SELECTION ->
                stringResource(Res.string.account_setup_customer_category_description)
            CustomerRegistrationStep.LOCATION_GOVERNORATE ->
                stringResource(Res.string.location_description)
            CustomerRegistrationStep.LOCATION_DISTRICT ->
                stringResource(Res.string.location_description)
            CustomerRegistrationStep.LOCATION_DETAILED ->
                stringResource(Res.string.location_description)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.craftoColors.background.screen)
        ) {
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = state.isSwipeEnabled && state.canNavigateNext
            ) { page ->
                when (CustomerRegistrationStep.fromIndex(page)) {
                    CustomerRegistrationStep.PERSONAL_INFO -> {
                        CustomerPersonalInfoPage(
                            personalInfo = state.personalInfo,
                            profilePicture = state.profilePicture,
                            onPersonalInfoChanged = viewModel::onPersonalInfoChanged,
                            onProfilePictureSelected = viewModel::onProfilePictureSelected,
                            onProfilePictureRemoved = viewModel::onProfilePictureRemoved,
                            onImagePickerError = { errorMessage ->
                                viewModel.onImagePickerError(ErrorUiState(errorMessage))
                            },
                            isLoading = state.isLoading,
                            isUploadingProfilePicture = state.isUploadingProfilePicture
                        )
                    }

                    CustomerRegistrationStep.CATEGORY_SELECTION -> {
                        ServiceSelectionPage(
                            availableCategories = state.availableCategories,
                            selectedServiceIds = state.selectedCategoryIds,
                            onCategoryToggled = viewModel::onCategoryToggled
                        )
                    }

                    CustomerRegistrationStep.LOCATION_GOVERNORATE -> {
                        LocationGovernoratePage(
                            selectedGovernorate = state.selectedGovernorate,
                            locationDisplayText = state.locationDisplayText,
                            isLoading = state.isLoadingGovernorates,
                            onOpenSheet = viewModel::onGovernorateSheetOpen
                        )
                    }

                    CustomerRegistrationStep.LOCATION_DISTRICT -> {
                        LocationDistrictPage(
                            selectedDistrict = state.selectedDistrict,
                            selectedGovernorateName = state.selectedGovernorate?.name ?: "",
                            isLoading = state.isLoadingDistricts,
                            hasDistricts = state.districts.isNotEmpty(),
                            onOpenSheet = viewModel::onDistrictSheetOpen
                        )
                    }

                    CustomerRegistrationStep.LOCATION_DETAILED -> {
                        LocationDetailedPage(
                            detailedLocation = state.detailedLocation,
                            locationDisplayText = state.locationDisplayText,
                            onDetailedLocationChanged = viewModel::onDetailedLocationChanged
                        )
                    }
                }
            }
        }
    }
}