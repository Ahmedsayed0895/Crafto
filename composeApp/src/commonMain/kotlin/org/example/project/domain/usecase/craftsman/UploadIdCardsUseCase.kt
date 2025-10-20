package org.example.project.domain.usecase.craftsman

import org.example.project.domain.entity.VerificationDocuments
import org.example.project.domain.exception.ValidationException
import org.example.project.domain.repository.CraftsmanRepository



class UploadIdCardsUseCase(
    private val repository: CraftsmanRepository
) {
    suspend operator fun invoke(
        craftsmanId: String,
        idCardFront: ByteArray,
        idCardFrontFileName: String,
        idCardBack: ByteArray,
        idCardBackFileName: String
    ): VerificationDocuments {
        // Validate craftsman ID
        if (craftsmanId.isBlank()) {
            throw ValidationException("Craftsman ID is required")
        }

        // Validate file sizes
        if (idCardFront.isEmpty()) {
            throw ValidationException("Please select front ID card image")
        }

        if (idCardBack.isEmpty()) {
            throw ValidationException("Please select back ID card image")
        }

        if (idCardFront.size > org.example.project.util.ApiConstants.FileUpload.MAX_FILE_SIZE) {
            throw ValidationException("Front ID card image size must be less than 4MB")
        }

        if (idCardBack.size > org.example.project.util.ApiConstants.FileUpload.MAX_FILE_SIZE) {
            throw ValidationException("Back ID card image size must be less than 4MB")
        }

        // Validate file names (must have extensions)
        if (!idCardFrontFileName.contains(".")) {
            throw ValidationException("Invalid front ID card file name")
        }

        if (!idCardBackFileName.contains(".")) {
            throw ValidationException("Invalid back ID card file name")
        }

        // Validate file types
        val frontExtension = idCardFrontFileName.substringAfterLast('.', "").lowercase()
        val backExtension = idCardBackFileName.substringAfterLast('.', "").lowercase()

        if (frontExtension !in org.example.project.util.ApiConstants.FileUpload.ALLOWED_IMAGE_TYPES) {
            throw ValidationException(
                "Front ID card must be one of: ${org.example.project.util.ApiConstants.FileUpload.ALLOWED_IMAGE_TYPES.joinToString(", ")}"
            )
        }

        if (backExtension !in org.example.project.util.ApiConstants.FileUpload.ALLOWED_IMAGE_TYPES) {
            throw ValidationException(
                "Back ID card must be one of: ${org.example.project.util.ApiConstants.FileUpload.ALLOWED_IMAGE_TYPES.joinToString(", ")}"
            )
        }

        // All validation passed - call repository
        // No error handling - let exceptions propagate
        return repository.uploadIdCards(
            craftsmanId = craftsmanId,
            idCardFront = idCardFront,
            idCardFrontFileName = idCardFrontFileName,
            idCardBack = idCardBack,
            idCardBackFileName = idCardBackFileName
        )
    }
}