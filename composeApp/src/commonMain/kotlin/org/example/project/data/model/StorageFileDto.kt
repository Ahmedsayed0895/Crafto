package org.example.project.data.model

import kotlinx.serialization.Serializable

@Serializable
data class StorageFileDto(
    val name: String,
    val path: String,
    val url: String,
    val size: Long? = null,
    val contentType: String? = null
)
