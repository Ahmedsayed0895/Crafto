package org.example.project.presentation.navigation.routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.example.project.presentation.navigation.NavigationBarDestinations
import org.example.project.presentation.screens.home.HomeScreen
import org.example.project.presentation.screens.messages.MessagesScreen
import org.example.project.presentation.screens.more.MoreScreen
import org.example.project.presentation.screens.myjobs.MyJobsScreen
import org.example.project.presentation.screens.myrequests.MyRequestsScreen

fun NavGraphBuilder.homeRoute(navController: NavHostController) {
    composable<NavigationBarDestinations.HomeScreen> {
        HomeScreen()
    }
}

fun NavGraphBuilder.myRequestsRoute(navController: NavHostController) {
    composable<NavigationBarDestinations.MyRequestsScreen> {
        MyRequestsScreen()
    }
}

fun NavGraphBuilder.myJobsRoute(navController: NavHostController) {
    composable<NavigationBarDestinations.MyJobsScreen> {
        MyJobsScreen()
    }
}

fun NavGraphBuilder.messagesRoute(navController: NavHostController) {
    composable<NavigationBarDestinations.MessagesScreen> {
        MessagesScreen()
    }
}

fun NavGraphBuilder.moreRoute(navController: NavHostController) {
    composable<NavigationBarDestinations.MoreScreen> {
        MoreScreen()
    }
}