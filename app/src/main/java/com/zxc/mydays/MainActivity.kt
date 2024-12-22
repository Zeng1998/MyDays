package com.zxc.mydays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zxc.mydays.memo.MemoDetailScreen
import com.zxc.mydays.ui.theme.MyDaysTheme

fun scaleIntoContainer(): EnterTransition {
    return scaleIn(
        animationSpec = tween(220, delayMillis = 90),
        initialScale = 0.9f
    ) + fadeIn(animationSpec = tween(220, delayMillis = 90))
}

fun scaleOutOfContainer(): ExitTransition {
    return scaleOut(
        animationSpec = tween(
            durationMillis = 220,
            delayMillis = 90
        ), targetScale = 0.9f
    ) + fadeOut(tween(delayMillis = 90))
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            MyDaysTheme {
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(navController)
                    }
                    composable(
                        route = "memo/detail",
                        enterTransition = { scaleIntoContainer() },
                        exitTransition = { scaleOutOfContainer() }) {
                        MemoDetailScreen()
                    }
                }
            }
        }
    }
}