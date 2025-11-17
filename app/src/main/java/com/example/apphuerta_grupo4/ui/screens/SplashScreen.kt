package com.example.apphuerta_grupo4.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.apphuerta_grupo4.R
import android.view.animation.OvershootInterpolator
import androidx.compose.ui.graphics.graphicsLayer
import com.example.apphuerta_grupo4.navigation.INICIO
import com.example.apphuerta_grupo4.navigation.SPLASH

@Composable
fun SplashScreen(navController: NavController) {

    // Animación de escala
    val scale = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1200,
                easing = {
                    OvershootInterpolator(3f).getInterpolation(it)
                }
            )
        )
        delay(1200)
        navController.navigate(INICIO) {
            popUpTo(SPLASH) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_huertohogar),
            contentDescription = "Logo",
            modifier = Modifier
                .size(260.dp)
                .graphicsLayer(scaleX = scale.value, scaleY = scale.value)
        )
    }
}
