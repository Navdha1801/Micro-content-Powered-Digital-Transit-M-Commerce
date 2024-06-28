package com.learnperk.LearnPerk.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.learnperk.LearnPerk.Navigation.ScreenInfo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants


@Composable
fun YoutubeScreen(
    navController: NavHostController,
    User: Int,
    videoId: String,
    modifier: Modifier
) {
    var isPlayerInitialized by remember { mutableStateOf(false) }
    var showGoBackDialog by remember { mutableStateOf(false) }

    fun onPlayerStateChange(state: PlayerConstants.PlayerState) {
        if (state == PlayerConstants.PlayerState.ENDED) {
            showGoBackDialog = true
        }
    }

    BackHandler {
        navController.navigate(ScreenInfo.LPHome.route + "/${User}/true")
    }

    // Blur background
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AndroidView(
            factory = { context ->
                val youtubeView = YouTubePlayerView(context)
                youtubeView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        isPlayerInitialized = true
                        youTubePlayer.loadVideo(videoId, 0f)
                    }

                    override fun onStateChange(
                        youTubePlayer: YouTubePlayer,
                        state: PlayerConstants.PlayerState
                    ) {
                        onPlayerStateChange(state)
                    }
                })
                youtubeView
            },
            modifier = modifier,
            update = { youtubeView ->
                if (isPlayerInitialized) {
                    youtubeView.enterFullScreen()
                }
            }
        )

        if (showGoBackDialog) {
            AlertDialog(
                onDismissRequest = {
                    showGoBackDialog = false
                    navController.navigate(ScreenInfo.LPHome.route+ "/${User}/true")
                },
                title = { Text(text = "You have earned all the LearnCoins from this video. Go back to Home screen to watch more videos.", color = Color.White) },
                confirmButton = {
                    Button(
                        onClick = {
                            showGoBackDialog = false
                            // Navigate to a screen when OK is clicked
                            navController.navigate(ScreenInfo.LPHome.route + "/${User}/true")
                        } ,colors = ButtonDefaults.buttonColors(backgroundColor =  Color(red = 198, green = 165, blue = 95))
                    ) {
                        Text(text = "Ok", color = Color.Black)

                    }
                } ,
                backgroundColor = Color.Black
            )
        }
    }
}
