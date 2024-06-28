package com.learnperk.LearnPerk.ui.Uber

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.learnperk.LearnPerk.Navigation.MenuScreenInfo
import com.learnperk.LearnPerk.Navigation.UberscreenInfo

@Composable
fun UberAccount(navController: NavHostController) {
    BackHandler {
        navController.popBackStack(route = MenuScreenInfo.Menu.route, inclusive = false)
    }
    Scaffold(
        bottomBar = { UberBottomNavigationBar(navController = navController) }
    ) {padding ->
        Column( modifier = Modifier.padding(padding)) {
            Text("This is Profile Screen")
        }
    }
}