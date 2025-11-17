package com.example.apphuerta_grupo4.navigation

const val SPLASH = "splash"
const val INICIO = "inicio"

sealed class Screen(val route: String) {
    data object Home : Screen(INICIO)
    data object Settings : Screen("pagina_configuracion")
    data object Productos : Screen("apartado_productos")

    data object Registro : Screen("pagina_registro")

    data object ResumenCuenta : Screen("resumen_cuenta")

    data object DetalleProducto : Screen("detalle_producto")

}