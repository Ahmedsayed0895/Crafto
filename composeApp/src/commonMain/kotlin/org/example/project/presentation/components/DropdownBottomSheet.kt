package org.example.project.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.presentation.designsystem.components.BottomSheet
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.alt_arrow_down
import crafto.composeapp.generated.resources.down_arrow

@Composable
fun <T> DropdownBottomSheet(
    show: Boolean,
    items: List<T>,
    itemLabel: (T) -> String,
    onDismiss: () -> Unit,
    onSelect: (T) -> Unit
) {
    if (show && items.isNotEmpty()) {
        BottomSheet(onDismissRequest = onDismiss) {
            LazyColumn {
                items(items) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelect(item) }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = itemLabel(item),
                            style = AppTheme.textStyle.body.medium,
                            color = AppTheme.craftoColors.shade.primary,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            painter = painterResource(Res.drawable.alt_arrow_down),
                            contentDescription = stringResource(Res.string.down_arrow),
                            tint = AppTheme.craftoColors.shade.secondary
                        )
                    }
                }
            }
        }
    }
}