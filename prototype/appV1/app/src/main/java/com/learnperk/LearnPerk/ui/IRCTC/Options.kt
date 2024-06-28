package com.learnperk.LearnPerk.ui.IRCTC

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.learnperk.LearnPerk.bottomBar.BottomNavigationBar

@Composable
fun OptionsScreen(navController: NavHostController){
    Scaffold(
        bottomBar = {BottomNavigationBar(navController = navController)}
    ) { padding ->
        Column( modifier = Modifier.padding(padding)) {
            Text("This is Options Screen")
        }
    }
}