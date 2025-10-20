package org.example.project.presentation.screens.setupScreens.composable.page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.camera
import crafto.composeapp.generated.resources.plus
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.model.ImageData
import org.jetbrains.compose.resources.painterResource

@Composable
fun IdentityVerificationPage(
    idCardFront: ImageData?,
    idCardBack: ImageData?,
    onIdCardSelected: (isFront: Boolean, imageData: ImageData) -> Unit,
    onUploadClick: () -> Unit,
    onSkip: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UploadBox(
            title = "Upload Front of National ID",
            image = idCardFront,
            onSelect = { img -> onIdCardSelected(true, img) }
        )

        UploadBox(
            title = "Upload Back of National ID",
            image = idCardBack,
            onSelect = { img -> onIdCardSelected(false, img) }
        )

        Spacer(modifier = Modifier.weight(1f))

            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onSkip
            ) { Text("I'll Verify Later") }
    }
}

@Composable
private fun UploadBox(
    title: String,
    image: ImageData?,
    onSelect: (ImageData) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(title, style = AppTheme.textStyle.body.smallMedium)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    color = AppTheme.craftoColors.background.card,
                    RoundedCornerShape(12.dp)
                )
                .clickable {
                    // TODO: implement image picker
                },
            contentAlignment = Alignment.Center
        ) {
            if (image == null)
                Icon(painterResource(Res.drawable.camera), contentDescription = "upload image")
            else {
                AsyncImage(
                    model = image.uri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(12.dp))
                )
            }
        }
    }
}