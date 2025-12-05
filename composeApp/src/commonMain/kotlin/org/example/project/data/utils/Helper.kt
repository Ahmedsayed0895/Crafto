package org.example.project.data.utils

fun getMimeType(fileName: String): String {
    return when (fileName.substringAfterLast('.', "").lowercase()) {
        "png" -> "image/png"
        "jpg", "jpeg" -> "image/jpeg"
        else -> "image/jpeg" // Default to JPEG
    }
}