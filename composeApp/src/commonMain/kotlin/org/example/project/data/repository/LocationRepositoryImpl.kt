package org.example.project.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.call.body
import org.example.project.data.remote.dto.DistrictDto
import org.example.project.data.remote.dto.GovernoratesDto
import org.example.project.data.mapper.toDistrict
import org.example.project.data.mapper.toGovernorates
import org.example.project.data.remote.network.ApiConstants.Endpoints.DISTRICT_ENDPOINT
import org.example.project.data.remote.network.ApiConstants.Endpoints.GOVERNORATES_ENDPOINT
import org.example.project.data.remote.network.ApiConstants.Endpoints.LOCATION_PATH
import org.example.project.domain.entity.District
import org.example.project.domain.entity.Governorates
import org.example.project.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val httpClient: HttpClient
) : LocationRepository {

    override suspend fun getAllGovernorates(): List<Governorates> {
        val response = httpClient.get("/$LOCATION_PATH/$GOVERNORATES_ENDPOINT")
        val body: List<GovernoratesDto> = response.body()
        return body.toGovernorates()
    }

    override suspend fun getDistrictsByGovernorateId(governorateId: String): List<District> {
        val response = httpClient.get("/$LOCATION_PATH/$DISTRICT_ENDPOINT/$governorateId")
        val body: List<DistrictDto> = response.body()
        return body.toDistrict()
    }
}