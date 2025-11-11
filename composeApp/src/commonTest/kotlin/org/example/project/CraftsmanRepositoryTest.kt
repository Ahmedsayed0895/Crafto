package org.example.project

import kotlinx.coroutines.test.runTest
import org.example.project.domain.entity.Craftsman
import org.example.project.domain.entity.CraftsmanStatus
import org.example.project.domain.entity.PersonalInfo
import org.example.project.domain.entity.VerificationDocuments
import org.example.project.domain.exception.ValidationException
import org.example.project.domain.model.WorkImage
import org.example.project.domain.repository.CraftsmanRepository
import org.example.project.domain.usecase.craftsman.CreateCraftsmanProfileUseCase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class CreateCraftsmanProfileUseCaseTest {

    private lateinit var useCase: CreateCraftsmanProfileUseCase
    private lateinit var mockRepository: TestCraftsmanRepository

    @BeforeTest
    fun setup() {
        mockRepository = TestCraftsmanRepository()
        useCase = CreateCraftsmanProfileUseCase(mockRepository)
    }

    @Test
    fun `invoke succeeds with valid input`() = runTest {
        // Given
        val personalInfo = PersonalInfo(
            firstName = "John",
            lastName = "Doe",
            phoneNumber = "+1234567890",
            address = "123 Test St"
        )

        // When
        val result = useCase(personalInfo, listOf("plumbing"))

        // Then
        assertEquals("craftsman123", result)
    }

    @Test
    fun `invoke throws ValidationException for empty categories`() = runTest {
        // Given
        val personalInfo = PersonalInfo(
            firstName = "John",
            lastName = "Doe",
            phoneNumber = "+1234567890",
            address = "123 Test St"
        )

        // When/Then
        val exception = assertFailsWith<ValidationException> {
            useCase(personalInfo, emptyList())
        }
        assertEquals("Please select at least one service category", exception.message)
    }

    @Test
    fun `invoke throws ValidationException for invalid phone`() = runTest {
        // Given
        val personalInfo = PersonalInfo(
            firstName = "John",
            lastName = "Doe",
            phoneNumber = "invalid",
            address = "123 Test St"
        )

        // When/Then
        assertFailsWith<ValidationException> {
            useCase(personalInfo, listOf("plumbing"))
        }
    }
}

class TestCraftsmanRepository : CraftsmanRepository {
    override suspend fun createCraftsmanProfile(
        personalInfo: PersonalInfo,
        categories: List<String>
    ): String {
        return "craftsman123"
    }

    override suspend fun uploadIdCards(
        craftsmanId: String,
        idCardFront: ByteArray,
        idCardFrontFileName: String,
        idCardBack: ByteArray,
        idCardBackFileName: String
    ): VerificationDocuments {
        TODO("Not yet implemented")
    }

    override suspend fun uploadWorkPortfolio(
        craftsmanId: String,
        workImages: List<WorkImage>
    ): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getCraftsmanProfile(): Craftsman {
        TODO("Not yet implemented")
    }

    override suspend fun getCraftsmanStatus(craftsmanId: String): CraftsmanStatus {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCraftsmanAccount(craftsmanId: String) {
        TODO("Not yet implemented")
    }
}