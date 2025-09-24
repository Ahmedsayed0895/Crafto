package org.example.project.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.x
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    showCloseIcon: Boolean = false,
    onDismissRequest: () -> Unit = {},
    headerContent: @Composable () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        containerColor = AppTheme.craftoColors.background.bottomSheet,
        shape = RoundedCornerShape(
            topStart = AppTheme.craftoRadius.x3l,
            topEnd = AppTheme.craftoRadius.x3l
        ),
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .size(width = 48.dp, height = 3.dp)
                    .background(
                        color = AppTheme.craftoColors.shade.quaternary,
                        shape = RoundedCornerShape(8.dp)
                    )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
            ) {
                Box(modifier = Modifier.align(Alignment.TopStart)){
                    headerContent()
                }
                if (showCloseIcon){
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(40.dp)
                            .clip(shape = CircleShape)
                            .clickable { onDismissRequest() }, contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.x),
                            contentDescription = "cancel button",
                            tint = AppTheme.craftoColors.shade.secondary,
                            modifier = Modifier
                                .size(20.dp)
                        )
                    }
                }
            }
            content()
        }
    }
}

@Preview
@Composable
private fun BottomSheetPreview() {
    AppTheme {
        BottomSheet(
            headerContent = {
                Text(
                    text = "title",
                    style = AppTheme.textStyle.title.small,
                    color = AppTheme.craftoColors.shade.primary
                )
            },
            showCloseIcon = true,
            onDismissRequest = {},
            content = {}
        )
    }
}