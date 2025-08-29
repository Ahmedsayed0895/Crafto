package org.example.project.designSystem

import androidx.compose.ui.graphics.Color

data class CraftoColors(
    val background: Background,
    val shade: Shade,
    val additional: Additional,
    val stroke: Stroke,
    val overlay: Overlay,
    val brand: Brand,
    val button: Button
)

data class Background(
    val screen: Color,
    val card: Color,
    val bottomSheet: Color,
    val bottomSheetCard: Color
)

data class Shade(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val quaternary: Color,
    val quinary: Color
)

data class Additional(
    val primaryError: Color,
    val primarySuccess: Color,
    val primaryWarning: Color,
    val primaryPurple: Color,
    val primaryRed: Color,
    val primaryBlue: Color,
    val primaryTurquoise: Color,
    val primaryYellow: Color,
    val primaryGreen: Color,
    val secondaryError: Color,
    val secondarySuccess: Color,
    val secondaryWarning: Color,
    val secondaryPurple: Color,
    val secondaryRed: Color,
    val secondaryBlue: Color,
    val secondaryTurquoise: Color,
    val secondaryYellow: Color,
    val secondaryGreen: Color
)

data class Stroke(
    val primary: Color
)

data class Overlay(
    val primary: Color
)

data class Brand(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color
)

data class Button(
    val primary: Color,
    val secondary: Color,
    val disabled: Color,
    val onPrimary: Color,
    val onSecondary: Color,
    val onDisabled: Color,
    val onTertiary: Color
)