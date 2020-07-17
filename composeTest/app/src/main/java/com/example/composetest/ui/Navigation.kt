package com.example.composetest.ui

import androidx.annotation.MainThread
import androidx.compose.MutableState
import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.setValue
import androidx.lifecycle.ViewModel
import com.example.composetest.ui.Screen.Home
import com.example.composetest.ui.ScreenName.*

enum class ScreenName { HOME, ARTICLE}

sealed class Screen(val id: ScreenName) {
    object Home : Screen(HOME)
    data class Article(val postId: String) : Screen(ARTICLE)
}

class NavigationViewModel() : ViewModel() {
    var currentScreen: Screen by getMutableStateOf<Screen>(default = Home)
        private set

    @MainThread
    fun onBack(): Boolean {
        val wasHandled = currentScreen != Home
        currentScreen = Home
        return wasHandled
    }

    @MainThread
    fun navigateTo(screen: Screen) {
        currentScreen = screen
    }
}

fun <T> getMutableStateOf(default: T): MutableState<T> {
    val state = mutableStateOf(default)
    state.value = default
    return state
}

