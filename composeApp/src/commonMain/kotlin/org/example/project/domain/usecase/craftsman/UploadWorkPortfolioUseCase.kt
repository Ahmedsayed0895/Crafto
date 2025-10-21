package org.example.project.domain.usecase.craftsman

import org.example.project.domain.exception.ValidationException
import org.example.project.domain.model.WorkImage
import org.example.project.domain.repository.CraftsmanRepository


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

        if (workImages.size > org.example.project.util.AppConstants.FileUpload.MAX_PORTFOLIO_IMAGES) {
            throw ValidationException(
                "You can upload maximum ${org.example.project.util.AppConstants.FileUpload.MAX_PORTFOLIO_IMAGES} images"
            )
        }

        workImages.forEachIndexed { index, image ->
            if (image.data.isEmpty()) {
                throw ValidationException("Image ${index + 1} is empty")
            }

            if (image.data.size > org.example.project.util.AppConstants.FileUpload.MAX_FILE_SIZE) {
                throw ValidationException("Image ${index + 1} size must be less than 4MB")
            }

            val extension = image.fileName.substringAfterLast('.', "").lowercase()
            if (extension !in org.example.project.util.AppConstants.FileUpload.ALLOWED_IMAGE_TYPES) {
                throw ValidationException(
                    "Image ${index + 1} must be JPEG or PNG"
                )
            }
        }

        return repository.uploadWorkPortfolio(craftsmanId, workImages)
    }
}