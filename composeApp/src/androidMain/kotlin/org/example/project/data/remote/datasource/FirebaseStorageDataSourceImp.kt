package org.example.project.data.remote.datasource

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await
import org.example.project.data.datasource.remote.StorageRemoteDataSource
import androidx.core.net.toUri
import org.example.project.data.model.StorageFileDto

class FirebaseStorageDataSource : StorageRemoteDataSource {

    private val storage = Firebase.storage
    private val auth = Firebase.auth

    companion object {
        private const val MAX_UPLOAD_SIZE_BYTES = 10 * 1024 * 1024L // 10MB
    }

    override suspend fun uploadFile(localPath: String, remotePath: String): String {
        return try {
            val file = localPath.toUri()
            val storageRef = storage.reference.child(remotePath)

            val metadata = StorageMetadata.Builder()
                .setContentType(getContentType(localPath))
                .setCustomMetadata("uploadedAt", System.currentTimeMillis().toString())
                .setCustomMetadata("uploadedBy", auth.currentUser?.uid ?: "anonymous")
                .build()

            storageRef.putFile(file, metadata).await()
            storageRef.downloadUrl.await().toString()
        } catch (e: Exception) {
            throw StorageException("Failed to upload file: ${e.message}", e)
        }
    }

    override suspend fun deleteFile(remotePath: String): Boolean {
        return try {
            storage.reference.child(remotePath).delete().await()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getDownloadUrl(remotePath: String): String? {
        return try {
            storage.reference.child(remotePath).downloadUrl.await().toString()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun listFiles(folderPath: String): List<StorageFileDto> {
        return try {
            val listResult = storage.reference.child(folderPath).listAll().await()

            listResult.items.map { ref ->
                val metadata = ref.metadata.await()
                StorageFileDto(
                    name = ref.name,
                    path = ref.path,
                    url = ref.downloadUrl.await().toString(),
                    size = metadata.sizeBytes,
                    contentType = metadata.contentType
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun getContentType(path: String): String {
        return when (path.substringAfterLast('.').lowercase()) {
            "jpg", "jpeg" -> "image/jpeg"
            "png" -> "image/png"
            "webp" -> "image/webp"
            else -> "application/octet-stream"
        }
    }
}

class StorageException(message: String, cause: Throwable? = null) : Exception(message, cause)