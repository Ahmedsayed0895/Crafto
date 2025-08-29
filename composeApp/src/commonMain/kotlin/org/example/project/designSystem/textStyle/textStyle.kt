package org.example.project.designSystem.textStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


val defaultTextStyle = CraftoTextStyle(
    title = TitleTextStyle(
        xLarge = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        large = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
        ),
        medium = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
        ),
        small = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        ),
    ),
    body = BodyTextStyle(
        largeRegular = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
        ),
        largeMedium = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        ),
        largeSemiBold = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        mediumRegular = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
        ),
        medium = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
        ),
        mediumSemiBold = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        smallRegular = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
        ),
        smallMedium = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        ),
        smallSemiBold = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
        ),
    ),
    label = LabelTextStyle(
        medium = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        ),
        mediumRegular = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
        ),
        mediumSemiBold = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
        ),
    ),
    display = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
    ),
)