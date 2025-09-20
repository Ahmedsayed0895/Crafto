package org.example.project.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.logo
import crafto.composeapp.generated.resources.logo_icon
import kotlinx.coroutines.delay
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SplashScreen(
    onTimeout: () -> Unit
) {

    LaunchedEffect(Unit) {
        delay(2000)
        onTimeout()
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = AppTheme.craftoColors.background.screen),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.background(
                AppTheme.craftoColors.brand.primary, shape = RoundedCornerShape(
                    AppTheme.craftoRadius.full
                )
            ).size(100.dp)

        ) {
            Icon(
                painter = painterResource(Res.drawable.logo),
                contentDescription = stringResource(Res.string.logo_icon),
                modifier = Modifier.align(Alignment.Center).offset(x = (-9).dp, y = (10).dp),
                tint = AppTheme.craftoColors.background.screen
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        onTimeout = {}
    )
}