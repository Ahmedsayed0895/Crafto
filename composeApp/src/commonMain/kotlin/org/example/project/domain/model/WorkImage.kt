package org.example.project.domain.model

data class WorkImage(
    val data: ByteArray,
    val fileName: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as WorkImage

        if (!data.contentEquals(other.data)) return false
        if (fileName != other.fileName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = data.contentHashCode()
        result = 31 * result + fileName.hashCode()
        return result
    }
}