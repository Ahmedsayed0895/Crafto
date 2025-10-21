package org.example.project.presentation.screens.setupScreens.composable.page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mohamedrejeb.calf.core.LocalPlatformContext
import com.mohamedrejeb.calf.io.getName
import com.mohamedrejeb.calf.io.readByteArray
import com.mohamedrejeb.calf.picker.FilePickerFileType
import com.mohamedrejeb.calf.picker.FilePickerSelectionMode
import com.mohamedrejeb.calf.picker.rememberFilePickerLauncher
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.camera
import crafto.composeapp.generated.resources.plus
import crafto.composeapp.generated.resources.x
import kotlinx.coroutines.launch
import org.example.project.presentation.designsystem.components.TextField
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.model.ImageData
import org.example.project.presentation.util.rememberImagePicker
import org.jetbrains.compose.resources.painterResource
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun PortfolioUploadPage(
    images: List<ImageData>,
    canAddMore: Boolean,
    onAddPhotosClicked: (List<ImageData>) -> Unit,
    onImageRemoved: (Int) -> Unit,
    workDescription: String,
    onDescriptionChanged: (String) -> Unit
) {
    val scope = rememberCoroutineScope()
    val context = LocalPlatformContext.current

    val imagePicker = rememberImagePicker(
        singleSelection = false,
        onImagesSelected = { newImages ->
            onAddPhotosClicked(newImages)
        },
        onError = { errorMessage ->
            // Handle error - show snackbar/toast
        }
    )

//    val imagePicker = rememberFilePickerLauncher(
//        type = FilePickerFileType.Image,
//        selectionMode = FilePickerSelectionMode.Multiple,
//    ) { files ->
//        scope.launch {
//            val imageDataList = files.take(4 - images.size).mapNotNull { file ->
//                try {
//                    val fileName = file.getName(context) ?: "image_${Clock.System.now()}.jpg"
//                    val byteArray = file.readByteArray(context)
//
//                    ImageData(
//                        uri = fileName,
//                        byteArray = byteArray,
//                        fileName = fileName
//                    )
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    null
//                }
//            }
//            if (imageDataList.isNotEmpty()) {
//                onAddPhotosClicked(imageDataList)
//            }
//        }
//    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Either show the empty placeholder or thumbnails + add button
        if (images.isEmpty()) {
            EmptyPortfolioBox(
                onAddPhotosClicked={ imagePicker.launch() }
            )
        } else {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                images.forEachIndexed { index, image ->
                    PortfolioThumbnail(image) {
                        onImageRemoved(index)
                    }
                }
                if (canAddMore) {
                    AddPhotoBox(
                        onAddPhotosClicked= { imagePicker.launch() }
                    )
                }
            }
        }

        TextField(
            labelText = "Describe Your Work (Optional)",
            text = workDescription,
            onTextChange = onDescriptionChanged,
            hint = "You can mention your years of experience, tools you use, or types of jobs you usually handle.",
            modifier = Modifier.fillMaxWidth(),
            maxLines = 4
        )
    }
}

// Shown when no images yet
@Composable
private fun EmptyPortfolioBox(onAddPhotosClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2f)
            .background(
                AppTheme.craftoColors.background.card,
                RoundedCornerShape(12.dp)
            )
            .clickable { onAddPhotosClicked() },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(Res.drawable.camera),
                contentDescription = "Add Photos",
                tint = AppTheme.craftoColors.shade.secondary,
                modifier = Modifier.size(36.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Tap to add photos",
                style = AppTheme.textStyle.body.smallMedium,
                color = AppTheme.craftoColors.shade.secondary
            )
        }
    }
}

// Thumbnail with remove button
@Composable
private fun PortfolioThumbnail(
    imageData: ImageData,
    onRemove: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.TopEnd
    ) {
        AsyncImage(
            model = imageData.byteArray,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        IconButton(
            onClick = onRemove,
            modifier = Modifier
                .size(24.dp)
                .background(
                    AppTheme.craftoColors.background.card.copy(alpha = 0.6f),
                    RoundedCornerShape(8.dp)
                )
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.x),
                contentDescription = "Remove",
                tint = AppTheme.craftoColors.shade.primary
            )
        }
    }
}

// Add (+) box shown beside thumbnails
@Composable
private fun AddPhotoBox(onAddPhotosClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                AppTheme.craftoColors.background.card,
                RoundedCornerShape(12.dp)
            )
            .clickable { onAddPhotosClicked() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(Res.drawable.plus),
            contentDescription = "Add",
            tint = AppTheme.craftoColors.shade.secondary,
            modifier = Modifier.size(21.dp)
        )
    }
}