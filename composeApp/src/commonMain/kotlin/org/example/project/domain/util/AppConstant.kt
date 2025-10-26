package org.example.project.domain.util

object AppConstants {
    object FileUpload {
        const val MAX_FILE_SIZE_MB = 4
        const val MAX_FILE_SIZE = MAX_FILE_SIZE_MB * 1024 * 1024 // 4MB

        val ALLOWED_IMAGE_TYPES = listOf("jpg", "jpeg", "png")
        const val MAX_PORTFOLIO_IMAGES = 4

        const val MIME_TYPE_JPEG = "image/jpeg"
        const val MIME_TYPE_PNG = "image/png"
    }

    object PersonalInfo {
        const val MIN_FIRST_NAME_LENGTH = 3
        const val MAX_FIRST_NAME_LENGTH = 50
        const val MIN_LAST_NAME_LENGTH = 3
        const val MAX_LAST_NAME_LENGTH = 50
        const val MIN_PHONE_LENGTH = 10
        const val MAX_PHONE_LENGTH = 15
        const val MIN_ADDRESS_LENGTH = 5
        const val MAX_ADDRESS_LENGTH = 50
        const val PHONE_REGEX = "^\\+?[1-9]\\d{1,14}$"
        const val EMAIL_REGEX =
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    }

    object Categories {
        const val MIN_CATEGORIES = 1
        const val MAX_CATEGORIES = 7
    }
}