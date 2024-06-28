package com.learnperk.LearnPerk.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.learnperk.LearnPerk.ui.Uber.UberAccount
import com.learnperk.LearnPerk.ui.Uber.UberActivity
import com.learnperk.LearnPerk.ui.Uber.UberServices
import com.learnperk.LearnPerk.ui.Uber.Uberhomescreen

fun NavGraphBuilder.UberNavGraph(navController: NavHostController) {
    navigation(
        route = GraphInfo.UberGraph,
        startDestination = UberscreenInfo.Home.route
    ) {
        composable(route = UberscreenInfo.Home.route) {
            Uberhomescreen(navController = navController)
        }
        composable(route = UberscreenInfo.Services.route) {
            UberServices(navController = navController)
        }
        composable(route = UberscreenInfo.Activity.route) {
            UberActivity(navController = navController)
        }
        composable(route = UberscreenInfo.Account.route) {
            UberAccount(navController = navController)
        }
    }
}


sealed class UberscreenInfo(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home: UberscreenInfo(
        route = "Home",
        title = "Home",
        icon = Icons.Default.Home
    )
    data object Services: UberscreenInfo(
        route = "services",
        title = "Services",
        icon = Icons.Default.Menu
    )
    data object Activity: UberscreenInfo(
        route = "activity",
        title = "Activity",
        icon = Icons.Default.Notifications
    )
    data object Account: UberscreenInfo(
        route = "account",
        title = "Account",
        icon = Icons.Default.Person
    )
}