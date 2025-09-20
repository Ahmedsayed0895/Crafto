package org.example.project.data.datasource.remote

import org.example.project.data.model.StorageFileDto

interface StorageRemoteDataSource {
    suspend fun uploadFile(localPath: String, remotePath: String): String
    suspend fun deleteFile(remotePath: String): Boolean
    suspend fun getDownloadUrl(remotePath: String): String?
    suspend fun listFiles(folderPath: String): List<StorageFileDto>
}