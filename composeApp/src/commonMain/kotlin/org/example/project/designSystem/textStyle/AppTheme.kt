package org.example.project.designSystem.textStyle

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

object AppTheme {
    val textStyle : CraftoTextStyle
        @Composable
        @ReadOnlyComposable
        get() = LocalCraftoTextStyle.current
    @Composable
    operator fun invoke(
        isDarkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
    ){
        CompositionLocalProvider(
            LocalCraftoTextStyle provides defaultTextStyle,
        ) {
            content()
        }

    }


}

private val LocalCraftoTextStyle = staticCompositionLocalOf { defaultTextStyle }
