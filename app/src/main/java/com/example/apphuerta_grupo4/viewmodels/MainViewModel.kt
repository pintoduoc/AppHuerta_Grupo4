package com.example.apphuerta_grupo4.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.apphuerta_grupo4.navigation.NavigationEvent
import com.example.apphuerta_grupo4.navigation.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var navController: NavController? = null

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    val navigationEvent: SharedFlow<NavigationEvent> = _navigationEvent.asSharedFlow()

    fun onNavigationEvent(event: NavigationEvent) {
        viewModelScope.launch {
            _navigationEvent.emit(event)
        }
    }

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun navigateTo(screen : Screen) {
        navController?.navigate(screen.route)
    }

    fun navigateBack() {
        navController?.popBackStack()
    }

    fun navigateUp() {
        navController?.navigateUp()
    }
}