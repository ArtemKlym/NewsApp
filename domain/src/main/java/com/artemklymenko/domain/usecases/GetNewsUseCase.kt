package com.artemklymenko.domain.usecases

import com.artemklymenko.domain.repository.NewsRepository
import com.artemklymenko.domain.repository.NewsResult
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(searchQuery: String): NewsResult {
        return newsRepository.getNewsByQuery(searchQuery)
    }
}