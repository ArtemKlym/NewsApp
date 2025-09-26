package com.artemklymenko.presentation.home

sealed interface HomeUiEvent {
    data class SearchNews(val querySearchNews: String): HomeUiEvent
    data object OnSearchTriggered : HomeUiEvent
}