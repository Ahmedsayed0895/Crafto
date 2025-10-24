package org.example.project.domain.service

interface ValidationService {
    fun isValidPhoneNumber(phone: String): Boolean
    //fun isValidImageFile(fileName: String, data: ByteArray): ValidationResult
}

class ValidationServiceImpl : ValidationService {
    override fun isValidPhoneNumber(phone: String): Boolean {
        return phone.matches(Regex("^\\+?[1-9]\\d{1,14}$"))
    }

//    override fun isValidImageFile(
//        fileName: String,
//        data: ByteArray
//    ): ValidationResult {
//        TODO("Not yet implemented")
//    }
}