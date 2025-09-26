package com.artemklymenko.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemklymenko.domain.models.News
import com.artemklymenko.domain.repository.NewsResult
import com.artemklymenko.domain.usecases.GetNewsByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getNewsByCategoryUseCase: GetNewsByCategoryUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(CategoriesUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: CategoriesUiEvent){
        when(event) {
            is CategoriesUiEvent.OnCategorySelected -> {
                _uiState.update { it.copy(selectedCategory = event.category) }
                searchByCategory(event.category)
            }
        }
    }

    private fun searchByCategory(category: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val newsResult = getNewsByCategoryUseCase.invoke(category)
            val newState = newsResult.map(object : NewsResult.Mapper<CategoriesUiState> {
                override fun mapSuccess(news: News): CategoriesUiState {
                    return _uiState.value.copy(
                        news = news,
                        isLoading = false,
                        error = null
                    )
                }

                override fun mapError(message: String): CategoriesUiState {
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