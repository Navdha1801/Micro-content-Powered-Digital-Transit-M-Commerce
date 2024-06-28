package com.example.transit1.screens

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.transit1.Bottombaritems
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.concurrent.ArrayBlockingQueue


@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController)}
    ) { padding ->
        Column( modifier = Modifier.padding(padding)) {

            Text(text = Fetch("home"));
        }
    }
}

fun Fetch(key:String): String {
    val db = Firebase.firestore
    val text = ArrayBlockingQueue<String>(1);
    val docRef = db.collection("testCollection").document("doc1")
    docRef.get().addOnSuccessListener { document ->
        if (document != null) {
            text.put(document.getString(key).toString())
            Log.d(TAG,"Changed $text")
        }
    }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }
    return text.take();
}
@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        Bottombaritems.Home,
        Bottombaritems.Profile,
        Bottombaritems.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route}
    if (bottomBarDestination) {
        NavigationBar {
            screens.forEachIndexed { index, bottombaritems ->
                NavigationBarItem(selected = currentDestination?.hierarchy?.any {
                                                                                it.route == bottombaritems.route
                } == true,
                    onClick = { navController.navigate(bottombaritems.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    } },
                    icon = { 
                        Icon(imageVector = bottombaritems.icon
                            , contentDescription = "Navigation Icon")
                    })
            }
        }
    }

}

