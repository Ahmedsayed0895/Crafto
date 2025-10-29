package org.example.project.presentation.screens.setupscreens.composable.page

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
import org.example.project.presentation.designsystem.components.TextField
import org.example.project.presentation.model.ImageData
import org.example.project.presentation.model.PersonalInfoUiModel
import org.example.project.presentation.screens.setupscreens.composable.ImagePicker

@Composable
fun PersonalInfoPage(
    personalInfo: PersonalInfoUiModel,
    onPersonalInfoChanged: (PersonalInfoUiModel) -> Unit,
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
            labelText = "First Name",
            text = personalInfo.firstName,
            onTextChange = { onPersonalInfoChanged(personalInfo.copy(firstName = it)) },
            enabledState = !isLoading,
            inputKeyboard = KeyboardOptions(imeAction = ImeAction.Next)
        )
        TextField(
            labelText = "Last Name",
            text = personalInfo.lastName,
            onTextChange = { onPersonalInfoChanged(personalInfo.copy(lastName = it)) },
            enabledState = !isLoading,
            inputKeyboard = KeyboardOptions(imeAction = ImeAction.Next)
        )
        TextField(
            text = personalInfo.phoneNumber,
            onTextChange = { onPersonalInfoChanged(personalInfo.copy(phoneNumber = it)) },
            labelText = "Phone Number",
            enabledState = !isLoading,
            inputKeyboard = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Phone)
        )
        TextField(
            text = personalInfo.address,
            onTextChange = { onPersonalInfoChanged(personalInfo.copy(address = it)) },
            labelText = "Address",
            maxLines = 3,
            enabledState = !isLoading,
        )
    }
}