package org.example.project.designSystem.textStyle

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import org.example.project.designSystem.colors.CraftoColors
import org.example.project.designSystem.colors.CraftoDarkColors
import org.example.project.designSystem.colors.CraftoLightColors
import org.example.project.designSystem.radius.CraftoRadius

object AppTheme {
    val textStyle : CraftoTextStyle
        @Composable
        @ReadOnlyComposable
        get() = LocalCraftoTextStyle.current
    val craftoColors :CraftoColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCraftoColors.current

    val craftoRadius : CraftoRadius
        @Composable
        @ReadOnlyComposable
        get() = LocalCraftoRadius.current

    @Composable
    operator fun invoke(
        isDarkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
    ){
        val color = if(isDarkTheme) CraftoDarkColors else CraftoLightColors
        CompositionLocalProvider(
            LocalCraftoColors provides color,
            LocalCraftoTextStyle provides defaultTextStyle,
            LocalCraftoRadius provides CraftoRadius()
        ) {
            content()
        }

    }

}
private val LocalCraftoColors =staticCompositionLocalOf { CraftoLightColors }

private val LocalCraftoTextStyle = staticCompositionLocalOf { defaultTextStyle }

private val LocalCraftoRadius = staticCompositionLocalOf { CraftoRadius() }