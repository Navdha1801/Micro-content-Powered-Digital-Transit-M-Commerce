package com.learnperk.LearnPerk.ui.themes

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    background = Color(0xFFFFFFFF),        // White background
    surface = Color(0xFFF5F5F5),           // Light gray surface
    primary = Color(0xFF3C1186),           // Violet primary color
    secondary = Color(0xFFFFEB3B),         // Bright yellow secondary color
    onPrimary = Color.White,               // White text on primary color
    onSecondary = Color.Black,             // Black text on secondary color
    onTertiary = Color.Black,              // Black text on tertiary color
    onBackground = Color.Black,            // Black text on background color
    onSurface = Color.Black,               // Black text on surface color
    surfaceVariant = Color(0x689A7DCE)     // Light gray surface variant
)


@Composable
fun BottomBarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}