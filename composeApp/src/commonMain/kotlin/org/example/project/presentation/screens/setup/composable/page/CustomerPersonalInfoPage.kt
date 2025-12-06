package org.example.project.presentation.screens.setup.composable.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.first_name
import crafto.composeapp.generated.resources.phone_hint
import org.example.project.presentation.designsystem.components.TextField
import org.example.project.presentation.model.CustomerPersonalInfoUiModel
import org.example.project.presentation.model.ImageData
import org.example.project.presentation.screens.setup.composable.ProfilePictureSelector
import org.jetbrains.compose.resources.stringResource

@Composable
fun CustomerPersonalInfoPage(
    personalInfo: CustomerPersonalInfoUiModel,
    profilePicture: ImageData?,
    onPersonalInfoChanged: (CustomerPersonalInfoUiModel) -> Unit,
    onProfilePictureSelected: (ImageData) -> Unit,
    onProfilePictureRemoved: () -> Unit,
    onImagePickerError: (String) -> Unit,
    isLoading: Boolean,
    isUploadingProfilePicture: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        ProfilePictureSelector(
            selectedImage = profilePicture,
            onImageSelected = onProfilePictureSelected,
            //onRemove = onProfilePictureRemoved,
            onError = onImagePickerError,
            isUploading = isUploadingProfilePicture,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))


        TextField(
            text = personalInfo.name,
            onTextChange = { newName ->
                onPersonalInfoChanged(personalInfo.copy(name = newName))
            },
            hint = stringResource(Res.string.first_name),
            enabledState = !isLoading,
            modifier = Modifier.fillMaxWidth()
        )

        // Phone Number Input
        TextField(
            text = personalInfo.phoneNumber,
            onTextChange = { newPhone ->
                onPersonalInfoChanged(personalInfo.copy(phoneNumber = newPhone))
            },
            hint = stringResource(Res.string.phone_hint),
            enabledState = !isLoading,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}