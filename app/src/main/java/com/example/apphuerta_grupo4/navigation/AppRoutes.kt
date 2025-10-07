package com.example.apphuerta_grupo4.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("pagina_inicio")
    data object Settings : Screen("pagina_configuracion")
    data object Productos : Screen("apartado_productos")


    data class Detail(val itemId: String) : Screen("detalle_producto/{itemId}") {
        fun buildRoute() : String {
            return route.replace("{itemId}", itemId)
        }
    }
}