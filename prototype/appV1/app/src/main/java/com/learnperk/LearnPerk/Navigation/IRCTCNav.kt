package com.learnperk.LearnPerk.Navigation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.learnperk.LearnPerk.ui.Ecommerce
import com.learnperk.LearnPerk.ui.IRCTC.HomeScreen
import com.learnperk.LearnPerk.ui.LPscreen
import com.learnperk.LearnPerk.ui.IRCTC.ProfileScreen
import com.learnperk.LearnPerk.ui.IRCTC.SettingsScreen
import com.learnperk.LearnPerk.ui.YoutubeScreen
import androidx.compose.ui.Modifier
import com.learnperk.LearnPerk.ui.IRCTC.IrctcComScreen
import com.learnperk.LearnPerk.ui.LP_transactions
import com.learnperk.LearnPerk.ui.IRCTC.OptionsScreen
import com.learnperk.LearnPerk.ui.InterestSelectionScreen
import com.learnperk.LearnPerk.ui.Profiles
import com.learnperk.LearnPerk.ui.Usersdisplay

fun NavGraphBuilder.IRCTCNavGraph(navController: NavHostController){
    navigation(
        route = GraphInfo.IRCTCgraph,
        startDestination = ScreenInfo.Home.route
    ){
        composable(route = ScreenInfo.Home.route) {
            HomeScreen(navController)
        }
        composable(route = ScreenInfo.Profile.route) {
            ProfileScreen(navController)
        }
        composable(route = ScreenInfo.Settings.route) {
            SettingsScreen(navController)
        }
        composable(route = ScreenInfo.Shop.route) {
            IrctcComScreen(navController)
        }
        composable(route = ScreenInfo.Transactions.route) {
            OptionsScreen(navController)
        }
        composable(route = ScreenInfo.Users.route){
            Usersdisplay(navController)
        }
        composable(route = ScreenInfo.LPHome.route + "/{User}/{toggleSB}") {
            val User = it.arguments?.getString("User")?.toInt()
            var toggleSB = it.arguments?.getString("toggleSB")?.toBoolean()
            if (User != null) {
                if (toggleSB == null){
                    toggleSB = false;
                }
                Log.d(TAG,"$User")
                LPscreen(navController = navController,User, toggleSB)
            }
        }
        composable(route = ScreenInfo.LPStore.route + "/{User}") {
            val User = it.arguments?.getString("User")?.toInt()
            if (User != null) {
                Ecommerce(navController = navController, User = User)
            }
        }
        composable(route = ScreenInfo.Player.route + "/{videoId}/{User}") { // Assuming you pass the videoId as a parameter
            val videoId = it.arguments?.getString("videoId")
            val User = it.arguments?.getString("User")?.toInt()

            if (videoId != null && User!=null) {
                YoutubeScreen(navController,User,videoId, modifier = Modifier)
            }
        }

        composable(route = ScreenInfo.LPTransactions.route + "/{User}") {
            val User = it.arguments?.getString("User")?.toInt()
            if (User != null) {
                LP_transactions(navController = navController, User)
            }

        }
        composable(route = ScreenInfo.Interests.route + "/{User}") {
            val User = it.arguments?.getString("User")?.toInt()
            if (User != null) {
                InterestSelectionScreen(navController = navController, User)
            }

        }
    }
}