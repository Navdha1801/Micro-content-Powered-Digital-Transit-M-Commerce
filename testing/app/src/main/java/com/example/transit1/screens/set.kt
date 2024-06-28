package com.example.transit1.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(navController: NavHostController){
    Scaffold(
        bottomBar = { BottomBar(navController = navController)}
    ) { padding ->
        Column( modifier = Modifier.padding(padding)) {
            Text(Fetch("profile"));
        }
    }
}