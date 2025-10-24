package org.example.project.presentation.screens.setupScreens.craftsmansetup

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
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.components.TextButton
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.screens.setupScreens.composable.SetupScreenScaffold
import org.example.project.presentation.screens.setupScreens.composable.page.IdentityVerificationPage
import org.example.project.presentation.screens.setupScreens.composable.page.PersonalInfoPage
import org.example.project.presentation.screens.setupScreens.composable.page.PortfolioUploadPage
import org.example.project.presentation.screens.setupScreens.composable.page.ServiceSelectionPage
import org.example.project.presentation.screens.setupScreens.composable.page.UserTypeSelectionPage
import org.example.project.presentation.viewmodel.base.ErrorUiState
import org.example.project.presentation.viewmodel.craftsmansetup.CraftsmanRegistrationEffect
import org.example.project.presentation.viewmodel.craftsmansetup.CraftsmanSetupUiState
import org.example.project.presentation.viewmodel.craftsmansetup.CraftsmanSetupViewModel
import org.example.project.presentation.viewmodel.craftsmansetup.RegistrationStep
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
                ErrorSnackbar(
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
                    if (state.idCardFront != null && state.idCardBack != null) {
                        viewModel.onUploadIdCards()
                    } else {
                        viewModel.onSkipIdentityVerification()
                    }
                }
                else -> viewModel.navigateNext()
            }
        },
        title = when (state.currentStep) {
            RegistrationStep.USER_TYPE -> { "How would you like to use Crafto?" }
            RegistrationStep.SERVICE_SELECTION -> {"What services do you offer?"}
            RegistrationStep.PERSONAL_INFO -> {"Let’s personalize your profile"}
            RegistrationStep.PORTFOLIO_UPLOAD -> {"Show Us Your Work"}
            RegistrationStep.IDENTITY_VERIFICATION -> {"Verify Your Identity\n(Optional)"}
        },
        description =when (state.currentStep) {
            RegistrationStep.USER_TYPE -> { "You can switch roles anytime from your profile." }
            RegistrationStep.SERVICE_SELECTION -> {"Choose your specialties to get relevant job requests. You can change this later."}
            RegistrationStep.PERSONAL_INFO -> {"We’ll use this to personalize your experience. You can add a profile photo too, or skip for now."}
            RegistrationStep.PORTFOLIO_UPLOAD -> {"Add photos or a video of your past work. This helps build trust with customers."}
            RegistrationStep.IDENTITY_VERIFICATION -> {"Uploading your ID helps build trust with customers. Verified craftsmen get more jobs and a special badge on their profile."}
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
                    RegistrationStep.USER_TYPE -> {
                        UserTypeSelectionPage(
                            selectedType = state.userType,
                            onTypeSelected = viewModel::onUserTypeSelected,
                        )
                    }
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
                            isUploadingProfilePicture = state.isUploadingProfilePicture
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
                        )
                    }

                    RegistrationStep.IDENTITY_VERIFICATION -> {
                        IdentityVerificationPage(
                            idCardFront = state.idCardFront,
                            idCardBack = state.idCardBack,
                            onIdCardSelected = viewModel::onIdCardSelected,
                            onUploadClick = viewModel::onUploadIdCards,
                            onSkip = {},
                            onErrorMessage = {} ,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorSnackbar(
    error: ErrorUiState,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Snackbar(
        modifier = modifier.padding(16.dp),
        shape = RoundedCornerShape( 8.dp),
        containerColor = AppTheme.craftoColors.additional.primaryRed.copy(alpha = 0.95f),
        contentColor = AppTheme.craftoColors.button.onPrimary,
        action = {
            TextButton(
                onClick = onDismiss, text = "Dismiss",
                enabled = true, buttonState = ButtonState.Enable
            )
        }
    ) {
        Text(
            text = error.message,
            style = AppTheme.textStyle.body.medium,
            color = AppTheme.craftoColors.button.onPrimary
        )
    }
}