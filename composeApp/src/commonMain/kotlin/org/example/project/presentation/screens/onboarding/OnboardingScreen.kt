package org.example.project.presentation.screens.onboarding

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.get_started
import crafto.composeapp.generated.resources.next
import crafto.composeapp.generated.resources.skip
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.components.PrimaryButton
import org.example.project.presentation.designsystem.components.SecondaryButton
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.screens.onboarding.composable.OnboardingIndicator
import org.example.project.presentation.screens.onboarding.composable.OnboardingItem
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = koinViewModel(),
    onNavigateToOtp: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                OnboardingScreenEffect.NavigateToGetStartedScreen,
                OnboardingScreenEffect.NavigateToRegisterScreen -> {
                    onNavigateToOtp()
                }
                OnboardingScreenEffect.NavigateToNext -> {
                    // Internal navigation handled by pager
                }
            }
        }
    }
    OnboardingContent(
        state = state,
        interactions = viewModel
    )
}

@Composable
private fun OnboardingContent(
    state: OnboardingScreenState,
    interactions: OnboardingScreenInteractionListener,
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { state.onboardingData.size })
    val coroutineScope = rememberCoroutineScope()
    val buttonText = if (pagerState.currentPage == state.onboardingData.size - 1) Res.string.get_started else Res.string.next

    Column(
        modifier = Modifier.fillMaxSize()
            .background(AppTheme.craftoColors.background.screen)
            .padding(horizontal = 16.dp)
            .systemBarsPadding()
            .verticalScroll(rememberScrollState()),

        ) {
        if (state.loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp).padding(top = 40.dp),
                    color = AppTheme.craftoColors.brand.primary
                )
            }
        } else {
            Box(
                modifier = Modifier.padding(top = 24.dp).align(Alignment.End),
            ) {
                SecondaryButton(
                    text = stringResource(Res.string.skip),
                    enabled = true,
                    onClick = {
                        val skipPage = pagerState.pageCount - 1
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                page = skipPage,
                                animationSpec = spring(
                                    dampingRatio = 0.85f,
                                    stiffness = 44f
                                )
                            )
                        }
//                        interactions::onSkipClick
                        interactions.onSkipClick()
                    },
                    buttonState = ButtonState.Enable,
                    containerColor = AppTheme.craftoColors.button.secondary,
                    contentPadding = PaddingValues(vertical = 14.dp, horizontal = 24.dp)
                )
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.padding(vertical = 32.dp)
            ) { page ->
                OnboardingItem(state.onboardingData[page])
            }

            Spacer(modifier = Modifier.weight(1f))

            OnboardingActionsRow(
                pagerState = pagerState,
                coroutineScope = coroutineScope,
                state = state,
                interactions = interactions,
                buttonText = buttonText,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
    }
}

@Composable
private fun OnboardingActionsRow(
    modifier: Modifier,
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    state: OnboardingScreenState,
    interactions: OnboardingScreenInteractionListener,
    buttonText: StringResource
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OnboardingIndicator(
            currentPage = pagerState.currentPage,
            totalPage = pagerState.pageCount,
            progressColor = AppTheme.craftoColors.brand.primary,
            trackColor = AppTheme.craftoColors.shade.quaternary,
            modifier = Modifier.width(100.dp)
        )
        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = stringResource(buttonText),
            enabled = true,
            onClick = {
                if (pagerState.currentPage < state.onboardingData.size - 1) {
                    val nextPage = pagerState.currentPage + 1
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(
                            page = nextPage,
                            animationSpec = spring(
                                dampingRatio = 0.85f,
                                stiffness = 440f
                            )
                        )
                    }
                } else {
                    //interactions::onGetStartedClick
                    interactions.onGetStartedClick()
                }
            },
            buttonState = ButtonState.Enable,
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = 0.85f,
                    stiffness = 44f,
                ),
                alignment = Alignment.BottomEnd
            ),
            contentPadding = PaddingValues(vertical = 14.dp, horizontal = 24.dp)
        )
    }
}


@Preview
@Composable
private fun OnBoardingScreenPreview() {
    AppTheme(isDarkTheme = false) {
        OnboardingContent(
            state = OnboardingScreenState(),
            interactions = object : OnboardingScreenInteractionListener {
                override fun onSkipClick() {}

                override fun onNextClick() {}

                override fun onGetStartedClick() {}
            }
        )
    }
}