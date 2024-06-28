package com.learnperk.LearnPerk.ui

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.learnperk.LearnPerk.R
import com.learnperk.LearnPerk.Navigation.ScreenInfo
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import coil.request.ImageRequest
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Usersdisplay(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(red=210,green=165,blue=95),
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black,
                ),
                title = { Text(text = "Users", fontWeight = FontWeight.Bold, fontSize = 25.sp)},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go back")
                    }
                },
            )
        }
    ) {paddingValues ->
        Row(Modifier.padding(paddingValues)) {

        }
        Profiles(navController = navController)
    }
}
@Composable
fun Profiles(navController: NavHostController) {
    val user1 = remember {
        mutableStateOf(User("",0, emptyList<String>(), emptyList<String>(), emptyList<String>()))
    }
    val user2 = remember {
        mutableStateOf(User("",0, emptyList<String>(), emptyList<String>(), emptyList<String>()))
    }
    FetchUser(1) { user ->
        user1.value = user
    }
    FetchUser(2) { user ->
        user2.value = user
    }
    ProfileDisplay(navController = navController, user1 = user1.value, user2 = user2.value)

}

@Composable
fun ProfileDisplay(navController: NavHostController,user1: User, user2: User) {
    var golden=Color(red = 198, green = 165, blue = 95)
    BackHandler {
        navController.popBackStack()
    }
    Surface(
        color = Color.Black,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(Modifier) {
                Text(
                    text = "Log-In As: ",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = golden
                    )
                )

            }

            Spacer(modifier = Modifier.padding(10.dp))
            Row {
                Column {
                    val request = ImageRequest.Builder(LocalContext.current)
                        .data(user1.imgUrl)
                        .build()
                    val painter1 = rememberImagePainter(request = request)

                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .border(BorderStroke(1.dp, Color.Red), CircleShape)
                            .clickable { navController.navigate(ScreenInfo.LPHome.route + "/1/false") }
                    ) {
                        Image(
                            painter = painter1,
                            contentDescription = "lakshmi",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = "Lakshmi",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = golden // Change the color to white or any other color you prefer
                        )
                    )


                }

                Spacer(modifier = Modifier.padding(15.dp))

                Column {
                    val request = ImageRequest.Builder(LocalContext.current)
                        .data(user2.imgUrl)
                        .build()
                    val painter2: Painter = rememberImagePainter(request = request)
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .border(BorderStroke(1.dp, Color.Red), CircleShape)
                            .clickable { navController.navigate(ScreenInfo.LPHome.route + "/2/false") }
                    ) {
                        Image(
                            painter = painter2,
                            contentDescription = "lakshmi",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    //Text(text = "Lisa", Modifier.align(Alignment.CenterHorizontally))
                    Text(
                        text = "Lisa",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = golden // Change the color to white or any other color you prefer
                        )
                    )

                }

            }

            Spacer(modifier = Modifier.padding(20.dp))

            Column(Modifier) {
                // Instagram Sign-in Button
                Button(
                    onClick = {
                        // Handle Instagram sign-in
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = golden,
                        containerColor = Color.Black
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .height(80.dp)
                        .width(280.dp)
                        .padding(10.dp)
                        .border(width = 1.dp, color = golden, shape = RoundedCornerShape(10.dp))
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.instagram_logo),
                            contentDescription = "Instagram",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Sign-In via Instagram", color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Google Sign-in Button
                Button(
                    onClick = {
                        // Handle Google sign-in
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = golden
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .height(80.dp)
                        .width(280.dp)
                        .padding(10.dp)
                        .border(width = 1.dp, color = golden, shape = RoundedCornerShape(10.dp))
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.google_logo),
                            contentDescription = "Google",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Sign-In via Google", color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.padding(20.dp))


        }


    }

}