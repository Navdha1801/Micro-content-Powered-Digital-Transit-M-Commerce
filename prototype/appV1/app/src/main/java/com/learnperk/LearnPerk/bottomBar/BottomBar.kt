package com.learnperk.LearnPerk.bottomBar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.learnperk.LearnPerk.Navigation.ScreenInfo

@Composable
fun BottomBar(modifier: Modifier,navController: NavHostController) {
    val screens = listOf(
        ScreenInfo.Home,
        ScreenInfo.Profile,
        ScreenInfo.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route}
    if (bottomBarDestination) {
        NavigationBar {
            screens.forEachIndexed { _, bottomBarItems ->
                NavigationBarItem(selected = currentDestination?.hierarchy?.any {
                    it.route == bottomBarItems.route
                } == true,
                    onClick = { navController.navigate(bottomBarItems.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    } },
                    icon = {
                        Icon(imageVector = bottomBarItems.icon
                            , contentDescription = "Navigation Icon")
                    })
            }
        }
    }

}

@Composable
fun BottomNavigationBar(navController : NavHostController) {
    BottomNavigation(backgroundColor = Color.White) {
        val screens = listOf(
            ScreenInfo.Home,
            ScreenInfo.Profile,
            ScreenInfo.Shop,
            ScreenInfo.Transactions,
            ScreenInfo.Settings,
        )
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route

        screens.forEach {item ->
            BottomNavigationItem(selected = currentDestination == item.route, onClick = { navController.navigate(item.route) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            } },icon = { Icon(imageVector = item.icon, contentDescription = null) })

        }
    }
}