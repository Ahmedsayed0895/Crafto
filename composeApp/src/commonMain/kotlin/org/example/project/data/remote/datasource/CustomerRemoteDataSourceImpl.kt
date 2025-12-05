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
import org.example.project.data.remote.dto.CreateCustomerRequest
import org.example.project.data.remote.dto.CustomerProfileResponseDto
import org.example.project.data.remote.dto.CustomerSetupResponseDto
import org.example.project.data.remote.dto.DeleteAccountResponseDto
import org.example.project.data.remote.dto.ProfilePictureUploadResponseDto
import org.example.project.data.remote.network.ApiConstants
import org.example.project.data.remote.network.wrapApiCall
import org.example.project.data.utils.getMimeType

class CustomerRemoteDataSourceImpl(private val httpClient: HttpClient) : CustomerRemoteDataSource {
    override suspend fun createCustomerProfile(
        userId: String,
        request: CreateCustomerRequest
    ): CustomerSetupResponseDto {
        return wrapApiCall {
            httpClient.post(ApiConstants.Endpoints.CUSTOMER_SETUP) {
                header(ApiConstants.Headers.USER_ID, userId)
                contentType(ContentType.Application.Json)
                setBody(request)
            }
        }

    }

    override suspend fun uploadProfilePicture(
        userId: String,
        customerId: String,
        profilePicture: ByteArray,
        profilePictureFileName: String
    ): ProfilePictureUploadResponseDto {
        return wrapApiCall{
            httpClient.submitFormWithBinaryData(
                url = ApiConstants.Endpoints.customerProfilePicture(customerId),
                formData = formData {
                    val mimeType = getMimeType(profilePictureFileName)
                    append("profilePicture", profilePicture, Headers.build {
                        append(HttpHeaders.ContentType, mimeType)
                        append(HttpHeaders.ContentDisposition, "filename=\"$profilePictureFileName\"")
                    }
                    )
                }) {
                header(ApiConstants.Headers.USER_ID, userId)
            }
        }
    }

    override suspend fun getCustomerProfile(userId: String): CustomerProfileResponseDto {
        return wrapApiCall {
            httpClient.get(ApiConstants.Endpoints.CUSTOMER_PROFILE) {
                header(ApiConstants.Headers.USER_ID, userId)
            }
        }
    }

    override suspend fun deleteCustomerAccount(
        userId: String,
        customerId: String
    ): DeleteAccountResponseDto {
        return wrapApiCall {
            httpClient.delete(ApiConstants.Endpoints.deleteCustomer(customerId))
        }
    }
}