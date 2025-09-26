package com.artemklymenko.presentation.categories

import com.artemklymenko.domain.models.News


data class CategoriesUiState(
    val news: News? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedCategory: String? = null
)
