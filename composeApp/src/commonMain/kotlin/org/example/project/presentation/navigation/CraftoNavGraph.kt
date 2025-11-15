package org.example.project.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.example.project.domain.entity.UserType
import org.example.project.domain.usecase.session.GetUserSessionUseCase
import org.example.project.presentation.navigation.routes.*
import org.koin.compose.koinInject

@Composable
fun CraftoNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    getUserSessionUseCase: GetUserSessionUseCase = koinInject()
) {
    var userType by remember { mutableStateOf<UserType?>(null) }
    var startDestination by remember { mutableStateOf<Any?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    val coroutineScope = rememberCoroutineScope()

    // Determine start destination on first load
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val session = getUserSessionUseCase()
                userType = session.userType

                startDestination = when {
                    session.isFirstTime -> {
                        SplashDestination  // Will navigate to Onboarding
                    }
                    session.userId != null && session.userType != null -> {
                        NavigationBarDestinations.HomeScreen
                    }
                    session.userId != null && session.userType == null -> {
                        UserTypeSelectionDestination
                    }
                    else -> {
                        SplashDestination  // Will navigate to OTP
                    }
                }
            } catch (e: Exception) {
                println("Error loading session: ${e.message}")
                startDestination = SplashDestination
            } finally {
                isLoading = false
            }
        }
    }

    // Show loading while checking session
    if (isLoading || startDestination == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            getCurrentNavBarScreen(navController)?.let { selectedRoute ->
                userType?.let { type ->
                    ShowNavigationBar(
                        selectedRoute = selectedRoute,
                        navController = navController,
                        userType = type
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination!!,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            authNavigationGraph(navController, onUserTypeUpdated = { type -> userType = type })
            setupNavigationGraph(navController, onUserTypeUpdated = { type -> userType = type })
            bottomNavigationBarGraph(navController)
            //detailsNavigationGraph(navController)
        }
    }
}

@Composable
private fun ShowNavigationBar(
    selectedRoute: NavigationBarDestinations,
    navController: NavHostController,
    userType: UserType
) {
    CraftoNavBar(
        currentRoute = selectedRoute,
        onNavDestinationClicked = { route ->
            navController.navigate(route) {
                popUpTo(NavigationBarDestinations.HomeScreen) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        userType = userType
    )
}

// ===== Navigation Graph Functions =====

fun NavGraphBuilder.authNavigationGraph(
    navController: NavHostController,
    onUserTypeUpdated: (UserType) -> Unit
) {
    splashRoute(navController)
    onboardingRoute(navController)
    otpRegistrationRoute(navController)
    userTypeSelectionRoute(navController, onUserTypeUpdated)
}

fun NavGraphBuilder.setupNavigationGraph(
    navController: NavHostController,
    onUserTypeUpdated: (UserType) -> Unit
) {
    craftsmanSetupRoute(navController, onUserTypeUpdated)
    customerSetupRoute(navController, onUserTypeUpdated)
}

fun NavGraphBuilder.bottomNavigationBarGraph(navController: NavHostController) {
    homeRoute(navController)
    myRequestsRoute(navController)
    myJobsRoute(navController)
    messagesRoute(navController)
    moreRoute(navController)
}

//fun NavGraphBuilder.detailsNavigationGraph(navController: NavHostController) {
//    requestDetailsRoute(navController)
//    jobDetailsRoute(navController)
//    messageThreadRoute(navController)
//    settingsDetailRoute(navController)
//}