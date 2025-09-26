package com.artemklymenko.presentation.categories

sealed interface CategoriesUiEvent {
    data class OnCategorySelected(val category: String): CategoriesUiEvent
}