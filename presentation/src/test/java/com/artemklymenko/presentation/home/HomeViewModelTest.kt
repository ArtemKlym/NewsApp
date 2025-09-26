package com.artemklymenko.presentation.home

import com.artemklymenko.domain.repository.NewsResult
import com.artemklymenko.domain.usecases.GetNewsUseCase
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
class HomeViewModelTest {

    @get: Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getNewsUseCase: GetNewsUseCase = mock()
    private lateinit var viewModel: HomeViewModel

    companion object {
        private const val QUERY_STRING = "sport"
    }

    @Before
    fun setup() {
        viewModel = HomeViewModel(getNewsUseCase)
    }

    @Test
    fun `when search field isn't empty, uiState updates with loading then success`() = runTest(mainDispatcherRule.getTestDispatcher()) {

        `when`(getNewsUseCase.invoke(QUERY_STRING))
            .thenReturn(NewsResult.Success(fakeNews()))

        viewModel.onEvent(HomeUiEvent.SearchNews(QUERY_STRING))

        advanceUntilIdle()

        assertEquals(false, viewModel.uiState.value.isLoading)
        assertEquals(QUERY_STRING, viewModel.uiState.value.searchQuery)
        assertEquals(fakeNews(), viewModel.uiState.value.news)
        assertEquals(null, viewModel.uiState.value.error)
    }

    @Test
    fun `when use case returns error, uiState updates with error`() = runTest(mainDispatcherRule.getTestDispatcher()) {
        `when`(getNewsUseCase.invoke(QUERY_STRING))
            .thenReturn(NewsResult.Error("network error"))

        viewModel.onEvent(HomeUiEvent.SearchNews(QUERY_STRING))

        advanceUntilIdle()

        assertEquals(false, viewModel.uiState.value.isLoading)
        assertEquals(QUERY_STRING, viewModel.uiState.value.searchQuery)
        assertEquals("network error", viewModel.uiState.value.error)
        assertEquals(null, viewModel.uiState.value.news)
    }

}