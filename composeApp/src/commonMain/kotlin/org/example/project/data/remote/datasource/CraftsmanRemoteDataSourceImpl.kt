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
import org.example.project.data.dto.CraftsmanProfileResponseDto
import org.example.project.data.dto.CraftsmanSetupResponseDto
import org.example.project.data.dto.CraftsmanStatusResponseDto
import org.example.project.data.dto.CreateCraftsmanRequest
import org.example.project.data.dto.DeleteAccountResponseDto
import org.example.project.data.dto.IdCardUploadResponseDto
import org.example.project.data.dto.WorkPortfolioResponseDto
import org.example.project.data.remote.network.ApiConstants
import org.example.project.data.remote.network.ApiConstants.Headers.USER_ID
import org.example.project.data.remote.network.wrapApiCall
import org.example.project.domain.model.WorkImage
import org.koin.core.annotation.Single

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
        return wrapApiCall {
            httpClient.submitFormWithBinaryData(
                url = ApiConstants.Endpoints.craftsmanIdCards(craftsmanId),
                formData = formData {
                    append("idCardFront", idCardFront, Headers.build {
                        append(HttpHeaders.ContentType, "image/*")
                        append(HttpHeaders.ContentDisposition, "filename=\"$idCardFrontFileName\"")
                    })
                    append("idCardBack", idCardBack, Headers.build {
                        append(HttpHeaders.ContentType, "image/*")
                        append(HttpHeaders.ContentDisposition, "filename=\"$idCardBackFileName\"")
                    })
                }
            ) {
                header(USER_ID, userId)
            }
        }
    }

    override suspend fun uploadWorkPortfolio(
        userId: String,
        craftsmanId: String,
        workImages: List<WorkImage>
    ): WorkPortfolioResponseDto {
        return wrapApiCall {
            httpClient.submitFormWithBinaryData(
                url = ApiConstants.Endpoints.craftsmanWorkPortfolio(craftsmanId),
                formData = formData {
                    workImages.forEach { image ->
                        append("workImages", image.data, Headers.build {
                            append(HttpHeaders.ContentType, "image/*")
                            append(HttpHeaders.ContentDisposition, "filename=\"${image.fileName}\"")
                        })
                    }
                }
            ) {
                header(USER_ID, userId)
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

}