package org.example.project.domain.usecase.craftsman

import org.example.project.domain.entity.VerificationDocuments
import org.example.project.domain.exception.ValidationException
import org.example.project.domain.repository.CraftsmanRepository
import org.example.project.domain.service.ValidationService
import org.example.project.domain.util.AppConstants


class UploadIdCardsUseCase(
    private val repository: CraftsmanRepository,
    private val validationService: ValidationService
) {
    suspend operator fun invoke(
        craftsmanId: String,
        idCardFront: ByteArray,
        idCardFrontFileName: String,
        idCardBack: ByteArray,
        idCardBackFileName: String
    ): VerificationDocuments {
        if (craftsmanId.isBlank()) {
            throw ValidationException("Craftsman ID is required")
        }

        if (idCardFront.isEmpty()) {
            throw ValidationException("Please select front ID card image")
        }

        if (idCardBack.isEmpty()) {
            throw ValidationException("Please select back ID card image")
        }

        if (!validationService.isValidFileSize(idCardFront.size)) {
            throw ValidationException("Front ID card image size must be less than ${AppConstants.FileUpload.MAX_FILE_SIZE_MB} MB")
        }

        if (!validationService.isValidFileSize(idCardBack.size)) {
            throw ValidationException("Back ID card image size must be less than 4MB")
        }

        if (!idCardFrontFileName.contains(".")) {
            throw ValidationException("Invalid front ID card file name")
        }

        if (!idCardBackFileName.contains(".")) {
            throw ValidationException("Invalid back ID card file name")
        }

        if (!validationService.isValidImageFileName(idCardFrontFileName)) {
            throw ValidationException(
                "Front ID card must be one of: ${AppConstants.FileUpload.ALLOWED_IMAGE_TYPES.joinToString(", ")}"
            )
        }

        if (!validationService.isValidImageFileName(idCardBackFileName)) {
            throw ValidationException(
                "Back ID card must be one of: ${AppConstants.FileUpload.ALLOWED_IMAGE_TYPES.joinToString(", ")}"
            )
        }

        return repository.uploadIdCards(
            craftsmanId = craftsmanId,
            idCardFront = idCardFront,
            idCardFrontFileName = idCardFrontFileName,
            idCardBack = idCardBack,
            idCardBackFileName = idCardBackFileName
        )
    }
}