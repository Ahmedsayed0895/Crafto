package org.example.project.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import org.example.project.data.dto.OnBoardingDto
import org.example.project.data.mapper.toEntity
import org.example.project.data.utils.NetworkConstants.ONBOARDING_END_POINT
import org.example.project.data.utils.safeApiCall
import org.example.project.domain.entity.OnboardingItem
import org.example.project.domain.repository.OnboardingRepository
import org.koin.core.annotation.Provided
import org.koin.core.annotation.Single


@Single(binds = [OnboardingRepository::class])
class OnboardingRepositoryImp(
    @Provided private val httpClient: HttpClient
) : OnboardingRepository {

    override suspend fun getOnboardingData(): List<OnboardingItem> {
        return safeApiCall<List<OnBoardingDto>> {
            httpClient.get("/$ONBOARDING_END_POINT")
        }.map { it.toEntity() }
    }
}