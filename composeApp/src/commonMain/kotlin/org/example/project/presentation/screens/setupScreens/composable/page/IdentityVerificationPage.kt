package org.example.project.presentation.screens.setupscreens.composable.page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.model.ImageData
import org.example.project.presentation.screens.setupscreens.composable.ImagePicker
import org.example.project.presentation.util.rememberImagePicker
import org.jetbrains.compose.resources.painterResource

@Composable
fun IdentityVerificationPage(
    idCardFront: ImageData?,
    idCardBack: ImageData?,
    onIdCardSelected: (isFront: Boolean, imageData: ImageData) -> Unit,
    onFrontImageRemoved: () -> Unit,
    onBackImageRemoved: () -> Unit,
    onSkip: () -> Unit,
    onErrorMessage: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UploadBox(
            modifier = Modifier.weight(1f),
            title = "Upload Front of National ID",
            image = idCardFront,
            onSelect = { img -> onIdCardSelected(true, img) },
            onError = onErrorMessage,
            onRemove = onFrontImageRemoved
        )

        UploadBox(
            modifier = Modifier.weight(1f),
            title = "Upload Back of National ID",
            image = idCardBack,
            onSelect = { img -> onIdCardSelected(false, img) },
            onError = onErrorMessage,
            onRemove = onBackImageRemoved
        )

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onSkip
        ) { Text("I'll Verify Later") }
    }
}

@Composable
private fun UploadBox(
    modifier: Modifier=Modifier,
    title: String,
    image: ImageData?,
    onSelect: (ImageData) -> Unit,
    onError: (String) -> Unit,
    onRemove: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(title, style = AppTheme.textStyle.body.smallMedium)

        ImagePicker(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            onImageSelected = onSelect,
            onError = onError,
            selectedImage = image,
            onRemove = onRemove,
            contentDescriptor = "ID Card Image",
        )
    }
}