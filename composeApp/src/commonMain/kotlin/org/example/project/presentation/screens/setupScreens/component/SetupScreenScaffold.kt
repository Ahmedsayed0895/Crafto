package org.example.project.presentation.ui.screens.setupScreens.component

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
import crafto.composeapp.generated.resources.account_setup_craftsman_category_description
import crafto.composeapp.generated.resources.account_setup_craftsman_category_title
import crafto.composeapp.generated.resources.next
//import org.example.project.data.memory.dataSource.categoryList
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.components.PrimaryButton
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.util.DeviceConfiguration
import org.example.project.presentation.viewmodel.accountSetup.AccountSetupCategoryState
import org.example.project.presentation.viewmodel.accountSetup.AccountSetupState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SetupScreenScaffold(
    modifier: Modifier = Modifier,
    currentPageNumber: Int,
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
                description = description,
                onBackButtonClick = onBackButtonClick,
                onNextButtonClick = onNextButtonClick,
                content = content
            )
        }

        DeviceConfiguration.MOBILE_LANDSCAPE,
        DeviceConfiguration.TABLET_LANDSCAPE -> {
            LandscapeLayout(
                modifier = modifier,
                currentPageNumber = currentPageNumber,
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
        AccountSetupTopBar(onBackButtonClick = onBackButtonClick, currentPage = currentPageNumber)
        TitleDescriptionBox(
            modifier = Modifier.weight(1f),
            title = title,
            description = description,
        )
        content()
        PrimaryButton(
            text = stringResource(Res.string.next),
            enabled = true,
            buttonState = ButtonState.Enable,
            modifier = Modifier.fillMaxWidth(),
            onClick = onNextButtonClick
        )

    }
}

@Composable
private fun LandscapeLayout(
    modifier: Modifier = Modifier,
    currentPageNumber: Int,
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
        AccountSetupTopBar(onBackButtonClick = onBackButtonClick, currentPage = currentPageNumber)
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
            text = stringResource(Res.string.next),
            enabled = true,
            buttonState = ButtonState.Enable,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .align(Alignment.End),
            onClick = onNextButtonClick
        )

    }
}

@Preview
@Composable
fun SetupScreenScaffoldLightPreview() {
    SetupScreenScaffold(
        currentPageNumber = 2,
        title = stringResource(Res.string.account_setup_craftsman_category_title),
        description = stringResource(Res.string.account_setup_craftsman_category_description),
        onBackButtonClick = {},
        onNextButtonClick = {},

        )
    {
//        CategoryActionBox(
//            state = AccountSetupState(
//                categoryState = AccountSetupCategoryState(
//                    categories = categoryList
//                )
//            ),
//            onChipSelected = {}
//        )
    }


}

@Preview
@Composable
fun SetupScreenScaffoldDarkPreview() {
    AppTheme(isDarkTheme = true) {
        SetupScreenScaffoldLightPreview()
    }
}