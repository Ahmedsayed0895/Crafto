package org.example.project.presentation.navigation.routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.example.project.domain.entity.UserType
import org.example.project.presentation.navigation.CraftsmanSetupDestination
import org.example.project.presentation.navigation.CustomerSetupDestination
import org.example.project.presentation.navigation.NavigationBarDestinations
import org.example.project.presentation.screens.setup.craftsmansetup.CraftsmanSetupScreen
import org.example.project.presentation.screens.setup.customersetup.CustomerSetupScreen

fun NavGraphBuilder.craftsmanSetupRoute(
    navController: NavHostController,
    onUserTypeUpdated: (UserType) -> Unit
) {
    composable<CraftsmanSetupDestination> {
        CraftsmanSetupScreen(
            onComplete = {
                onUserTypeUpdated(UserType.CRAFTSMAN)
                navController.navigate(NavigationBarDestinations.HomeScreen) {
                    popUpTo(0) { inclusive = true }
                }
            },
            onClose = {
                navController.popBackStack()
            }
        )
    }
}

fun NavGraphBuilder.customerSetupRoute(
    navController: NavHostController,
    onUserTypeUpdated: (UserType) -> Unit
) {
    composable<CustomerSetupDestination> {
        CustomerSetupScreen(
            onComplete = {
                onUserTypeUpdated(UserType.CUSTOMER)
                navController.navigate(NavigationBarDestinations.HomeScreen) {
                    popUpTo(0) { inclusive = true }
                }
            },
            onClose = {
                navController.popBackStack()
            }
        )
    }
}