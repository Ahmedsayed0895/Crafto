package org.example.project.presentation.screens.setup.composable.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.id_card_image
import crafto.composeapp.generated.resources.personal_info
import crafto.composeapp.generated.resources.skip_for_now
import crafto.composeapp.generated.resources.upload_back_of_national_id
import crafto.composeapp.generated.resources.upload_front_of_national_id
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.model.ImageData
import org.example.project.presentation.screens.setup.composable.ImagePicker
import org.jetbrains.compose.resources.stringResource

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
            title = stringResource(Res.string.upload_front_of_national_id),
            image = idCardFront,
            onSelect = { img -> onIdCardSelected(true, img) },
            onError = onErrorMessage,
            onRemove = onFrontImageRemoved
        )

        UploadBox(
            modifier = Modifier.weight(1f),
            title = stringResource(Res.string.upload_back_of_national_id),
            image = idCardBack,
            onSelect = { img -> onIdCardSelected(false, img) },
            onError = onErrorMessage,
            onRemove = onBackImageRemoved
        )

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onSkip
        ) { Text(stringResource(Res.string.skip_for_now)) }
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
            contentDescriptor = stringResource(Res.string.id_card_image),
        )
    }
}