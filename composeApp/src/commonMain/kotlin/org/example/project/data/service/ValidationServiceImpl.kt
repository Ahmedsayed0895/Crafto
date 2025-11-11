package org.example.project.data.service

import org.example.project.domain.service.ValidationService
import org.example.project.domain.util.AppConstants

class ValidationServiceImpl : ValidationService {
    override fun isValidPhoneNumber(phone: String): Boolean {
        return phone.matches(Regex(AppConstants.PersonalInfo.PHONE_REGEX))
    }

    override fun isValidEmail(email: String): Boolean {
        return email.matches(Regex(AppConstants.PersonalInfo.EMAIL_REGEX))
    }

    override fun isValidImageFileName(fileName: String): Boolean {
        val extension = fileName.substringAfterLast('.', "").lowercase()
        return extension in AppConstants.FileUpload.ALLOWED_IMAGE_TYPES
    }

    override fun isValidFileSize(sizeInBytes: Int): Boolean {
        return sizeInBytes <= AppConstants.FileUpload.MAX_FILE_SIZE
    }
}