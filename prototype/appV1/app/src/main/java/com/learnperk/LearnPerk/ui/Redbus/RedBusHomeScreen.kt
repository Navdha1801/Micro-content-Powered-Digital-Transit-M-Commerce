package com.learnperk.LearnPerk.ui.Redbus

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.learnperk.LearnPerk.Navigation.MenuScreenInfo
import com.learnperk.LearnPerk.Navigation.RedbusscreenInfo
import com.learnperk.LearnPerk.Navigation.ScreenInfo
import com.learnperk.LearnPerk.R

@Composable
fun Redbushomescreen(navController: NavHostController) {
    BackHandler {
        navController.popBackStack(route = MenuScreenInfo.Menu.route, inclusive = false)
    }
    Scaffold(
        topBar = { TopNavBar(navController) },
        bottomBar = { Redbusbottomnavigationbar(navController = navController) },
        floatingActionButton = { Rebus_button(navController = navController)}
    ) {padding ->
        Column(Modifier.padding(padding)) {

        }
        RedBusContent()
    }
}

@Composable
fun RedBusContent() {
    Surface(
        color=Color.White
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.height(115.dp))
            }

            item {
                Text(text = "Bus Tickets", Modifier.padding(5.dp), fontWeight = FontWeight.ExtraBold, fontSize = 25.sp)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.searchtickets),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp)
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Button(onClick = { },
                        Modifier
                            .fillMaxWidth(0.9f), colors = ButtonDefaults.buttonColors(Color(0xFFEB3C3C))) {
                        Row {
                            Text(text = "Search buses")
                        }
                    }
                    Spacer(modifier = Modifier.padding(20.dp))
                }
            }


            item {
                Column {
                    Text(text = "Governament Buses", Modifier.padding(5.dp),fontWeight = FontWeight.ExtraBold, fontSize = 25.sp)
                    LazyRow() {
                        item {
                            Gov_buses1()
                        }
                        item {
                            Gov_buses2()
                        }
                        item {
                            Gov_buses3()
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(20.dp))
            }

            item {
                Column {
                    Text(text = "Offers", Modifier.padding(5.dp),fontWeight = FontWeight.ExtraBold, fontSize = 25.sp)
                    Text(text = "Get best deals with great offers", Modifier.padding(5.dp),fontWeight = FontWeight.Light)

                    LazyRow() {
                        item {
                            Offer_fun1()
                        }
                        item {
                            Offer_fun2()
                        }
                        item {
                            Offer_fun3()
                        }
                        item {
                            Offer_fun4()
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(70.dp))
            }
        }
    }
}

@Composable
fun Redbusbottomnavigationbar(navController : NavHostController) {
    BottomNavigation(backgroundColor = Color.White) {
        val screens = listOf(
            RedbusscreenInfo.Home,
            RedbusscreenInfo.Bookings,
            RedbusscreenInfo.Help,
            RedbusscreenInfo.Account
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
fun TopNavBar(navController: NavHostController) {
    LazyRow(Modifier.background(Color.White)) {
        item {
            Column(
                Modifier
                    .padding(10.dp)
                    .clickable { }) {
                Image(painter = painterResource(id = R.drawable.top_bus), contentDescription = "null")
                Text(text = "Bus Tickets", fontWeight = FontWeight.Light, fontSize = 10.sp)
            }
        }
        item {
            Column(
                Modifier
                    .padding(10.dp)
                    .clickable {  }) {
                Image(painter = painterResource(id = R.drawable.top_rail), contentDescription = "null")
                Text(text = "Train Tickets", fontWeight = FontWeight.Light, fontSize = 10.sp)
            }
        }
        item {
            Column(
                Modifier
                    .padding(15.dp)
                    .clickable { navController.navigate(ScreenInfo.Users.route) }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .border(3.dp, color = MaterialTheme.colorScheme.primary,shape = RoundedCornerShape(8.dp))
                        .size(width = 65.dp, height = 48.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lp_logo),
                        contentDescription = "null",
                        modifier = Modifier.size(45.dp)
                    )
                }
                Text(text = "LearnPerk", fontWeight = FontWeight.Light, fontSize = 10.sp,modifier = Modifier.padding(start = 8.dp))
            }
        }

        item {
            Column(
                Modifier
                    .padding(15.dp)
                    .clickable { }) {
                Image(painter = painterResource(id = R.drawable.top_metro), contentDescription = "null")
                Text(text = "Metro Tickets", fontWeight = FontWeight.Light, fontSize = 10.sp)
            }
        }
        item {
            Column(
                Modifier
                    .padding(20.dp)
                    .clickable { }) {
                Image(painter = painterResource(id = R.drawable.top_auto), contentDescription = "null")
                Text(text = "Auto Ride", fontWeight = FontWeight.Light, fontSize = 10.sp)
            }
        }
    }
}

@Composable
fun Gov_buses1() {
    ElevatedCard(
        modifier = Modifier
            .size(width = 300.dp, height = 300.dp)
            .padding(10.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Box(
            Modifier
                .weight(0.3f)
                .fillMaxWidth()) {
            Row(Modifier.fillMaxSize()) {
                Image(painter = painterResource(R.drawable.apsrtc), contentDescription = "null",
                    Modifier
                        .weight(0.4f)
                        .size(70.dp)
                        .padding(top = 10.dp))
                Text(text = "APSTRC",
                    Modifier
                        .weight(0.6f)
                        .padding(top = 10.dp), fontWeight = FontWeight.Bold)
            }

        }
        Divider(color = Color.Black, thickness = 0.8.dp)
        Box(Modifier.weight(0.7f)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "1539 services including Garuda,", fontSize = 12.sp)
                Text(text = "Garuda Plus and more", fontSize = 12.sp)

                Spacer(modifier = Modifier.padding(20.dp))
                Box(Modifier.weight(0.5f)) {
                    Row(
                        Modifier
                            .fillMaxWidth(0.9f)
                            .clip(shape = RoundedCornerShape(5.dp))
                            .background(Color(0xB6DEBAE7)), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        Image(painter = painterResource(R.drawable.smallredbus), contentDescription = null,Modifier.size(20.dp))
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Official booking partner of TSRTC", fontSize = 13.sp)
                    }
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .background(Color(0xB2FC99AB))
                        .size(30.dp), Alignment.Center) {
                    Text(text = "Get instant refund with UPI payments", fontWeight = FontWeight.Bold, fontSize = 10.sp)
                }
            }
        }

    }
}

@Composable
fun Gov_buses2() {
    ElevatedCard(
        modifier = Modifier
            .size(width = 300.dp, height = 300.dp)
            .padding(10.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Box(
            Modifier
                .weight(0.3f)
                .fillMaxWidth()) {
            Row(Modifier.fillMaxSize()) {
                Image(painter = painterResource(R.drawable.tsrtc), contentDescription = "null",
                    Modifier
                        .weight(0.4f)
                        .size(70.dp)
                        .padding(top = 10.dp))
                Text(text = "APSTRC",
                    Modifier
                        .weight(0.6f)
                        .padding(top = 10.dp), fontWeight = FontWeight.Bold)
            }

        }
        Divider(color = Color.Black, thickness = 0.8.dp)
        Box(Modifier.weight(0.7f)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "1450 services including Garuda Plus,", fontSize = 12.sp)
                Text(text = "Rajdhani and more", fontSize = 12.sp)

                Spacer(modifier = Modifier.padding(20.dp))
                Box(Modifier.weight(0.5f)) {
                    Row(
                        Modifier
                            .fillMaxWidth(0.9f)
                            .clip(shape = RoundedCornerShape(5.dp))
                            .background(Color(0xB6DEBAE7)), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        Image(painter = painterResource(R.drawable.smallredbus), contentDescription = null,Modifier.size(20.dp))
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Official booking partner of TSRTC", fontSize = 13.sp)
                    }
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .background(Color(0xB2FC99AB))
                        .size(30.dp), Alignment.Center) {
                    Text(text = "Get instant refund with UPI payments", fontWeight = FontWeight.Bold, fontSize = 10.sp)
                }
            }
        }
    }
}

