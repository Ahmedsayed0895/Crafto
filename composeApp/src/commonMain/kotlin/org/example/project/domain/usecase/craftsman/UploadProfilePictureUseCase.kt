package org.example.project.domain.usecase.craftsman

import org.example.project.domain.exception.ValidationException
import org.example.project.domain.repository.CraftsmanRepository
import org.example.project.util.AppConstants

class UploadProfilePictureUseCase(
    private val repository: CraftsmanRepository
) {
    suspend operator fun invoke(
        craftsmanId: String,
        profilePicture: ByteArray,
        profilePictureFileName: String
    ): String {
        if (craftsmanId.isBlank()) {
            throw ValidationException("Craftsman ID is required")
        }

        if (profilePicture.isEmpty()) {
            throw ValidationException("Profile picture is required")
        }

        if (profilePicture.size > AppConstants.FileUpload.MAX_FILE_SIZE) {
            throw ValidationException(
                "Profile picture size must be less than ${AppConstants.FileUpload.MAX_FILE_SIZE_MB}MB"
            )
        }

        val extension = profilePictureFileName.substringAfterLast('.', "").lowercase()
        if (extension !in AppConstants.FileUpload.ALLOWED_IMAGE_TYPES) {
            throw ValidationException("Profile picture must be JPG or PNG")
        }

        return repository.uploadProfilePicture(
            craftsmanId,
            profilePicture,
            profilePictureFileName
        )
    }
}