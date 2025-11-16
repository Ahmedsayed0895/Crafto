package org.example.project.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.*
import org.example.project.domain.entity.UserType
import org.example.project.presentation.designsystem.components.CraftoBottomNavBar
import org.example.project.presentation.designsystem.components.CraftoNavItem
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


enum class BottomNavigationItem(
    val icon: DrawableResource,
    val selectedIcon: DrawableResource,
    val label: String,
    val route: NavigationBarDestinations,
) {
    Home(
        icon = Res.drawable.home_angle,
        selectedIcon = Res.drawable.home_angle_1,
        label = "Home",
        route = NavigationBarDestinations.HomeScreen
    ),
    MyRequests(  // Customer
        icon = Res.drawable.clipboard_text,
        selectedIcon = Res.drawable.clipboard_text_1,
        label = "My Requests",
        route = NavigationBarDestinations.MyRequestsScreen
    ),
    MyJobs(  // Craftsman
        icon = Res.drawable.clipboard_text,
        selectedIcon = Res.drawable.clipboard_text_1,
        label = "My Jobs",
        route = NavigationBarDestinations.MyJobsScreen
    ),
    Messages(
        icon = Res.drawable.dialog,
        selectedIcon = Res.drawable.dialog_1,
        label = "Messages",
        route = NavigationBarDestinations.MessagesScreen
    ),
    More(
        icon = Res.drawable.user_circle,
        selectedIcon = Res.drawable.user_circle_1,
        label = "More",
        route = NavigationBarDestinations.MoreScreen
    )
}

fun getBottomNavItems(userType: UserType): List<BottomNavigationItem> {
    return when (userType) {
        UserType.CUSTOMER -> listOf(
            BottomNavigationItem.Home,
            BottomNavigationItem.MyRequests,
            BottomNavigationItem.Messages,
            BottomNavigationItem.More
        )
        UserType.CRAFTSMAN -> listOf(
            BottomNavigationItem.Home,
            BottomNavigationItem.MyJobs,
            BottomNavigationItem.Messages,
            BottomNavigationItem.More
        )
    }
}

@Composable
fun CraftoNavBar(
    currentRoute: NavigationBarDestinations,
    onNavDestinationClicked: (NavigationBarDestinations) -> Unit,
    userType: UserType
) {
    val items = getBottomNavItems(userType)
    val selectedIndex = items.indexOfFirst { it.route == currentRoute }

    CraftoBottomNavBar(
        items = items.map { item ->
            CraftoNavItem(
                label = item.label,
                icon = painterResource(item.icon),
                selectedIcon = painterResource(item.selectedIcon)
            )
        },
        selectedIndex = selectedIndex.coerceAtLeast(0),
        onItemSelected = { index ->
            val selectedItem = items[index]
            if (selectedItem.route != currentRoute) {
                onNavDestinationClicked(selectedItem.route)
            }
        }
    )
}

@Composable
fun getCurrentNavBarScreen(navController: NavController): NavigationBarDestinations? {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute: String? = backStackEntry?.destination?.route
    val matchedEntry: Map.Entry<String?, NavigationBarDestinations>? =
        bottomNavBarDestinationsMap.entries.firstOrNull { (route, _) ->
            currentRoute != null && route != null && currentRoute.startsWith(route)
        }
    return matchedEntry?.value
}