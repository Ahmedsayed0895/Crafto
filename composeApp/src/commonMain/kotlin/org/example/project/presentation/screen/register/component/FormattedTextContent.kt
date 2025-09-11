package org.example.project.presentation.screen.register.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.designSystem.textStyle.AppTheme

@Composable
fun FormattedTextContent(content: String) {
    val paragraphLines = content.split("\n")
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        var i = 0
        while (i < paragraphLines.size) {
            val line = paragraphLines[i].trim()

            if (line.isEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                i++
                continue
            }

            val isTitle = "\\d+\\.".toRegex().find(line) != null

            Text(
                text = line,
                style = if (isTitle) {
                    AppTheme.textStyle.body.largeSemiBold
                } else {
                    AppTheme.textStyle.body.medium
                },
                color = AppTheme.craftoColors.shade.primary,
                modifier = Modifier.padding(bottom = if (i < paragraphLines.size - 1) 8.dp else 0.dp)
            )
            i++
        }
    }
}