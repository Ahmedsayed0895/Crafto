package org.example.project.data.remote.datasource

import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import org.example.project.data.datasource.remote.CraftsmanRemoteDataSource
import org.example.project.data.remote.dto.CraftsmanProfileResponseDto
import org.example.project.data.remote.dto.CraftsmanSetupResponseDto
import org.example.project.data.remote.dto.CraftsmanStatusResponseDto
import org.example.project.data.remote.dto.CreateCraftsmanRequest
import org.example.project.data.remote.dto.DeleteAccountResponseDto
import org.example.project.data.remote.dto.IdCardUploadResponseDto
import org.example.project.data.remote.dto.WorkPortfolioResponseDto
import org.example.project.data.remote.network.ApiConstants
import org.example.project.data.remote.network.ApiConstants.Headers.USER_ID
import org.example.project.data.remote.network.wrapApiCall
import org.example.project.domain.model.WorkImage
import org.example.project.util.AppLogger

class CraftsmanRemoteDataSourceImpl(
    private val httpClient: HttpClient,
) : CraftsmanRemoteDataSource {

    override suspend fun createCraftsmanProfile(
        userId: String,
        request: CreateCraftsmanRequest
    ): CraftsmanSetupResponseDto {
        return wrapApiCall {
            httpClient.post(ApiConstants.Endpoints.CRAFTSMAN_SETUP) {
                header(ApiConstants.Headers.USER_ID, userId)
                contentType(ContentType.Application.Json)
                setBody(request)
            }
        }
    }

    override suspend fun uploadIdCards(
        userId: String,
        craftsmanId: String,
        idCardFront: ByteArray,
        idCardFrontFileName: String,
        idCardBack: ByteArray,
        idCardBackFileName: String
    ): IdCardUploadResponseDto {
        AppLogger.d("API", "=== Starting ID Cards Upload ===")
        AppLogger.d("API", "UserId: $userId")
        AppLogger.d("API", "CraftsmanId: $craftsmanId")
        AppLogger.d("API", "Front: $idCardFrontFileName (${idCardFront.size} bytes)")
        AppLogger.d("API", "Back: $idCardBackFileName (${idCardBack.size} bytes)")

        return wrapApiCall {
            httpClient.submitFormWithBinaryData(
                url = ApiConstants.Endpoints.craftsmanIdCards(craftsmanId),
                formData = formData {
                    val frontMimeType = getMimeType(idCardFrontFileName)
                    AppLogger.d("API", "Front MIME type: $frontMimeType")

                    append("idCardFront", idCardFront, Headers.build {
                        append(HttpHeaders.ContentType, frontMimeType)
                        append(HttpHeaders.ContentDisposition, "filename=\"$idCardFrontFileName\"")
                    })

                    val backMimeType = getMimeType(idCardBackFileName)
                    AppLogger.d("API", "Back MIME type: $backMimeType")

                    append("idCardBack", idCardBack, Headers.build {
                        append(HttpHeaders.ContentType, backMimeType)
                        append(HttpHeaders.ContentDisposition, "filename=\"$idCardBackFileName\"")
                    })
                }
            ) {
                header(USER_ID, userId)
                AppLogger.d("API", "Request sent to: ${ApiConstants.Endpoints.craftsmanIdCards(craftsmanId)}")
            }
        }
    }

    override suspend fun uploadWorkPortfolio(
        userId: String,
        craftsmanId: String,
        workImages: List<WorkImage>
    ): WorkPortfolioResponseDto {
        AppLogger.d("API", "=== Starting Portfolio Upload ===")
        AppLogger.d("API", "UserId: $userId")
        AppLogger.d("API", "CraftsmanId: $craftsmanId")
        AppLogger.d("API", "Number of images: ${workImages.size}")

        workImages.forEachIndexed { index, image ->
            AppLogger.d("API", "Image $index: ${image.fileName}, ${image.data.size} bytes")
        }

        return wrapApiCall {
            httpClient.submitFormWithBinaryData(
                url = ApiConstants.Endpoints.craftsmanWorkPortfolio(craftsmanId),
                formData = formData {
                    workImages.forEachIndexed { index, image ->
                        val mimeType = getMimeType(image.fileName)
                        AppLogger.d("API", "Appending image $index: ${image.fileName} ($mimeType)")

                        append(
                            key = "workImages",
                            value = image.data,
                            headers = Headers.build {
                                append(HttpHeaders.ContentType, mimeType)
                                append(HttpHeaders.ContentDisposition, "filename=\"${image.fileName}\"")
                            }
                        )
                    }
                }
            ) {
                header(ApiConstants.Headers.USER_ID, userId)
                AppLogger.d("API", "Request sent to: ${ApiConstants.Endpoints.craftsmanWorkPortfolio(craftsmanId)}")
            }
        }
    }

    override suspend fun getCraftsmanProfile(userId: String): CraftsmanProfileResponseDto {
        return wrapApiCall {
            httpClient.get(ApiConstants.Endpoints.CRAFTSMAN_PROFILE) {
                header(ApiConstants.Headers.USER_ID, userId)
            }
        }
    }

    override suspend fun getCraftsmanStatus(craftsmanId: String): CraftsmanStatusResponseDto {
        return wrapApiCall {
            httpClient.get(ApiConstants.Endpoints.craftsmanStatus(craftsmanId))
        }
    }

    override suspend fun deleteCraftsmanAccount(
        userId: String,
        craftsmanId: String
    ): DeleteAccountResponseDto {
        return wrapApiCall {
            httpClient.delete(ApiConstants.Endpoints.deleteCraftsman(craftsmanId)) {
                header(USER_ID, userId)
            }
        }
    }

    private fun getMimeType(fileName: String): String {
        return when (fileName.substringAfterLast('.', "").lowercase()) {
            "png" -> "image/png"
            "jpg", "jpeg" -> "image/jpeg"
            else -> "image/jpeg" // Default to JPEG
        }
    }
}