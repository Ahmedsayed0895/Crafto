package org.example.project.presentation.screens.setup.composable.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.address
import crafto.composeapp.generated.resources.first_name
import crafto.composeapp.generated.resources.last_name
import crafto.composeapp.generated.resources.phone_number
import org.example.project.presentation.designsystem.components.TextField
import org.example.project.presentation.model.ImageData
import org.example.project.presentation.model.CraftsmanPersonalInfoUiModel
import org.example.project.presentation.screens.setup.composable.ImagePicker
import org.jetbrains.compose.resources.stringResource

@Composable
fun PersonalInfoPage(
    personalInfo: CraftsmanPersonalInfoUiModel,
    onPersonalInfoChanged: (CraftsmanPersonalInfoUiModel) -> Unit,
    profilePicture: ImageData? = null,
    onProfilePictureSelected: (ImageData) -> Unit,
    onRemove: () -> Unit,
    onImagePickerError: (String) -> Unit,
    isLoading: Boolean,
    isUploadingProfilePicture: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        ImagePicker(
            modifier = Modifier.fillMaxWidth(),
            selectedImage = profilePicture,
            onImageSelected = onProfilePictureSelected,
            onError = onImagePickerError,
            imageSize = 100.dp,
            shape = CircleShape,
            enabled = !isLoading,
            isUploading = isUploadingProfilePicture,
            onRemove = onRemove,
        )

        TextField(
            labelText = stringResource(Res.string.first_name),
            text = personalInfo.firstName,
            onTextChange = { onPersonalInfoChanged(personalInfo.copy(firstName = it)) },
            enabledState = !isLoading,
            inputKeyboard = KeyboardOptions(imeAction = ImeAction.Next)
        )
        TextField(
            labelText = stringResource(resource = Res.string.last_name),
            text = personalInfo.lastName,
            onTextChange = { onPersonalInfoChanged(personalInfo.copy(lastName = it)) },
            enabledState = !isLoading,
            inputKeyboard = KeyboardOptions(imeAction = ImeAction.Next)
        )
        TextField(
            text = personalInfo.phoneNumber,
            onTextChange = { onPersonalInfoChanged(personalInfo.copy(phoneNumber = it)) },
            labelText = stringResource(Res.string.phone_number),
            enabledState = !isLoading,
            inputKeyboard = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Phone)
        )
        TextField(
            text = personalInfo.address,
            onTextChange = { onPersonalInfoChanged(personalInfo.copy(address = it)) },
            labelText = stringResource(Res.string.address),
            maxLines = 3,
            enabledState = !isLoading,
        )
    }
}