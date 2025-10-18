
package com.example.apphuerta_grupo4.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.apphuerta_grupo4.navigation.Screen
import com.example.apphuerta_grupo4.ui.shared.AppScaffold
import com.example.apphuerta_grupo4.viewmodels.MainViewModel
import com.example.apphuerta_grupo4.viewmodels.UsuarioViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaginaConfiguracion(
    viewModel : MainViewModel,
    usuarioViewModel: UsuarioViewModel
) {
    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    }
    var isDarkMode by remember {
        mutableStateOf(sharedPreferences.getBoolean("dark_mode", false))
    }

    AppScaffold(
        viewModel = viewModel,
        currentScreen = Screen.Settings,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = "Modo Oscuro",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Switch(
                            checked = isDarkMode,
                            onCheckedChange = { checked ->
                                isDarkMode = checked
                                sharedPreferences.edit()
                                    .putBoolean("dark_mode", checked)
                                    .apply()
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
