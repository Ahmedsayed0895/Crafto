package org.example.project.categoryScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.account_setup_category_description
import crafto.composeapp.generated.resources.account_setup_category_title
import crafto.composeapp.generated.resources.arrow_left
import org.example.project.designSystem.components.ButtonState
import org.example.project.designSystem.components.Chip
import org.example.project.designSystem.components.PrimaryButton
import org.example.project.designSystem.components.ProgressIndicator
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AccountSetupCategoryScreen() {
    AccountSetupCategoryContent(
        onBackButtonClick = {},
        onNextButtonClick = {},
    )
}

@Composable
fun AccountSetupCategoryContent(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.craftoColors.background.screen)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        AccountSetupTopBar(onBackButtonClick = onBackButtonClick)
        TitleDescriptionBox(
            modifier = Modifier.weight(7f),
            title = stringResource(Res.string.account_setup_category_title),
            description = stringResource(Res.string.account_setup_category_description)
        )
        ActionBox()
        PrimaryButton(
            text = "Next",
            enabled = true,
            buttonState = ButtonState.Enable,
            modifier = Modifier.fillMaxWidth(),
            onClick = onNextButtonClick
        )

    }
}

data class Category(
    val title: String,
    val isSelected: Boolean,
    val color: Color,
)

private val categoryList = listOf(
    Category("Plumbing", false, Color(0xFF00ff00)),
    Category("Electrical", false, Color(0xFFff0000)),
    Category("Cleaning", false, Color(0xFF0000ff)),
    Category("AC Repair", false, Color(0xFF00ff00)),
    Category("Furniture", false, Color(0xFF00ff00)),
    Category("Painting", false, Color(0xFF00ff00)),
    Category("Carpentry", false, Color(0xFF00ff00)),
    Category("Roofing", false, Color(0xFF00ff00)),
    Category("Landscaping", false, Color(0xFF00ff00)),
    Category("Pest Control", false, Color(0xFF00ff00)),
    Category("Appliance Repair", false, Color(0xFF00ff00)),
    Category("Pool Maintenance", false, Color(0xFF00ff00)),
    Category("HVAC Maintenance", false, Color(0xFF00ff00)),
)


@Composable
private fun AccountSetupTopBar(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit

) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    )
    {
        BackButton(onBackButtonClick = onBackButtonClick)
        ProgressIndicator(
            currentPage = 2,
            totalPage = 4,
            modifier = Modifier.fillMaxWidth(0.75f),
        )
    }
}

@Composable
private fun TitleDescriptionBox(
    modifier: Modifier,
    title: String,
    description: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(), contentAlignment = Alignment.BottomStart

    ) {
        TitleDescriptionText(
            title = title,
            description = description
        )

    }
}

@Composable
private fun TitleDescriptionText(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        Text(
            text = title,
            style = AppTheme.textStyle.display,
            color = AppTheme.craftoColors.shade.primary
        )
        Text(
            text = description,
            style = AppTheme.textStyle.body.largeRegular,
            color = AppTheme.craftoColors.shade.secondary
        )
    }
}

@Composable
private fun ActionBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            categoryList.forEachIndexed { index, category ->
                var isSelected by remember { mutableStateOf(category.isSelected) }
                Chip(
                    text = category.title,
                    isSelected = isSelected,
                    onChipSelected = { text ->
                        if (category.title == text)
                            isSelected = !isSelected
                    },
                    modifier = Modifier.background(
                        if (isSelected)
                            category.color
                        else
                            AppTheme.craftoColors.background.card,
                        shape = RoundedCornerShape(AppTheme.craftoRadius.full)
                    ),
                    textColor = if (isSelected)
                        AppTheme.craftoColors.background.card
                    else
                        AppTheme.craftoColors.shade.secondary
                )
            }
        }
    }
}


@Composable
private fun BackButton(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(AppTheme.craftoColors.background.card)
            .clickable(onClick = onBackButtonClick),
        contentAlignment = Alignment.Center
    )
    {
        Icon(
            painter = painterResource(Res.drawable.arrow_left),
            contentDescription = "back button",
            tint = AppTheme.craftoColors.shade.primary
        )
    }
}


@Preview
@Composable
fun AccCategoryLightPreview() {
    AppTheme {
        AccountSetupCategoryScreen()

    }
}

@Preview
@Composable
fun AccCategoryDarkPreview() {
    AppTheme(isDarkTheme = true) {
        AccountSetupCategoryScreen()

    }
}