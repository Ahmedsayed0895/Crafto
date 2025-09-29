import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.call.body
import org.example.project.data.NetworkConstants.DISTRICT_ENDPOINT
import org.example.project.data.NetworkConstants.GOVERNORATES_ENDPOINT
import org.example.project.data.NetworkConstants.LOCATION_PATH
import org.example.project.data.remote.dto.DistrictDto
import org.example.project.data.remote.dto.GovernoratesDto
import org.example.project.data.remote.mapper.toDistrictEntityList
import org.example.project.data.remote.mapper.toGovernoratesEntityList

import org.example.project.domain.entity.District
import org.example.project.domain.entity.Governorates
import org.example.project.domain.repository.LocationRepository

internal class LocationRepositoryImpl(
    private val httpClient: HttpClient
) : LocationRepository {

    override suspend fun getAllGovernorates(): List<Governorates> {
        try {
            // Directly deserialize to List<GovernoratesDto> since server returns an array
            val response = httpClient.get("/$LOCATION_PATH/$GOVERNORATES_ENDPOINT")
            val body: List<GovernoratesDto> = response.body()
            return body.toGovernoratesEntityList()
        } catch (e: Exception) {
            return emptyList()
        }
    }

    override suspend fun getDistrictsByGovernorateId(governorateId: String): List<District> {
        try {
            val response = httpClient.get("/$LOCATION_PATH/$DISTRICT_ENDPOINT/$governorateId")
            val body: List<DistrictDto>  = response.body()
            return body.toDistrictEntityList()
        } catch (e: Exception) {
            return emptyList()
        }
    }
}