package org.example.project.domain.usecase.craftsman

import org.example.project.domain.exception.ValidationException
import org.example.project.domain.repository.CraftsmanRepository
import org.example.project.domain.service.ValidationService
import org.example.project.domain.util.AppConstants

class UploadCraftsmanProfilePictureUseCase(
    private val repository: CraftsmanRepository,
    private val validationService: ValidationService
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

        if (!validationService.isValidFileSize(profilePicture.size)) {
            throw ValidationException(
                "Profile picture size must be less than ${AppConstants.FileUpload.MAX_FILE_SIZE_MB}MB"
            )
        }

        if (!validationService.isValidImageFileName(profilePictureFileName)) {
            throw ValidationException("Profile picture must be one of: ${AppConstants.FileUpload.ALLOWED_IMAGE_TYPES.joinToString(", ")}")
        }

        return repository.uploadProfilePicture(
            craftsmanId,
            profilePicture,
            profilePictureFileName
        )
    }
}