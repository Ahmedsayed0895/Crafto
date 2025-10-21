package org.example.project.util

object AppConstants {
    // File Upload
    object FileUpload {
        const val MAX_FILE_SIZE_MB = 4
        const val MAX_FILE_SIZE = MAX_FILE_SIZE_MB * 1024 * 1024 // 4MB

        val ALLOWED_IMAGE_TYPES = listOf("jpg", "jpeg", "png")
        const val MAX_PORTFOLIO_IMAGES = 4

        const val MIME_TYPE_JPEG = "image/jpeg"
        const val MIME_TYPE_PNG = "image/png"
    }
}