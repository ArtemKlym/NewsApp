package com.artemklymenko.domain.usecases

import com.artemklymenko.domain.repository.NewsRepository
import com.artemklymenko.domain.repository.NewsResult
import javax.inject.Inject

class GetNewsByCategoryUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(category: String): NewsResult {
        return newsRepository.getNewsByCategory(category)
    }
}