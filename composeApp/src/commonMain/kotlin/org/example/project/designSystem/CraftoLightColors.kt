package org.example.project.designSystem

import androidx.compose.ui.graphics.Color

val CraftoLightColors = CraftoColors(
    background = Background(
        screen = Color(0xFFF7F7F),
        card = Color(0xFFFFFFFF),
        bottomSheet = Color(0xFFFFFFFF),
        bottomSheetCard = Color(0xFFF6F6F)
    ),
    shade = Shade(
        primary = Color(0xFF3131),
        secondary = Color(0xFF7171),
        tertiary = Color(0xFFA5A5A),
        quaternary = Color(0xFFECECEC),
        quinary = Color(0xFFF6F6F)
    ),
    additional = Additional(
        primaryError = Color(0xFF4444),
        primarySuccess = Color(0xFF55E5E),
        primaryWarning = Color(0xFFFAC15),
        primaryPurple = Color(0xFF83CE),
        primaryRed = Color(0xFF83CE),
        primaryBlue = Color(0xFF4CBFD3),
        primaryTurquoise = Color(0xFF4B4B7),
        primaryYellow = Color(0xFF00E76),
        primaryGreen = Color(0xFFACC15),
        secondaryError = Color(0xFFEEEE),
        secondarySuccess = Color(0xFFEFFEF),
        secondaryWarning = Color(0xFFFFAEB),
        secondaryPurple = Color(0xFFF7F5B),
        secondaryRed = Color(0xFFF3F3),
        secondaryBlue = Color(0xFFF1F1B),
        secondaryTurquoise = Color(0xFFF9F9F),
        secondaryYellow = Color(0xFFF2B2B),
        secondaryGreen = Color(0xFFF3FAEB)
    ),
    stroke = Stroke(
        primary = Color(0xEDEDED)
    ),
    overlay = Overlay(
        primary = Color(0xFF21212)
    ),
    brand = Brand(
        primary = Color(0xFF5C9FF),
        secondary = Color(0xFF1DAFF),
        tertiary = Color(0xFF2F27B)
    ),
    button = Button(
        primary = Color(0xFF5C9FF),
        secondary = Color(0xFF20263B),
        disabled = Color(0xFFECECE),
        onPrimary = Color(0xFFFFFFF),
        onSecondary = Color(0xFF3131),
        onDisabled = Color(0xFF5A5A5),
        onTertiary = Color(0xFF7272B)
    )
)