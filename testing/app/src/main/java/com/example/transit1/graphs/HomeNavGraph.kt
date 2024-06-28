package com.example.transit1.graphs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.transit1.Bottombaritems
import com.example.transit1.R
import com.example.transit1.screens.HomeScreen
import androidx.navigation.compose.NavHost
import com.example.transit1.screens.BottomBar
import com.example.transit1.screens.ProfileScreen
import com.example.transit1.screens.SettingScreen


fun NavGraphBuilder.HomeNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.HOME,
        startDestination = Bottombaritems.Home.route
    ) {
        composable(route = Bottombaritems.Home.route) {
            HomeScreen(navController)
//            PickupDestination(modifier = Modifier)
//            GetData(navController = navController, modifier = Modifier)
        }
        composable(route = Bottombaritems.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(route = Bottombaritems.Settings.route) {
            SettingScreen(navController = navController)
        }

    }
}


