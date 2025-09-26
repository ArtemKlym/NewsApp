package com.artemklymenko.presentation.home

import com.artemklymenko.domain.models.News

data class HomeUiState (
    val news: News? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
)