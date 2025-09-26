package com.artemklymenko.newsapp.presentation.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Categories : Screen("categories")
}