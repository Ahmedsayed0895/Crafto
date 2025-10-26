package org.example.project.domain.service

interface ValidationService {
    fun isValidPhoneNumber(phone: String): Boolean
    fun isValidEmail(email: String): Boolean
    fun isValidImageFileName(fileName: String): Boolean
    fun isValidFileSize(sizeInBytes: Int): Boolean
}