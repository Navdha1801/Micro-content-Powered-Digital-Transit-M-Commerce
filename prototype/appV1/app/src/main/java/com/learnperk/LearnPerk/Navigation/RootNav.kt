package com.learnperk.LearnPerk.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = GraphInfo.menuGraph
    ) {
        MenuNavGraph(navController = navController)
        IRCTCNavGraph(navController)
        UberNavGraph(navController)
        RedbusNavGraph(navController)
    }



}