package org.example.project.data.remote.network

object ApiConstants {
    const val BASE_URL = "http://192.168.1.52:8085"

    // Headers
    object Headers {
        const val USER_ID = "userId"
        const val AUTH_TOKEN = "Authorization"
        const val CONTENT_TYPE = "Content-Type"
        const val ACCEPT = "Accept"
    }

    // Timeouts (in milliseconds)
    object Timeouts {
        const val REQUEST = 30_000L // 30 seconds
        const val CONNECT = 10_000L // 10 seconds
        const val SOCKET = 30_000L  // 30 seconds
    }

    // File Upload
    object FileUpload {
        const val MAX_FILE_SIZE = 4 * 1024 * 1024 // 4MB
        val ALLOWED_IMAGE_TYPES = listOf("jpg", "jpeg", "png")
        const val MAX_PORTFOLIO_IMAGES = 4
    }

    // API Endpoints
    object Endpoints {
        // Craftsman endpoints
        const val CRAFTSMAN_SETUP = "/craftsman/setup"
        const val CRAFTSMAN_PROFILE = "/craftsman/profile"

        fun craftsmanIdCards(craftsmanId: String) = "/craftsman/$craftsmanId/verify/id-cards"
        fun craftsmanWorkPortfolio(craftsmanId: String) = "/craftsman/$craftsmanId/verify/work-portfolio"
        fun craftsmanStatus(craftsmanId: String) = "/craftsman/$craftsmanId/status"
        fun deleteCraftsman(craftsmanId: String) = "/craftsman/$craftsmanId"

    }

}