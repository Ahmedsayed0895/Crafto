package org.example.project.data.remote.network

object ApiConstants {
    const val BASE_URL = "http://192.168.1.51:8085"

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

    // API Endpoints
    object Endpoints {
        const val CRAFTSMAN_SETUP = "/craftsman/setup"
        const val CRAFTSMAN_PROFILE = "/craftsman/profile"
        const val ONBOARDING_END_POINT = "/onboarding"
        const val CUSTOMER_SETUP = "/customer/setup"
        const val CUSTOMER_PROFILE = "/customer/profile"


        fun craftsmanProfilePicture(craftsmanId: String) = "/craftsman/$craftsmanId/profile-picture"
        fun customerProfilePicture(customerId: String) = "/customer/$customerId/profile-picture"
        fun craftsmanIdCards(craftsmanId: String) = "/craftsman/$craftsmanId/verify/id-cards"
        fun craftsmanWorkPortfolio(craftsmanId: String) = "/craftsman/$craftsmanId/verify/work-portfolio"
        fun craftsmanStatus(craftsmanId: String) = "/craftsman/$craftsmanId/status"
        fun deleteCraftsman(craftsmanId: String) = "/craftsman/$craftsmanId"
        fun deleteCustomer(customerId: String) = "/customer/$customerId"
    }

}