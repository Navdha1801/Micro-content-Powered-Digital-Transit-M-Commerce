package com.learnperk.LearnPerk.Navigation


import android.graphics.drawable.AdaptiveIconDrawable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.learnperk.LearnPerk.R
import com.learnperk.LearnPerk.ui.Redbus.RedbusAccount
import com.learnperk.LearnPerk.ui.Redbus.RedbusBookings
import com.learnperk.LearnPerk.ui.Redbus.RedbusHelp
import com.learnperk.LearnPerk.ui.Redbus.Redbushomescreen

fun NavGraphBuilder.RedbusNavGraph(navController: NavHostController) {
    navigation(
        route = GraphInfo.RedbusGraph,
        startDestination = RedbusscreenInfo.Home.route
    ) {
        composable(route = RedbusscreenInfo.Home.route) {
            Redbushomescreen(navController = navController)
        }
        composable(route = RedbusscreenInfo.Bookings.route) {
            RedbusBookings(navController = navController)
        }
        composable(route = RedbusscreenInfo.Help.route) {
            RedbusHelp(navController = navController)
        }
        composable(route = RedbusscreenInfo.Account.route) {
            RedbusAccount(navController = navController)
        }
    }
}


sealed class RedbusscreenInfo(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home: RedbusscreenInfo(
        route = "RedHome",
        title = "RedHome",
        icon = Icons.Default.Home
    )
    data object Bookings: RedbusscreenInfo(
        route = "RedBookings",
        title = "RedServices",
        icon = Icons.Default.Menu
    )
    data object Help: RedbusscreenInfo(
        route = "RedHelp",
        title = "RedHelp",
        icon = Icons.Default.Email
    )
    data object Account: RedbusscreenInfo(
        route = "RedAccount",
        title = "Account",
        icon = Icons.Default.AccountCircle
    )
}