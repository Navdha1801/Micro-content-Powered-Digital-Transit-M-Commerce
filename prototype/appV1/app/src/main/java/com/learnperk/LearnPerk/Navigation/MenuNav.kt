package com.learnperk.LearnPerk.Navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.learnperk.LearnPerk.ui.Menuscreen

fun NavGraphBuilder.MenuNavGraph(navController: NavHostController) {
    navigation(
        route = GraphInfo.menuGraph,
        startDestination = MenuScreenInfo.Menu.route
    ) {
        composable(route = MenuScreenInfo.Menu.route) {
            Menuscreen(navController)
        }
    }
}

sealed class MenuScreenInfo(
    val route: String,
    val title: String
) {
    data object Menu: MenuScreenInfo (
        route = "Menu",
        title = "Menu"
    )
}
