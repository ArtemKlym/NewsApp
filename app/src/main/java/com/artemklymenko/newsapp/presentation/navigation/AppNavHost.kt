package com.artemklymenko.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.artemklymenko.presentation.categories.CategoriesScreen
import com.artemklymenko.presentation.categories.CategoriesViewModel
import com.artemklymenko.presentation.home.HomeScreen
import com.artemklymenko.presentation.home.HomeViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val state = viewModel.uiState.collectAsState()
            HomeScreen(
                state = state.value,
                modifier = modifier,
                onEvent = viewModel::onEvent,
                onNavigateToCategories = { navController.navigate(Screen.Categories.route) }
            )
        }
        composable(Screen.Categories.route) {
            val viewModel = hiltViewModel<CategoriesViewModel>()
            val state = viewModel.uiState.collectAsState()
            CategoriesScreen(
                state = state.value,
                modifier = modifier,
                onEvent = viewModel::onEvent,
                onBack = { navController.popBackStack() }
            )
        }
    }
}