package org.example.project.presentation.screens.setup.craftsmansetup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.identity_verification
import crafto.composeapp.generated.resources.personal_info
import crafto.composeapp.generated.resources.portfolio_upload
import crafto.composeapp.generated.resources.registration_step_2_description
import crafto.composeapp.generated.resources.registration_step_3_description
import crafto.composeapp.generated.resources.registration_step_4_description
import crafto.composeapp.generated.resources.registration_step_5_description
import crafto.composeapp.generated.resources.service_selection
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.components.TextButton
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.screens.setup.composable.ErrorSnackBar
import org.example.project.presentation.screens.setup.composable.SetupScreenScaffold
import org.example.project.presentation.screens.setup.composable.page.IdentityVerificationPage
import org.example.project.presentation.screens.setup.composable.page.PersonalInfoPage
import org.example.project.presentation.screens.setup.composable.page.PortfolioUploadPage
import org.example.project.presentation.screens.setup.composable.page.ServiceSelectionPage
import org.example.project.presentation.shared.base.ErrorUiState
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CraftsmanSetupScreen(
    viewModel: CraftsmanSetupViewModel= koinViewModel(),
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
                CraftsmanRegistrationEffect.RegistrationComplete -> onComplete()
            }
        }
    }

    AnimatedVisibility(
        visible = true,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {
        CraftsmanSetupContent(
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
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CraftsmanSetupContent(
    state: CraftsmanSetupUiState,
    viewModel: CraftsmanSetupViewModel,
    pagerState: PagerState,
    onClose: () -> Unit
) {
    SetupScreenScaffold(
        currentPageNumber = state.currentPageIndex + 1,
        totalPages = state.totalPages,
        nextButtonText = state.nextButtonText,
        nextButtonEnabled = state.canNavigateNext && !state.isLoading,
        nextButtonState = if (state.isLoading) ButtonState.LOADING else ButtonState.Enable,
        onBackButtonClick = {
            if (state.currentPageIndex == 0) onClose()
            else viewModel.navigateBack()
        },
        onNextButtonClick = {
            when (state.currentStep) {
                RegistrationStep.IDENTITY_VERIFICATION -> {
                    if (state.hasUploadedIdCards)
                        viewModel.onUploadIdCards()
//                    } else {
//                        viewModel.onSkipIdentityVerification()
//                    }
                }
                else -> viewModel.navigateNext()
            }
        },
        title = when (state.currentStep) {
            RegistrationStep.SERVICE_SELECTION -> {
                stringResource(Res.string.service_selection)
                }
            RegistrationStep.PERSONAL_INFO -> {
                stringResource(Res.string.personal_info)
                }
            RegistrationStep.PORTFOLIO_UPLOAD -> {
                stringResource(Res.string.portfolio_upload)
            }
            RegistrationStep.IDENTITY_VERIFICATION -> {
                stringResource(Res.string.identity_verification)
            }
        },
        description =when (state.currentStep) {
            RegistrationStep.SERVICE_SELECTION -> {
                stringResource(Res.string.registration_step_2_description)
            }
            RegistrationStep.PERSONAL_INFO -> {
                stringResource(Res.string.registration_step_3_description)
            }
            RegistrationStep.PORTFOLIO_UPLOAD -> {
                stringResource(Res.string.registration_step_4_description)
            }
            RegistrationStep.IDENTITY_VERIFICATION -> {
                stringResource(Res.string.registration_step_5_description)
            }
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
                when (RegistrationStep.fromIndex(page)) {
                    RegistrationStep.SERVICE_SELECTION -> {
                        ServiceSelectionPage(
                            availableCategories = state.availableCategories,
                            onCategoryToggled = viewModel::onCategoryToggled,
                            selectedServiceIds = state.selectedCategoryIds,
                        )
                    }

                    RegistrationStep.PERSONAL_INFO -> {
                        PersonalInfoPage(
                            personalInfo = state.personalInfo,
                            profilePicture = state.profilePicture,
                            onPersonalInfoChanged = viewModel::onPersonalInfoChanged,
                            onProfilePictureSelected = viewModel::onProfilePictureSelected,
                            onImagePickerError = { errorMessage ->
                                viewModel.onImagePickerError(ErrorUiState(errorMessage))
                            },
                            isLoading = state.isLoading,
                            isUploadingProfilePicture = state.isUploadingProfilePicture,
                            onRemove = viewModel::onProfilePictureRemoved
                        )
                    }

                    RegistrationStep.PORTFOLIO_UPLOAD -> {
                        PortfolioUploadPage(
                            images = state.portfolioImages,
                            workDescription = state.workDescription,
                            canAddMore = state.canAddMoreImages,
                            onAddPhotosClicked = viewModel::onPortfolioImagesAdded,
                            onImageRemoved = viewModel::onPortfolioImageRemoved,
                            onDescriptionChanged = viewModel::onWorkDescriptionChanged,
                            onError = { errorMessage ->
                                viewModel.onImagePickerError(ErrorUiState(errorMessage))
                            } ,
                        )
                    }

                    RegistrationStep.IDENTITY_VERIFICATION -> {
                        IdentityVerificationPage(
                            idCardFront = state.idCardFront,
                            idCardBack = state.idCardBack,
                            onIdCardSelected = viewModel::onIdCardSelected,
                            onSkip = viewModel::onSkipIdentityVerification,
                            onErrorMessage = { errorMessage ->
                                viewModel.onImagePickerError(ErrorUiState(errorMessage))
                            },
                            onFrontImageRemoved = viewModel::onFrontIdCardRemoved,
                            onBackImageRemoved = viewModel::onBackIdCardRemoved,
                        )
                    }
                }
            }
        }
    }
}
