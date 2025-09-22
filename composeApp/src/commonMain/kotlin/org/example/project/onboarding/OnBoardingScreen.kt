package org.example.project.onboarding

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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.get_started
import crafto.composeapp.generated.resources.next
import crafto.composeapp.generated.resources.onboarding1
import crafto.composeapp.generated.resources.onboarding2
import crafto.composeapp.generated.resources.onboarding3
import crafto.composeapp.generated.resources.skip
import kotlinx.coroutines.launch
import org.example.project.onboarding.composable.OnBoardingIndicator
import org.example.project.onboarding.composable.OnBoardingItem
import org.example.project.onboarding.composable.OnBoardingPage
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.components.PrimaryButton
import org.example.project.presentation.designsystem.components.SecondaryButton
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun OnBoardingScreen(
    onBoardingPagesContent: List<OnBoardingPage>
) {
    OnBoardingContent(
        onSkipButtonClick = {},
        onGetStartButtonClick = {},
        onBoardingPagesContent = onBoardingPagesContent
    )
}

@Composable
fun OnBoardingContent(
    onBoardingPagesContent: List<OnBoardingPage>,
    onSkipButtonClick: () -> Unit,
    onGetStartButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState =
        rememberPagerState(initialPage = 0, pageCount = { onBoardingPagesContent.size })
    val coroutineScope = rememberCoroutineScope()
    val buttonText =
        if (pagerState.currentPage == onBoardingPagesContent.size - 1) Res.string.get_started else Res.string.next


    Column(
        modifier = modifier.fillMaxSize()
            .background(AppTheme.craftoColors.background.screen)
            .padding(horizontal = 16.dp)
            .systemBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
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
                    onSkipButtonClick()
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
            OnBoardingItem(onBoardingPagesContent[page])
        }

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OnBoardingIndicator(
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
                    if (pagerState.currentPage < onBoardingPagesContent.size - 1) {
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
                        onGetStartButtonClick()
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
}


@Preview
@Composable
private fun OnBoardingScreenPreview() {
    AppTheme(isDarkTheme = false) {
        OnBoardingContent(
            onSkipButtonClick = {},
            onGetStartButtonClick = {},
            onBoardingPagesContent = listOf(
                OnBoardingPage(
                    imageRes = Res.drawable.onboarding1,
                    title = "Relax, We’ve Got It Covered",
                    description = "From the comfort of your couch, post your request and let trusted professionals come to you, no calls, no stress."
                ),
                OnBoardingPage(
                    imageRes = Res.drawable.onboarding2,
                    title = "Find What You Need in Seconds",
                    description = "Browse dozens of home services — from quick fixes to big projects. Just tap a category and get started instantly."
                ),
                OnBoardingPage(
                    imageRes = Res.drawable.onboarding3,
                    title = "Post, Compare Offers and Choose!",
                    description = "Receive multiple offers from nearby professionals, check their prices and ratings, then pick the one that suits you best."
                ),
            )
        )
    }
}