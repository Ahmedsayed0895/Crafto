package org.example.project.presentation.screens.setupScreens.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.next
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.components.PrimaryButton
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.util.DeviceConfiguration
import org.jetbrains.compose.resources.stringResource

@Composable
fun SetupScreenScaffold(
    modifier: Modifier = Modifier,
    currentPageNumber: Int,
    totalPages: Int = 4,
    nextButtonText: String = stringResource(Res.string.next),
    nextButtonEnabled: Boolean = true,
    nextButtonState: ButtonState = ButtonState.Enable,
    title: String,
    description: String,
    onBackButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    content: @Composable (() -> Unit)
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)

    when (deviceConfiguration) {
        DeviceConfiguration.MOBILE_PORTRAIT,
        DeviceConfiguration.TABLET_PORTRAIT,
        DeviceConfiguration.DESKTOP -> {
            PortraitLayout(
                modifier = modifier,
                currentPageNumber = currentPageNumber,
                title = title,
                totalPages = totalPages,
                nextButtonText = nextButtonText,
                nextButtonEnabled = nextButtonEnabled,
                nextButtonState = nextButtonState,
                description = description,
                onBackButtonClick = onBackButtonClick,
                onNextButtonClick = onNextButtonClick,
                content = content,
            )
        }

        DeviceConfiguration.MOBILE_LANDSCAPE,
        DeviceConfiguration.TABLET_LANDSCAPE -> {
            LandscapeLayout(
                modifier = modifier,
                currentPageNumber = currentPageNumber,
                totalPages = totalPages,
                nextButtonText = nextButtonText,
                nextButtonEnabled = nextButtonEnabled,
                nextButtonState = nextButtonState,
                title = title,
                description = description,
                onBackButtonClick = onBackButtonClick,
                onNextButtonClick = onNextButtonClick,
                content = content
            )
        }

    }

}

@Composable
private fun PortraitLayout(
    modifier: Modifier = Modifier,
    currentPageNumber: Int,
    title: String,
    totalPages: Int,
    nextButtonText: String,
    nextButtonEnabled: Boolean,
    nextButtonState: ButtonState,
    description: String,
    onBackButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.craftoColors.background.screen)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    )
    {
        AccountSetupTopBar(
            onBackButtonClick = onBackButtonClick,
            currentPage = currentPageNumber,
            totalPages = totalPages )

        TitleDescriptionBox(
            modifier = Modifier.weight(1f),
            title = title,
            description = description,
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            content()
        }

        PrimaryButton(
            text = nextButtonText,
            enabled = nextButtonEnabled,
            buttonState = nextButtonState,
            modifier = Modifier.fillMaxWidth(),
            onClick = onNextButtonClick
        )
    }
}

@Composable
private fun LandscapeLayout(
    modifier: Modifier = Modifier,
    currentPageNumber: Int,
    totalPages: Int,
    nextButtonText: String,
    nextButtonEnabled: Boolean,
    nextButtonState: ButtonState,
    title: String,
    description: String,
    onBackButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.craftoColors.background.screen)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    )
    {
        AccountSetupTopBar(
            onBackButtonClick = onBackButtonClick,
            currentPage = currentPageNumber,
            totalPages = totalPages)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            TitleDescriptionBox(
                modifier = Modifier.weight(1f),
                title = title,
                description = description,
            )
            Box(modifier = Modifier.weight(1f)) {
                content()
            }
        }

        PrimaryButton(
            text = nextButtonText,
            enabled = nextButtonEnabled,
            buttonState = nextButtonState,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .align(Alignment.End),
            onClick = onNextButtonClick
        )

    }
}

//@Preview
//@Composable
//fun SetupScreenScaffoldLightPreview() {
//    SetupScreenScaffold(
//        currentPageNumber = 2,
//        title = stringResource(Res.string.account_setup_craftsman_category_title),
//        description = stringResource(Res.string.account_setup_craftsman_category_description),
//        onBackButtonClick = {},
//        onNextButtonClick = {},
//
//        )
//    {
//        CategoryActionBox(
//            state = AccountSetupState(
//                categoryState = AccountSetupCategoryState(
//                    categories = categorySeed
//                )
//            ),
//            onChipSelected = {}
//        )
//    }
//
//
//}

//@Preview
//@Composable
//fun SetupScreenScaffoldDarkPreview() {
//    AppTheme(isDarkTheme = true) {
//        SetupScreenScaffoldLightPreview()
//    }
//}