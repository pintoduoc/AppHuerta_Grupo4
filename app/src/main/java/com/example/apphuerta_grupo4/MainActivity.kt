package com.example.apphuerta_grupo4

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apphuerta_grupo4.navigation.AppNavigation
import com.example.apphuerta_grupo4.navigation.NavigationEvent
import com.example.apphuerta_grupo4.navigation.Screen
import com.example.apphuerta_grupo4.ui.screens.ApartadoProducto
import com.example.apphuerta_grupo4.ui.screens.PaginaConfiguracion
import com.example.apphuerta_grupo4.ui.screens.PaginaInicio
import com.example.apphuerta_grupo4.ui.screens.PaginaRegistro
import com.example.apphuerta_grupo4.ui.theme.AppHuerta_Grupo4Theme
import com.example.apphuerta_grupo4.viewmodels.MainViewModel
import com.example.apphuerta_grupo4.viewmodels.UsuarioViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)

        enableEdgeToEdge()
        setContent {
            AppHuerta_Grupo4Theme (darkTheme = isDarkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}