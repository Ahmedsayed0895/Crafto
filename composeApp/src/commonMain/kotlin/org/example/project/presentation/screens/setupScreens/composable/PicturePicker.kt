package org.example.project.presentation.screens.setupscreens.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.camera
import crafto.composeapp.generated.resources.plus
import crafto.composeapp.generated.resources.x
import io.ktor.util.internal.OpDescriptor
import org.example.project.presentation.designsystem.colors.Shade
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.model.ImageData
import org.example.project.presentation.util.rememberImagePicker
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImagePicker(
    modifier: Modifier = Modifier,
    selectedImage: ImageData? = null,
    onImageSelected: (ImageData) -> Unit,
    onRemove: () -> Unit,
    shape: Shape? = null,
    imageSize: Dp? = null,
    contentDescriptor: String? = null,
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
        Box(contentAlignment=Alignment.TopEnd) {
            Box(
                modifier = Modifier
                    .then(if (imageSize != null) Modifier.size(imageSize) else Modifier.fillMaxSize())
                    .then(shape?.let { Modifier.clip(it) } ?: Modifier)
                    .background(AppTheme.craftoColors.background.card)
                    .border(
                        BorderStroke(
                            width = 1.dp,
                            color = AppTheme.craftoColors.shade.quinary
                        ),
                        shape = shape ?: RectangleShape
                    )
                    .clickable(enabled = enabled && !isUploading) {
                        imagePicker.launch()
                    },
                contentAlignment = Alignment.Center
            ) {
                if (selectedImage != null) {
                    AsyncImage(
                        model = selectedImage.byteArray,
                        contentDescription = contentDescriptor,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Column(
                        modifier.matchParentSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
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
            if (selectedImage != null) {
                IconButton(
                    onClick = onRemove,
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            AppTheme.craftoColors.background.card.copy(alpha = 0.8f),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(4.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.x),
                        contentDescription = "Remove Image",
                        tint = AppTheme.craftoColors.shade.primary
                    )
                }
            }
        }
    }
}