@Composable
fun Gov_buses3() {
    ElevatedCard(
        modifier = Modifier
            .size(width = 300.dp, height = 300.dp)
            .padding(10.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Box(
            Modifier
                .weight(0.3f)
                .fillMaxWidth()) {
            Row(Modifier.fillMaxSize()) {
                Image(painter = painterResource(R.drawable.ksrtc), contentDescription = "null",
                    Modifier
                        .weight(0.4f)
                        .size(70.dp)
                        .padding(top = 10.dp))
                Text(text = "APSTRC",
                    Modifier
                        .weight(0.6f)
                        .padding(top = 10.dp), fontWeight = FontWeight.Bold)
            }

        }
        Divider(color = Color.Black, thickness = 0.8.dp)
        Box(Modifier.weight(0.7f)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "2100 services including Volvo Bus, AC &", fontSize = 12.sp)
                Text(text = "Non AC Bus and more", fontSize = 12.sp)

                Spacer(modifier = Modifier.padding(20.dp))
                Box(Modifier.weight(0.5f)) {
                    Row(
                        Modifier
                            .fillMaxWidth(0.9f)
                            .clip(shape = RoundedCornerShape(5.dp))
                            .background(Color(0xB6DEBAE7)), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        Image(painter = painterResource(R.drawable.smallredbus), contentDescription = null,Modifier.size(20.dp))
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Official booking partner of TSRTC", fontSize = 13.sp)
                    }
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .background(Color(0xB2FC99AB))
                        .size(30.dp), Alignment.Center) {
                    Text(text = "Get instant refund with UPI payments", fontWeight = FontWeight.Bold, fontSize = 10.sp)
                }
            }
        }

    }
}

