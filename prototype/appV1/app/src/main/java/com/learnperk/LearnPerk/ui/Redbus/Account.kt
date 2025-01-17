package com.learnperk.LearnPerk.ui.Redbus

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.learnperk.LearnPerk.Navigation.MenuScreenInfo
import com.learnperk.LearnPerk.Navigation.RedbusscreenInfo

@Composable
fun RedbusAccount(navController: NavHostController) {
    BackHandler {
        navController.popBackStack(route = MenuScreenInfo.Menu.route, inclusive = false)
    }
    Scaffold(
        bottomBar = { Redbusbottomnavigationbar(navController = navController) }
    ) {padding ->
        Column( modifier = Modifier.padding(padding), verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {
            Text("This is Account Screen")
        }
    }
}