package com.example.apphuerta_grupo4.navigation

sealed class NavigationEvent {
    data class NavigateTo(
        val route : Screen,
        val popUpToRoute : Screen? = null,
        val inclusive : Boolean = false,
        val singleTop : Boolean = false
    ) : NavigationEvent()

    data class NavigateToProductDetail(val productoNombre: String) : NavigationEvent()

    object PopBackStack : NavigationEvent()
    object NavigateUp : NavigationEvent()
}