@Composable
fun Offer_fun1() {
    ElevatedCard(
        modifier = Modifier
            .size(width = 300.dp, height = 200.dp)
            .padding(10.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row() {
            Column(
                Modifier.weight(0.4f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(R.drawable.superhit), contentDescription = "null",Modifier.size(300.dp))
            }
            Column(
                Modifier
                    .weight(0.6f)
                    .fillMaxHeight(),verticalArrangement = Arrangement.SpaceEvenly) {
                Row(
                    Modifier
                        .clip(shape = RoundedCornerShape(5.dp))
                        .background(Color.Blue), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "BUS", Modifier.padding(5.dp),fontWeight = FontWeight.Bold, color = Color.White, fontSize = 12.sp)
                }
                Box {
                    Column {
                        Text(text = "Save up to Rs 300 on", fontWeight = FontWeight.Bold)
                        Text(text = "AP, TS routes", fontWeight = FontWeight.Bold)
                        Text(text = "Valid till: 30 Apr", fontWeight = FontWeight.Light, fontSize = 10.sp)
                    }
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Button(onClick = {  }, colors = ButtonDefaults.buttonColors(containerColor = Color(
                    0xAEEB9E82
                )
                )) {
                    Text(text = "SUPERHIT", fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun Offer_fun2() {
    ElevatedCard(
        modifier = Modifier
            .size(width = 300.dp, height = 200.dp)
            .padding(10.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row() {
            Column(
                Modifier.weight(0.4f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(R.drawable.simpl), contentDescription = "null",Modifier.size(300.dp))
            }
            Column(
                Modifier
                    .weight(0.6f)
                    .fillMaxHeight(),verticalArrangement = Arrangement.SpaceEvenly) {
                Row(
                    Modifier
                        .clip(shape = RoundedCornerShape(5.dp))
                        .background(Color.Blue), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "RAILS", Modifier.padding(5.dp),fontWeight = FontWeight.Bold, color = Color.White, fontSize = 12.sp)
                }
                Box {
                    Column {
                        Text(text = "Flat RS 100 OFF on", fontWeight = FontWeight.Bold)
                        Text(text = "Train tickets", fontWeight = FontWeight.Bold)
                        Text(text = "Valid till: 30 Apr", fontWeight = FontWeight.Light, fontSize = 10.sp)
                    }
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Button(onClick = {  }, colors = ButtonDefaults.buttonColors(containerColor = Color(
                    0xAEEB9E82
                )
                )) {
                    Text(text = "SIMPLRB", fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun Offer_fun3() {
    ElevatedCard(
        modifier = Modifier
            .size(width = 300.dp, height = 200.dp)
            .padding(10.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row() {
            Column(
                Modifier.weight(0.4f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(R.drawable.kaveri), contentDescription = "null",Modifier.size(300.dp))
            }
            Column(
                Modifier
                    .weight(0.6f)
                    .fillMaxHeight(),verticalArrangement = Arrangement.SpaceEvenly) {
                Row(
                    Modifier
                        .clip(shape = RoundedCornerShape(5.dp))
                        .background(Color.Blue), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "BUS", Modifier.padding(5.dp),fontWeight = FontWeight.Bold, color = Color.White, fontSize = 12.sp)
                }
                Box {
                    Column {
                        Text(text = "Save up to Rs 300 V", fontWeight = FontWeight.Bold)
                        Text(text = "Kaveri Travels bus ...", fontWeight = FontWeight.Bold)
                        Text(text = "Valid till: 30 Apr", fontWeight = FontWeight.Light, fontSize = 10.sp)
                    }
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Button(onClick = {  }, colors = ButtonDefaults.buttonColors(containerColor = Color(
                    0xAEEB9E82
                )
                )) {
                    Text(text = "KAVERI300", fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun Offer_fun4() {
    ElevatedCard(
        modifier = Modifier
            .size(width = 300.dp, height = 200.dp)
            .padding(10.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row() {
            Column(
                Modifier.weight(0.4f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(R.drawable.freshbus), contentDescription = "null",Modifier.size(300.dp))
            }
            Column(
                Modifier
                    .weight(0.6f)
                    .fillMaxHeight(),verticalArrangement = Arrangement.SpaceEvenly) {
                Row(
                    Modifier
                        .clip(shape = RoundedCornerShape(5.dp))
                        .background(Color.Blue), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "BUS", Modifier.padding(5.dp),fontWeight = FontWeight.Bold, color = Color.White, fontSize = 12.sp)
                }
                Box {
                    Column {
                        Text(text = "Save up to Rs 300", fontWeight = FontWeight.Bold)
                        Text(text = "Fresh Bus bus oper...", fontWeight = FontWeight.Bold)
                        Text(text = "Valid till: 30 Apr", fontWeight = FontWeight.Light, fontSize = 10.sp)
                    }
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Button(onClick = {  }, colors = ButtonDefaults.buttonColors(containerColor = Color(
                    0xAEEB9E82
                )
                )) {
                    Text(text = "FRESHDEAL", fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun Rebus_button(navController: NavHostController) {
    ExtendedFloatingActionButton(
        onClick = { navController.navigate(ScreenInfo.Users.route) },containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.primaryContainer
    ){
        Image(painterResource(id = R.drawable.lp_logo), "LP LOGO",modifier = Modifier.size(60.dp))
    }
}

@Preview
@Composable
fun Bus_exam() {
    Offer_fun1()
}