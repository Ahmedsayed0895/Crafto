package org.example.project.presentation.model

data class ImageData(
    val uri: String,
    val fileName: String,
    val byteArray: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as ImageData
        return uri == other.uri && fileName == other.fileName
    }

    override fun hashCode(): Int {
        var result = uri.hashCode()
        result = 31 * result + fileName.hashCode()
        return result
    }
}
