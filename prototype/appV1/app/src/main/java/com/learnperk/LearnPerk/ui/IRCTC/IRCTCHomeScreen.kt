package com.learnperk.LearnPerk.ui.IRCTC

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.learnperk.LearnPerk.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.learnperk.LearnPerk.Navigation.ScreenInfo
import com.learnperk.LearnPerk.bottomBar.BottomNavigationBar


@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(navController = rememberNavController())
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFEE832C)
                ),
                title = { Text(text = "IRCTC RAIL") },
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                    }
                })
        },
        bottomBar = { BottomNavigationBar(navController = navController)},
        floatingActionButton = { Lp_button(navController) }
    ) { values ->
        Column( modifier = Modifier.padding(values)) {
            PickupDestination(modifier = Modifier, navController = navController)
        }

    }
}

@Composable
fun PickupDestination(modifier: Modifier, navController: NavHostController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,

        ){
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .size(20.dp, 10.dp))
        Row(Modifier.fillMaxWidth()) {
            Column(Modifier.weight(1f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.train), contentDescription = "null",Modifier.size(30.dp))
                }
                Box(Modifier.fillMaxWidth()) {
                    Text(text = "Train", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                }

            }

            Column(Modifier.weight(1f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.flight), contentDescription = "null",Modifier.size(30.dp))
                }
                Box(Modifier.fillMaxWidth()) {
                    Text(text = "Flights", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                }
            }


            Column(Modifier.weight(1f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.bus), contentDescription = "null",Modifier.size(30.dp))
                }
                Box(Modifier.fillMaxWidth()) {
                    Text(text = "Bus", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                }
            }


            Column(Modifier.weight(1f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.hotel), contentDescription = "null",Modifier.size(30.dp))
                }
                Box(Modifier.fillMaxWidth()) {
                    Text(text = "Hotel", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                }
            }


            Column(Modifier.weight(1f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.food), contentDescription = "null",Modifier.size(30.dp))
                }
                Column {
                    Box(Modifier.fillMaxWidth()) {
                        Text(text = "Order Food", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                    }
                }
                Column {
                    Box(Modifier.fillMaxWidth()) {
                        Text(text = "In Train", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                    }
                }
            }


        }
        Row(Modifier.fillMaxWidth()) {
            Column(Modifier.weight(0.2f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.disha), contentDescription = "null",Modifier.size(30.dp))
                }
                Box(Modifier.fillMaxWidth()) {
                    Text(text = "Ask disha", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                }

            }

            Column(Modifier.weight(0.2f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.tourism), contentDescription = "null",Modifier.size(30.dp))
                }
                Box(Modifier.fillMaxWidth()) {
                    Text(text = "Tourism", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                }
            }


            Column(Modifier.weight(0.2f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.rooms), contentDescription = "null",Modifier.size(30.dp))
                }
                Column {
                    Box(Modifier.fillMaxWidth()) {
                        Text(text = "Retiring", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                    }
                }
                Column {
                    Box(Modifier.fillMaxWidth()) {
                        Text(text = "Room", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                    }
                }
            }

            Column(Modifier.weight(0.2f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { navController.navigate(ScreenInfo.Users.route) },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    // Wrapping the Image with a Border
                    Box(
                        modifier = Modifier
                            .size(width=50.dp,height=38.dp) // Adjust the size as needed
                            .border(width = 2.dp, color = MaterialTheme.colorScheme.primary,shape = RoundedCornerShape(8.dp)) // Border settings
                    ) {
                        Image(
                            painter = painterResource(R.drawable.lp_logo),
                            contentDescription = "null",
                            modifier = Modifier.size(30.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
                Box(Modifier.fillMaxWidth()) {
                    Text(text = "LearnPerk", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                }
            }


            Column(Modifier.weight(0.2f)) {

            }
        }
        Row(Modifier.fillMaxWidth()) {
            Column(Modifier.weight(0.2f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.gifts), contentDescription = "null",Modifier.size(30.dp))
                }
                Box(Modifier.fillMaxWidth()) {
                    Text(text = "E-gifts", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                }

            }

            Column(Modifier.weight(0.2f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.youtube), contentDescription = "null",Modifier.size(30.dp))
                }
                Column {
                    Box(Modifier.fillMaxWidth()) {
                        Text(text = "IRCTC on", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                    }
                }
                Column {
                    Box(Modifier.fillMaxWidth()) {
                        Text(text = "youtube", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                    }
                }
            }

            Column(Modifier.weight(0.2f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.dicounts), contentDescription = "null",Modifier.size(30.dp))
                }
                Box(Modifier.fillMaxWidth()) {
                    Text(text = "Daily Deals", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                }
            }

            Column(Modifier.weight(0.4f)) {

            }
        }
        Spacer(modifier = Modifier

            .fillMaxWidth()
            .size(20.dp, 10.dp))
        Spacer(modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth()
            .size(20.dp, 15.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(text = "RECHARGE", modifier = Modifier.align(Alignment.Center))
        }
        Row(Modifier.fillMaxWidth()) {
            Column(Modifier.weight(1f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.mobile), contentDescription = "null",Modifier.size(30.dp))
                }
                Box(Modifier.fillMaxWidth()) {
                    Text(text = "Mobile", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                }

            }

            Column(Modifier.weight(1f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.dth), contentDescription = "null",Modifier.size(30.dp))
                }
                Box(Modifier.fillMaxWidth()) {
                    Text(text = "DTH", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                }
            }


            Column(Modifier.weight(1f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.playstore), contentDescription = "null",Modifier.size(30.dp))
                }
                Box(Modifier.fillMaxWidth()) {
                    Text(text = "Google Play", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                }
            }


            Column(Modifier.weight(1f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.fastag), contentDescription = "null",Modifier.size(30.dp))
                }
                Box(Modifier.fillMaxWidth()) {
                    Text(text = "Fastag", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                }
            }


            Column(Modifier.weight(1f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.metro), contentDescription = "null",Modifier.size(30.dp))
                }
                Box(Modifier.fillMaxWidth()) {
                    Text(text = "Metro", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                }
            }


        }
        Spacer(modifier = Modifier

            .fillMaxWidth()
            .size(20.dp, 10.dp))
        Spacer(modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth()
            .size(20.dp, 15.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(text = "PAY BILLS", modifier = Modifier.align(Alignment.Center))
        }
        Row(Modifier.fillMaxWidth()) {
            Column(Modifier.weight(1f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.elctricity), contentDescription = "null",Modifier.size(30.dp))
                }
                Box(Modifier.fillMaxWidth()) {
                    Text(text = "Electricity", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                }

            }

            Column(Modifier.weight(1f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.cylinder), contentDescription = "null",Modifier.size(30.dp))
                }
                Column {
                    Box(Modifier.fillMaxWidth()) {
                        Text(text = "Gas", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                    }
                }
                Column {
                    Box(Modifier.fillMaxWidth()) {
                        Text(text = "Cylinder", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                    }
                }
            }

            Column(Modifier.weight(1f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.crerditcard), contentDescription = "null",Modifier.size(30.dp))
                }
                Box(Modifier.fillMaxWidth()) {
                    Text(text = "Credit Card", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                }
            }

            Column(Modifier.weight(1f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.postpaid), contentDescription = "null",Modifier.size(30.dp))
                }
                Column {
                    Box(Modifier.fillMaxWidth()) {
                        Text(text = "Mobile", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                    }
                }
                Column {
                    Box(Modifier.fillMaxWidth()) {
                        Text(text = "Postpaid", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                    }
                }
            }


            Column(Modifier.weight(1f)) {
                SmallFloatingActionButton(
                    containerColor = Color.White,
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Image(painter = painterResource(R.drawable.broadband), contentDescription = "null",Modifier.size(30.dp))
                }
                Box(Modifier.fillMaxWidth()) {
                    Text(text = "Broad band", Modifier.align(Alignment.Center), style = TextStyle(fontSize = 13.sp))
                }
            }

        }
        Spacer(modifier = Modifier

            .fillMaxWidth()
            .size(20.dp, 10.dp))
        Spacer(modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth()
            .size(20.dp, 15.dp))
        Spacer(modifier = Modifier

            .fillMaxWidth()
            .size(20.dp, 30.dp))
//        Button(onClick = { navController.navigate(ScreenInfo.Users.route) },
//            modifier = Modifier
//                .fillMaxWidth(0.9f)
//                .padding(0.dp, 10.dp)
//        ) {
//            Image(painterResource(id = R.drawable.lp_logo), "LP LOGO",modifier = Modifier.size(40.dp))
//        }

    }
}

@Composable
fun Lp_button(navController: NavHostController) {
    ExtendedFloatingActionButton(
        onClick = { navController.navigate(ScreenInfo.Users.route) },containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.primaryContainer
    ){
        Image(painterResource(id = R.drawable.lp_logo), "LP LOGO",modifier = Modifier.size(60.dp))
    }
}


@Composable
fun Example() {
    Box(modifier = Modifier) {
        Column {
            SmallFloatingActionButton(
                onClick = { },
                modifier = Modifier
            ) {
                Image(painter = painterResource(R.drawable.train), contentDescription = "null",Modifier.size(30.dp))
            }
            Box(Modifier) {
                Text(text = "train", Modifier.align(Alignment.Center))

            }

        }

    }
}

@Preview
@Composable
fun For_preview() {
    Example()
}