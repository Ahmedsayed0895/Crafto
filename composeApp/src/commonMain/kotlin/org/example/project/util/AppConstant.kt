package org.example.project.util

object ApiConstants {
    // File Upload
    object FileUpload {
        const val MAX_FILE_SIZE = 4 * 1024 * 1024 // 4MB
        val ALLOWED_IMAGE_TYPES = listOf("jpg", "jpeg", "png")
        const val MAX_PORTFOLIO_IMAGES = 4
    }
}