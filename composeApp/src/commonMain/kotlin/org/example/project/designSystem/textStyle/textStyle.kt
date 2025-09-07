package org.example.project.designSystem.textStyle

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.plus_jakarta_sans
import crafto.composeapp.generated.resources.plus_jakarta_sans_bold
import crafto.composeapp.generated.resources.plus_jakarta_sans_medium
import crafto.composeapp.generated.resources.plus_jakarta_sans_semibold
import org.jetbrains.compose.resources.Font

@Composable
fun fontResources(): FontFamily {
    return FontFamily(
        Font(Res.font.plus_jakarta_sans, FontWeight.Normal),
        Font(Res.font.plus_jakarta_sans_medium, FontWeight.Medium),
        Font(Res.font.plus_jakarta_sans_semibold, FontWeight.SemiBold),
        Font(Res.font.plus_jakarta_sans_bold, FontWeight.Bold)
    )
}

@Composable
fun defaultTextStyle(): CraftoTextStyle{
  return  CraftoTextStyle(
        title = TitleTextStyle(
            xLarge = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontResources()
            ),
            large = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = fontResources()
            ),
            medium = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = fontResources()
            ),
            small = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = fontResources()
            ),
        ),
        body = BodyTextStyle(
            largeRegular = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontResources()
            ),
            largeMedium = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = fontResources()
            ),
            largeSemiBold = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontResources()
            ),
            mediumRegular = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontResources()
            ),
            medium = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = fontResources()
            ),
            mediumSemiBold = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontResources()
            ),
            smallRegular = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontResources()
            ),
            smallMedium = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = fontResources()
            ),
            smallSemiBold = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontResources()
            ),
        ),
        label = LabelTextStyle(
            medium = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = fontResources()
            ),
            mediumRegular = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontResources()
            ),
            mediumSemiBold = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontResources()
            ),
        ),
        display = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontResources()
        ),
    )
}
