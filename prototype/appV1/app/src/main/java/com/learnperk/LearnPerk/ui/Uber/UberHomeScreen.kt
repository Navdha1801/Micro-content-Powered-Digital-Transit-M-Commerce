package com.learnperk.LearnPerk.ui.Uber

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.learnperk.LearnPerk.Navigation.MenuScreenInfo
import com.learnperk.LearnPerk.Navigation.ScreenInfo
import com.learnperk.LearnPerk.Navigation.UberscreenInfo
import com.learnperk.LearnPerk.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Uberhomescreen(navController: NavHostController) {
    BackHandler {
        navController.popBackStack(route = MenuScreenInfo.Menu.route, inclusive = false)
    }
    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(text = "Uber", fontWeight = FontWeight.ExtraBold, fontSize = 40.sp)
                }
            )
        },
        bottomBar = { UberBottomNavigationBar(navController = navController) },
        floatingActionButton = { FAB_Uber(navController = navController)  }
    ){padding ->
        LazyColumn(Modifier.padding(padding)) {
            item {
                Subrow(navController)
            }
            item {
                Subrow1()
            }
            item {
                Subrow2()
            }
            item {
                Subrow3()
            }
        }

    }
}

@Composable
fun Subrow1() {
    Column {
        Text(text = "Commute smarter",  Modifier.padding(horizontal = 16.dp),fontWeight = FontWeight.Bold, fontSize = 20.sp)
        LazyRow() {
            item {
                Box(modifier = Modifier.padding(16.dp)) {
                    Column {
                        Card(modifier = Modifier
                            .clickable { /* Handle card click if needed */ },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                                disabledContentColor = MaterialTheme.colorScheme.onSurface,
                                contentColor = MaterialTheme.colorScheme.onSurface)) {
                            Image(painter = painterResource(com.learnperk.LearnPerk.R.drawable.r11), contentDescription = "null")
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Go with Uber Auto")
                        Text(text = "Doorstep pickup, no bargaining", fontWeight = FontWeight.Light, fontSize = 13.sp)
                    }
                }
            }
            item {
                Box(modifier = Modifier.padding(16.dp)) {
                    Column {
                        Card(modifier = Modifier
                            .clickable { /* Handle card click if needed */ },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                                disabledContentColor = MaterialTheme.colorScheme.onSurface,
                                contentColor = MaterialTheme.colorScheme.onSurface)) {
                            Image(painter = painterResource(com.learnperk.LearnPerk.R.drawable.r12), contentDescription = "null")
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Hop on Uber Auto")
                        Text(text = "Move through traffic & save time", fontWeight = FontWeight.Light, fontSize = 13.sp)
                    }
                }
            }
            item {
                Box(modifier = Modifier.padding(16.dp)) {
                    Column {
                        Card(modifier = Modifier
                            .clickable { /* Handle card click if needed */ },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                                disabledContentColor = MaterialTheme.colorScheme.onSurface,
                                contentColor = MaterialTheme.colorScheme.onSurface)) {
                            Image(painter = painterResource(com.learnperk.LearnPerk.R.drawable.r13), contentDescription = "null")
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Hop on a shuttle")
                        Text(text = "Pre-book a seat, ride in comfort", fontWeight = FontWeight.Light, fontSize = 13.sp)
                    }
                }
            }
            item {
                Box(modifier = Modifier.padding(16.dp)) {
                    Column {
                        Card(modifier = Modifier
                            .clickable { /* Handle card click if needed */ },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                                disabledContentColor = MaterialTheme.colorScheme.onSurface,
                                contentColor = MaterialTheme.colorScheme.onSurface)) {
                            Image(painter = painterResource(com.learnperk.LearnPerk.R.drawable.r14), contentDescription = "null")
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Try Group Rides")
                        Text(text = "Ride with coworkers and save", fontWeight = FontWeight.Light, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun Subrow2() {
    Column {
        Text(text = "Save everyday",  Modifier.padding(horizontal = 16.dp),fontWeight = FontWeight.Bold, fontSize = 20.sp)
        LazyRow() {
            item {
                Box(modifier = Modifier.padding(16.dp)) {
                    Column {
                        Card(modifier = Modifier
                            .clickable { /* Handle card click if needed */ },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                                disabledContentColor = MaterialTheme.colorScheme.onSurface,
                                contentColor = MaterialTheme.colorScheme.onSurface)) {
                            Image(painter = painterResource(com.learnperk.LearnPerk.R.drawable.r21), contentDescription = "null")
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Auto rides")
                        Text(text = "Upfront fares, doorstep pickups", fontWeight = FontWeight.Light, fontSize = 13.sp)
                    }
                }
            }
            item {
                Box(modifier = Modifier.padding(16.dp)) {
                    Column {
                        Card(modifier = Modifier
                            .clickable { /* Handle card click if needed */ },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                                disabledContentColor = MaterialTheme.colorScheme.onSurface,
                                contentColor = MaterialTheme.colorScheme.onSurface)) {
                            Image(painter = painterResource(com.learnperk.LearnPerk.R.drawable.r22), contentDescription = "null")
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Uber Moto rides")
                        Text(text = "Affordable motorcycle pickups", fontWeight = FontWeight.Light, fontSize = 13.sp)
                    }
                }
            }
            item {
                Box(modifier = Modifier.padding(16.dp)) {
                    Column {
                        Card(modifier = Modifier
                            .clickable { /* Handle card click if needed */ },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                                disabledContentColor = MaterialTheme.colorScheme.onSurface,
                                contentColor = MaterialTheme.colorScheme.onSurface)) {
                            Image(painter = painterResource(com.learnperk.LearnPerk.R.drawable.r23), contentDescription = "null")
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Shuttle rides")
                        Text(text = "Low fares, premium A/C buses", fontWeight = FontWeight.Light, fontSize = 13.sp)
                    }
                }
            }
            item {
                Box(modifier = Modifier.padding(16.dp)) {
                    Column {
                        Card(modifier = Modifier
                            .clickable { /* Handle card click if needed */ },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                                disabledContentColor = MaterialTheme.colorScheme.onSurface,
                                contentColor = MaterialTheme.colorScheme.onSurface)) {
                            Image(painter = painterResource(com.learnperk.LearnPerk.R.drawable.r14), contentDescription = "null")
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Try a group ride")
                        Text(text = "Seamless rides, together", fontWeight = FontWeight.Light, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun Subrow3() {
    Column {
        Text(text = "Elevate your ride",  Modifier.padding(horizontal = 16.dp),fontWeight = FontWeight.Bold, fontSize = 20.sp)
        LazyRow() {
            item {
                Box(modifier = Modifier.padding(16.dp)) {
                    Column {
                        Card(modifier = Modifier
                            .clickable { /* Handle card click if needed */ },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                                disabledContentColor = MaterialTheme.colorScheme.onSurface,
                                contentColor = MaterialTheme.colorScheme.onSurface)) {
                            Image(painter = painterResource(com.learnperk.LearnPerk.R.drawable.r11), contentDescription = "null")
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Request Uber XL")
                        Text(text = "Spacious Comfortable SUV rides", fontWeight = FontWeight.Light, fontSize = 13.sp)
                    }
                }
            }
            item {
                Box(modifier = Modifier.padding(16.dp)) {
                    Column {
                        Card(modifier = Modifier
                            .clickable { /* Handle card click if needed */ },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                                disabledContentColor = MaterialTheme.colorScheme.onSurface,
                                contentColor = MaterialTheme.colorScheme.onSurface)) {
                            Image(painter = painterResource(com.learnperk.LearnPerk.R.drawable.r12), contentDescription = "null")
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Request Premier")
                        Text(text = "Ride with top-rated drivers", fontWeight = FontWeight.Light, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun Subrow(navController: NavHostController) {
    Column {
        Text(text = "Suggestions",  Modifier.padding(horizontal = 16.dp),fontWeight = FontWeight.Bold, fontSize = 20.sp)
        LazyRow {
            item {
                Box(modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(modifier = Modifier
                            .clickable {  },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                                disabledContentColor = MaterialTheme.colorScheme.onSurface,
                                contentColor = MaterialTheme.colorScheme.onSurface)) {
                            Image(painter = painterResource(com.learnperk.LearnPerk.R.drawable.ride), contentDescription = "null")
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Ride", fontWeight = FontWeight.Light, fontSize = 13.sp)
                    }
                }
            }
            item {
                Box(modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)) {
                    Column (horizontalAlignment = Alignment.CenterHorizontally){
                        Card(modifier = Modifier
                            .clickable { /* Handle card click if needed */ },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                                disabledContentColor = MaterialTheme.colorScheme.onSurface,
                                contentColor = MaterialTheme.colorScheme.onSurface)) {
                            Image(painter = painterResource(com.learnperk.LearnPerk.R.drawable.intercity), contentDescription = "null")
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Package", fontWeight = FontWeight.Light, fontSize = 13.sp)
                    }
                }
            }
            item {
                Box(modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
                    .clickable { navController.navigate(ScreenInfo.Users.route) }) {
                    Column (horizontalAlignment = Alignment.CenterHorizontally){
                        Card(
                            modifier = Modifier,
                            shape = RoundedCornerShape(16.dp),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                                disabledContentColor = MaterialTheme.colorScheme.onSurface,
                                contentColor = MaterialTheme.colorScheme.onSurface
                            )
                        ) {
                            // Wrapping the Image with a Border
                            Box(
                                modifier = Modifier
                                    .size(width=60.dp,height=50.dp) // Adjust the size as needed
                                    .border(width = 2.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(16.dp)) // Border settings
                            ) {
                                Image(
                                    painter = painterResource(com.learnperk.LearnPerk.R.drawable.lp_logo),
                                    contentDescription = "null",
                                    Modifier.size(50.dp)
                                        .align(Alignment.Center)// Adjust the size as needed
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "LearnPerk", fontWeight = FontWeight.Light, fontSize = 13.sp)
                    }
                }
            }

            item {
                Box(modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(modifier = Modifier
                            .clickable { /* Handle card click if needed */ },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                                disabledContentColor = MaterialTheme.colorScheme.onSurface,
                                contentColor = MaterialTheme.colorScheme.onSurface)) {
                            Image(painter = painterResource(com.learnperk.LearnPerk.R.drawable.shuttle), contentDescription = "null")
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Shuttle", fontWeight = FontWeight.Light, fontSize = 13.sp)
                    }
                }
            }
            item {
                Box(modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(modifier = Modifier
                            .clickable { /* Handle card click if needed */ },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                                disabledContentColor = MaterialTheme.colorScheme.onSurface,
                                contentColor = MaterialTheme.colorScheme.onSurface)) {
                            Image(painter = painterResource(com.learnperk.LearnPerk.R.drawable.intercity), contentDescription = "null")
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Intercity", fontWeight = FontWeight.Light, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}


@Composable
fun UberBottomNavigationBar(navController : NavHostController) {
    BottomNavigation(backgroundColor = Color.White) {
        val screens = listOf(
            UberscreenInfo.Home,
            UberscreenInfo.Services,
            UberscreenInfo.Activity,
            UberscreenInfo.Account,
        )
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route

        screens.forEach {item ->
            BottomNavigationItem(selected = currentDestination == item.route, onClick = { navController.navigate(item.route) {
            } },icon = { Icon(imageVector = item.icon, contentDescription = null) })

        }
    }
}

@Composable
fun Example_row() {
    Row {
        Box(modifier = Modifier.padding(16.dp)) {
            Column {
                Card(modifier = Modifier
                    .clickable { /* Handle card click if needed */ },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                        disabledContentColor = MaterialTheme.colorScheme.onSurface,
                        contentColor = MaterialTheme.colorScheme.onSurface)) {
                    Image(painter = painterResource(com.learnperk.LearnPerk.R.drawable.r11), contentDescription = "null")
                }
                Text(text = "Go with Uber Auto")
                Text(text = "Doorstep pickup, no bargaining", fontWeight = FontWeight.Light, fontSize = 15.sp)
            }
        }
        Box(modifier = Modifier.padding(16.dp)) {
            Column {
                Card(modifier = Modifier
                    .clickable { /* Handle card click if needed */ },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                        disabledContentColor = MaterialTheme.colorScheme.onSurface,
                        contentColor = MaterialTheme.colorScheme.onSurface)) {
                    Image(painter = painterResource(com.learnperk.LearnPerk.R.drawable.r12), contentDescription = "null")
                }
                Text(text = "Hop on Uber Auto")
                Text(text = "Move through traffic & save time", fontWeight = FontWeight.Light, fontSize = 15.sp)
            }
        }
        Box(modifier = Modifier.padding(16.dp)) {
            Column {
                Card(modifier = Modifier
                    .clickable { /* Handle card click if needed */ },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                        disabledContentColor = MaterialTheme.colorScheme.onSurface,
                        contentColor = MaterialTheme.colorScheme.onSurface)) {
                    Image(painter = painterResource(com.learnperk.LearnPerk.R.drawable.r13), contentDescription = "null")
                }
                Text(text = "Hop on a shuttle")
                Text(text = "Pre-book a seat, ride in comfort", fontWeight = FontWeight.Light, fontSize = 15.sp)
            }
        }
        Box(modifier = Modifier.padding(16.dp)) {
            Column {
                Card(modifier = Modifier
                    .clickable { /* Handle card click if needed */ },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                        disabledContentColor = MaterialTheme.colorScheme.onSurface,
                        contentColor = MaterialTheme.colorScheme.onSurface)) {
                    Image(painter = painterResource(com.learnperk.LearnPerk.R.drawable.r14), contentDescription = "null")
                }
                Text(text = "Try Group Rides")
                Text(text = "Ride with coworkers and save", fontWeight = FontWeight.Light)
            }
        }
    }

}

@Composable
fun FAB_Uber(navController: NavHostController) {
    Column {
        FloatingActionButton(modifier = Modifier.width(120.dp),onClick = { navController.navigate(ScreenInfo.Users.route) }, containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.primaryContainer) {
            Image(painterResource(id = R.drawable.lp_logo), "LP LOGO",modifier = Modifier.size(60.dp))
        }
    }
}

@Preview
@Composable
fun Pre_Example() {
    Example_row()
}