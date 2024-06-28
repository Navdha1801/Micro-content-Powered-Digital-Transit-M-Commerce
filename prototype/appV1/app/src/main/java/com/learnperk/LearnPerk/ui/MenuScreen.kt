package com.learnperk.LearnPerk.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.learnperk.LearnPerk.Navigation.RedbusscreenInfo
import com.learnperk.LearnPerk.Navigation.ScreenInfo
import com.learnperk.LearnPerk.Navigation.UberscreenInfo
import com.learnperk.LearnPerk.R
import java.time.format.TextStyle

@Composable
fun Menuscreen(navController: NavHostController) {
    Surface(
        color= Color.Black,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = 50.dp,
                    start = 50.dp
                )
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier) {
                    // This empty Box will occupy the available space to push the text to the right
                }
                Text(
                    text = "Experience With",
                    color = Color(198, 165, 95),
                    fontSize = 35.sp, // Using sp for font size
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
                // Add your other composables within the Row here
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(60.dp)
            ) {
                ServiceButton(
                    serviceName = "RedBus",
                    iconResId = R.drawable.irctc,
                    ScreenInfo.Home.route,
                    navController
                )
                ServiceButton(
                    serviceName = "Uber",
                    iconResId = R.drawable.uber,
                    UberscreenInfo.Home.route,
                    navController
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(60.dp)
            ) {
                ServiceButton(
                    serviceName = "RedBus",
                    iconResId = R.drawable.redbus,
                    RedbusscreenInfo.Home.route,
                    navController
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .clickable {  }
                            .size(100.dp)
                            .border(3.dp, Color(red=220,green=165,blue=95)) // Add red border
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color.Black)
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.indian_railways),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize() // Ensures the image fills the entire space of the Box
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(60.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .clickable {  }
                            .size(100.dp)
                            .border(3.dp, Color(red=220,green=165,blue=95)) // Add red border
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color.Black)
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ola),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize() // Ensures the image fills the entire space of the Box
                            )
                        }
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .clickable {  }
                            .size(100.dp)
                            .border(3.dp, Color(red=220,green=165,blue=95)) // Add red border
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color.Black)
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.united_airlines),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize() // Ensures the image fills the entire space of the Box
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(60.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .clickable {  }
                            .size(100.dp)
                            .border(3.dp, Color(red=220,green=165,blue=95)) // Add red border
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color.Black)
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.hydmetro),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize() // Ensures the image fills the entire space of the Box
                            )
                        }
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .clickable {  }
                            .size(100.dp)
                            .border(3.dp, Color(red=220,green=165,blue=95)) // Add red border
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color.Black)
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.one_delhi),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize() // Ensures the image fills the entire space of the Box
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ServiceButton(serviceName: String, iconResId: Int, route: String, navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clickable { navController.navigate(route) }
                .size(100.dp)
                .border(3.dp, Color(red=220,green=165,blue=95)) // Add red border
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize() // Ensures the image fills the entire space of the Box
                )
            }
        }
    }
}



@Preview
@Composable
fun PreviewButtonLayout() {

}