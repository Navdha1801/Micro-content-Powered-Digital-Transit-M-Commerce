package com.learnperk.LearnPerk.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenInfo(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    data object Home : ScreenInfo(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    data object Profile : ScreenInfo(
        route = "profile",
        title = "profile",
        icon = Icons.Default.Person
    )
    data object Shop : ScreenInfo(
        route = "shop",
        title = "Shop",
        icon = Icons.Default.ShoppingCart
    )
    data object Settings : ScreenInfo(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
    data object Transactions : ScreenInfo(
        route = "more",
        title = "More",
        icon = Icons.Default.Menu
    )
    data object LPHome : ScreenInfo(
        route = "lp_home",
        title = "LPHome",
        icon = Icons.Default.Add
    )
    data object Player: ScreenInfo(
        route = "Player",
        title = "Player",
        icon = Icons.Default.Add
    )
    data object LPStore : ScreenInfo(
        route = "lp_store",
        title = "LPstore",
        icon = Icons.Default.Add
    )
    data object  Users: ScreenInfo(
        route = "users",
        title = "users",
        icon = Icons.Default.Add
    )
    data object LPTransactions: ScreenInfo(
        route = "lp_transactions",
        title = "LP_Transactions",
        icon = Icons.Default.Add
    )

    data object Interests: ScreenInfo(
        route = "interests",
        title = "Interests",
        icon = Icons.Default.Add
    )
}

object GraphInfo{
    const val IRCTCgraph = "HomeGraph"
    const val menuGraph = "MenuGraph"
    const val UberGraph = "UberGraph"
    const val RedbusGraph = "RedbusGraph"
}