package com.example.transit1

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Bottombaritems(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : Bottombaritems(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Profile : Bottombaritems(
        route = "profile",
        title = "profile",
        icon = Icons.Default.Person
    )
    object Settings : Bottombaritems(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )



}