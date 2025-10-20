package com.example.apphuerta_grupo4.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apphuerta_grupo4.ui.screens.ApartadoProducto
import com.example.apphuerta_grupo4.ui.screens.DetalleProducto
import com.example.apphuerta_grupo4.ui.screens.PaginaConfiguracion
import com.example.apphuerta_grupo4.ui.screens.PaginaInicio
import com.example.apphuerta_grupo4.ui.screens.PaginaRegistro
import com.example.apphuerta_grupo4.ui.screens.PaginaResumenCuenta
import com.example.apphuerta_grupo4.viewmodels.MainViewModel
import com.example.apphuerta_grupo4.viewmodels.ProductoViewModel
import com.example.apphuerta_grupo4.viewmodels.UsuarioViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = viewModel()
    val usuarioViewModel: UsuarioViewModel = viewModel()
    val productoViewModel: ProductoViewModel = viewModel()

    LaunchedEffect(Unit) {
        mainViewModel.setNavController(navController)
        mainViewModel.navigationEvent.collectLatest { event ->
            when (event) {
                is NavigationEvent.NavigateTo -> navController.navigate(event.route.route)
                is NavigationEvent.NavigateToProductDetail -> {
                    navController.navigate("detalle_producto/${event.productoNombre}")
                }
                else -> {}
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable("pagina_inicio") {
            PaginaInicio(viewModel = mainViewModel, usuarioViewModel = usuarioViewModel)
        }
        composable("pagina_configuracion") {
            PaginaConfiguracion(viewModel = mainViewModel, usuarioViewModel = usuarioViewModel)
        }
        composable("apartado_productos") {
            ApartadoProducto(mainViewModel = mainViewModel, productoViewModel = productoViewModel, usuarioViewModel = usuarioViewModel)
        }
        composable("pagina_registro") {
            PaginaRegistro(viewModel = mainViewModel, usuarioViewModel = usuarioViewModel)
        }
        composable("resumen_cuenta") {
            PaginaResumenCuenta(viewModel = mainViewModel, usuarioViewModel = usuarioViewModel)
        }
        composable(
            route = "detalle_producto/{productoNombre}",
            arguments = listOf(navArgument("productoNombre") { type = NavType.StringType })
        ) { backStackEntry ->
            val productoNombre = backStackEntry.arguments?.getString("productoNombre") ?: ""
            DetalleProducto(
                mainViewModel = mainViewModel,
                productoViewModel = productoViewModel,
                usuarioViewModel = usuarioViewModel,
                productoNombre = productoNombre
            )
        }
    }
}