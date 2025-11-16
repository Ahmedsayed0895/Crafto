package org.example.project.domain.entity

data class UserSession(
    val userId: String?,
    val userType: UserType?,
    val isFirstTime: Boolean
)
