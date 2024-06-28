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
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.transit1.Bottombaritems
import com.example.transit1.R
import com.example.transit1.screens.HomeScreen
import androidx.navigation.compose.NavHost
@Composable
fun RootNavigationGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Graph.HOME
    ) {
        HomeNavGraph(navController = navController)
        AuthNavGraph(navController = navController)
    }



}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
}


@Preview
@Composable
fun Forpreview() {
    PickupDestination(modifier = Modifier)
}

@Composable
fun PickupDestination(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.8f)) {
            Text(stringResource(R.string.button_name))
        }
        Text(text = stringResource(R.string.search_vehicles))
//        Box(
        Modifier.background(MaterialTheme.colorScheme.onBackground)
//        ) {
        EnterPickAndDestination(modifier = modifier)
//        }
    }
}

@Composable
fun EnterPickAndDestination(modifier: Modifier) {
    var pickup by remember { mutableStateOf("") }
    var dest by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = pickup, onValueChange = { pickup = it },modifier = modifier, label = { Text(
            text = stringResource(R.string.enter_pickup)
        )
        })
        TextField(value = dest, onValueChange = {dest = it},modifier = modifier, label = { Text(
            text = stringResource(R.string.enter_destination)
        )
        })
        Button(onClick = { /*TODO*/ }) {
            Text(text = stringResource(R.string.search_vehicles))
        }
    }

}

@Composable
fun GetData(navController: NavController, modifier: Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = { navController.navigate(R.id.navigation_data_display) }) {
            Text(text = stringResource(R.string.get_data))
        }
    }
}

