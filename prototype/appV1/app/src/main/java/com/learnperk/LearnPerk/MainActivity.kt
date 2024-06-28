package com.learnperk.LearnPerk

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.learnperk.LearnPerk.Navigation.RootNavGraph
import com.learnperk.LearnPerk.databinding.ActivityMainBinding
import com.learnperk.LearnPerk.ui.InterestSelectionScreen
import com.learnperk.LearnPerk.ui.enableOfflinePersistence
import com.learnperk.LearnPerk.ui.themes.BottomBarTheme

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContent{
            enableOfflinePersistence();
            BottomBarTheme{
                RootNavGraph(navController = rememberNavController());
            }
        }
    }
}