package org.example.project.core.config

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage


object FirebaseConfig {
    val storage by lazy { Firebase.storage }
    val auth by lazy { Firebase.auth }

    // Storage references
    fun getCraftsmenStorageRef(): StorageReference = storage.reference.child("craftsmen")

    // Storage settings
    const val MAX_UPLOAD_SIZE_BYTES = 10 * 1024 * 1024L // 10MB
    const val MAX_IDENTITY_SIZE_BYTES = 5 * 1024 * 1024L // 5MB
    const val UPLOAD_TIMEOUT_MILLIS = 120_000L // 2 minutes
}