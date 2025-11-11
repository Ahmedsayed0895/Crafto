package org.example.project.domain.usecase.craftsman

import org.example.project.domain.exception.ValidationException
import org.example.project.domain.model.WorkImage
import org.example.project.domain.repository.CraftsmanRepository
import org.example.project.domain.util.AppConstants


class UploadWorkPortfolioUseCase(
    private val repository: CraftsmanRepository
) {
    suspend operator fun invoke(
        craftsmanId: String,
        workImages: List<WorkImage>
    ): List<String> {
        if (craftsmanId.isBlank()) {
            throw ValidationException("Craftsman ID is required")
        }

        if (workImages.isEmpty()) {
            throw ValidationException("Please select at least one work image")
        }

        if (workImages.size > AppConstants.FileUpload.MAX_PORTFOLIO_IMAGES) {
            throw ValidationException(
                "You can upload maximum ${AppConstants.FileUpload.MAX_PORTFOLIO_IMAGES} images"
            )
        }

        workImages.forEachIndexed { index, image ->
            if (image.data.isEmpty()) {
                throw ValidationException("Image ${index + 1} is empty")
            }

            if (image.data.size > AppConstants.FileUpload.MAX_FILE_SIZE) {
                throw ValidationException("Image ${index + 1} size must be less than 4MB")
            }

            val extension = image.fileName.substringAfterLast('.', "").lowercase()
            if (extension !in AppConstants.FileUpload.ALLOWED_IMAGE_TYPES) {
                throw ValidationException(
                    "Image ${index + 1} must be JPEG or PNG"
                )
            }
        }

        return repository.uploadWorkPortfolio(craftsmanId, workImages)
    }
}