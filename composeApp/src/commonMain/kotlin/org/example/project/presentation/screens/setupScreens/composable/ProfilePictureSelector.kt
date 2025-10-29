package org.example.project.presentation.screens.setupscreens.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
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
import org.example.project.presentation.util.rememberImagePicker
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProfilePictureSelector(
    modifier: Modifier = Modifier,
    selectedImage: ImageData? = null,
    onImageSelected: (ImageData) -> Unit,
    onError: (String) -> Unit,
    enabled: Boolean = true,
    isUploading: Boolean = false
) {
    val imagePicker = rememberImagePicker(
        singleSelection = true,
        onImagesSelected = { images ->
            images.firstOrNull()?.let { imageData ->
                onImageSelected(imageData)
            }
        },
        onError = { errorMessage ->
            onError(errorMessage)
        }
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(AppTheme.craftoColors.background.card)
                .border(
                    BorderStroke(
                        width = 1.dp,
                        color = AppTheme.craftoColors.shade.quinary

                    ),
                    CircleShape
                )
                .clickable(enabled = enabled && !isUploading) {
                    imagePicker.launch()
                },
            contentAlignment = Alignment.Center
        ) {
            if (selectedImage != null) {
                AsyncImage(
                    model = selectedImage.byteArray,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(AppTheme.craftoColors.background.card)
                        .align(Alignment.BottomEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.plus),
                        contentDescription = "Change Photo",
                        tint = AppTheme.craftoColors.button.onPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.camera),
                        contentDescription = "Add Profile Picture",
                        tint = AppTheme.craftoColors.shade.secondary,
                        modifier = Modifier.size(32.dp)
                    )

                    Text(
                        text = "Tap here",
                        style = AppTheme.textStyle.body.smallMedium,
                        color = AppTheme.craftoColors.shade.secondary
                    )
                }
            }
        }
    }
}