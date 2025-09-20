package org.example.project.domain.entity

data class CraftsmanPortfolio (
    val photoUrls: List<String> = emptyList(),
    val description: String = "",
    val photoPaths: List<String> = emptyList()
)

