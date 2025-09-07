package org.example.project.designSystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.clipboard_text
import crafto.composeapp.generated.resources.clipboard_text_1
import crafto.composeapp.generated.resources.dialog
import crafto.composeapp.generated.resources.dialog_1
import crafto.composeapp.generated.resources.home_angle
import crafto.composeapp.generated.resources.home_angle_1
import crafto.composeapp.generated.resources.user_circle
import crafto.composeapp.generated.resources.user_circle_1
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


data class CraftoNavItem(
    val label: String,
    val icon: Painter,
    val selectedIcon: Painter
)


@Composable
fun CraftoBottomNavBar(
    items: List<CraftoNavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = AppTheme.craftoColors.background.card,
    selectedContentColor: Color = AppTheme.craftoColors.brand.primary,
    labelColor: Color = AppTheme.craftoColors.shade.tertiary,
    unselectedContentColor: Color = Color.Gray,
    shadowElevation: Dp = 0.dp
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shadowElevation = shadowElevation,
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp, horizontal = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = index == selectedIndex

                val iconPainter = if (isSelected) item.selectedIcon else item.icon
                val iconTint = if (isSelected) selectedContentColor else unselectedContentColor
                val labelTint = if (isSelected) selectedContentColor else labelColor

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { onItemSelected(index) }
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = iconPainter,
                        contentDescription = item.label,
                        tint = iconTint,
                        modifier = Modifier.size(24.dp)
                    )

                    Text(
                        text = item.label,
                        style = AppTheme.textStyle.label.medium,
                        color = labelTint,
                        maxLines = 1
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun CraftoBottomNavBarPreview() {
    var selectedIndex by remember { mutableStateOf(0) }

    val items = listOf(
        CraftoNavItem(
            "Home",
            painterResource(Res.drawable.home_angle),
            painterResource(Res.drawable.home_angle_1)
        ),
        CraftoNavItem(
            "My Requests",
            painterResource(Res.drawable.clipboard_text),
            painterResource(Res.drawable.clipboard_text_1)
        ),
        CraftoNavItem(
            "Messages",
            painterResource(Res.drawable.dialog),
            painterResource(Res.drawable.dialog_1)
        ),
        CraftoNavItem(
            "More",
            painterResource(Res.drawable.user_circle),
            painterResource(Res.drawable.user_circle_1)
        ),
    )

    AppTheme {
        CraftoBottomNavBar(
            items = items,
            selectedIndex = selectedIndex,
            onItemSelected = { selectedIndex = it }
        )
    }
}