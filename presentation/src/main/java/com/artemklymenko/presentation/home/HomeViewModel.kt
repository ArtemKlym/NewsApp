package com.artemklymenko.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemklymenko.domain.models.News
import com.artemklymenko.domain.repository.NewsResult
import com.artemklymenko.domain.usecases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val searchQuery = MutableStateFlow("")

    init {
        observeSearchQuery()
    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.OnSearchTriggered -> {
                search(searchQuery.value)
            }
            is HomeUiEvent.SearchNews -> {
                searchQuery.value = event.querySearchNews
                _uiState.update { it.copy(searchQuery = event.querySearchNews) }
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        viewModelScope.launch {
            searchQuery
                .debounce(700)
                .distinctUntilChanged()
                .collectLatest { query ->
                    if (query.isNotBlank()) {
                        search(query)
                    }
                }
        }
    }

    private fun search(querySearchNews: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val newsResult = getNewsUseCase.invoke(querySearchNews)

            val newState = newsResult.map(object : NewsResult.Mapper<HomeUiState> {
                override fun mapSuccess(news: News): HomeUiState {
                    return _uiState.value.copy(
                        isLoading = false,
                        news = news,
                        error = null
                    )
                }

                override fun mapError(message: String): HomeUiState {
                    return _uiState.value.copy(
                        isLoading = false,
                        error = message
                    )
                }
            })

            _uiState.value = newState
        }
    }
}