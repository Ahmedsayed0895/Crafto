package org.example.project.presentation.screens.setupScreens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.example.project.data.datasource.local.UserPreferences
import org.example.project.domain.entity.PersonalInfo
import org.example.project.domain.usecase.craftsman.CreateCraftsmanProfileUseCase
import org.koin.compose.koinInject
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun TestScreen() {
    val scope = rememberCoroutineScope()
    var result by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var isLoggedIn by remember { mutableStateOf(false) }
    var currentUserId by remember { mutableStateOf<String?>(null) }

    // Get dependencies from Koin
    val userPreferences: UserPreferences = koinInject()
    val createCraftsmanUseCase: CreateCraftsmanProfileUseCase = koinInject()

    // Check login status on composition
    LaunchedEffect(Unit) {
        currentUserId = userPreferences.getUserId()
        isLoggedIn = currentUserId != null
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "API Test Screen",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        // Login Status Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = if (isLoggedIn) Color(0xFF4CAF50).copy(alpha = 0.1f)
                else Color(0xFFF44336).copy(alpha = 0.1f)
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Login Status:",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        if (isLoggedIn) "Logged In" else "Not Logged In",
                        color = if (isLoggedIn) Color(0xFF4CAF50) else Color(0xFFF44336),
                        fontWeight = FontWeight.Bold
                    )
                }

                if (isLoggedIn) {
                    Text(
                        "User ID: $currentUserId",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Button(
                    onClick = {
                        scope.launch {
                            if (isLoggedIn) {
                                // Logout
                                userPreferences.clearUserId()
                                isLoggedIn = false
                                currentUserId = null
                                result = "Logged out successfully"
                            } else {
                                // Simulate login
                                val testUserId = "test-user-${Clock.System.now()}"
                                userPreferences.setUserId(testUserId)
                                currentUserId = testUserId
                                isLoggedIn = true
                                result = "Logged in with ID: $testUserId"
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isLoggedIn) Color(0xFFF44336) else Color(0xFF4CAF50)
                    )
                ) {
                    Text(if (isLoggedIn) "Logout" else "Simulate Login")
                }
            }
        }

        Divider()

        // Test Actions
        Text(
            "Test Actions",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Button(
            onClick = {
                scope.launch {
                    isLoading = true
                    try {
                        val personalInfo = PersonalInfo(
                            firstName = "Test",
                            lastName = "User ${Clock.System.now()}",
                            phoneNumber = "+1234567890",
                            address = "123 Test Street"
                        )

                        val craftsmanId = createCraftsmanUseCase(
                            personalInfo,
                            listOf("plumbing", "electrical")
                        )

                        result = "Success! Craftsman ID: $craftsmanId"
                    } catch (e: Exception) {
                        result = "Error: ${e.message}"
                    } finally {
                        isLoading = false
                    }
                }
            },
            enabled = !isLoading && isLoggedIn,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text("Test Create Craftsman Profile")
            }
        }

        if (!isLoggedIn) {
            Text(
                "Please login first to test API calls",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Result Display
        if (result.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (result.startsWith("Success") || result.contains("Logged"))
                        Color(0xFF4CAF50).copy(alpha = 0.1f)
                    else Color(0xFFF44336).copy(alpha = 0.1f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        "Result:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = result,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (result.startsWith("Success") || result.contains("Logged"))
                            Color(0xFF4CAF50)
                        else Color(0xFFF44336)
                    )
                }
            }
        }
    }
}