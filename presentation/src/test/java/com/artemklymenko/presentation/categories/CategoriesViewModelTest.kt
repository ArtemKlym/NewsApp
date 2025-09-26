package com.artemklymenko.presentation.categories

import com.artemklymenko.domain.repository.NewsResult
import com.artemklymenko.domain.usecases.GetNewsByCategoryUseCase
import com.artemklymenko.presentation.MainDispatcherRule
import com.artemklymenko.presentation.common.fakeNews
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class CategoriesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getNewsByCategoryUseCase: GetNewsByCategoryUseCase = mock()
    private lateinit var viewModel: CategoriesViewModel

    companion object {
        private const val QUERY_STRING = "sport"
        private const val CATEGORY_STRING = "business"
    }

    @Before
    fun setup() {
        viewModel = CategoriesViewModel(getNewsByCategoryUseCase)
    }

    @Test
    fun `when category selected, uiState updates with loading then success`() = runTest(mainDispatcherRule.getTestDispatcher()) {

        `when`(getNewsByCategoryUseCase.invoke(QUERY_STRING))
            .thenReturn(NewsResult.Success(fakeNews()))

        viewModel.onEvent(CategoriesUiEvent.OnCategorySelected(QUERY_STRING))

        advanceUntilIdle()

        assertEquals(false, viewModel.uiState.value.isLoading)
        assertEquals(QUERY_STRING, viewModel.uiState.value.selectedCategory)
        assertEquals(fakeNews(), viewModel.uiState.value.news)
        assertEquals(null, viewModel.uiState.value.error)
    }

    @Test
    fun `when use case returns error, uiState updates with error`() = runTest(mainDispatcherRule.getTestDispatcher()) {
        `when`(getNewsByCategoryUseCase.invoke(CATEGORY_STRING))
            .thenReturn(NewsResult.Error("network error"))

        viewModel.onEvent(CategoriesUiEvent.OnCategorySelected(CATEGORY_STRING))

        advanceUntilIdle()

        assertEquals(false, viewModel.uiState.value.isLoading)
        assertEquals(CATEGORY_STRING, viewModel.uiState.value.selectedCategory)
        assertEquals("network error", viewModel.uiState.value.error)
        assertEquals(null, viewModel.uiState.value.news)
    }
}

