package com.artemklymenko.data.repository


import com.artemklymenko.data.Constants
import com.artemklymenko.domain.repository.NewsRepository
import com.artemklymenko.domain.repository.NewsResult
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val service: NewsService
): NewsRepository {
    override suspend fun getNewsByQuery(searchQuery: String): NewsResult {
        return try {
            val result = service.getEverything(query = searchQuery, apiKey = Constants.API_KEY)
            NewsResult.Success(result)
        } catch (e: Exception){
            NewsResult.Error(e.message.toString())
        }
    }

    override suspend fun getNewsByCategory(category: String): NewsResult {
        return try {
            val result = service.getTopHeadlinesByCategory(category = category, apiKey = Constants.API_KEY)
            NewsResult.Success(result)
        } catch (e: Exception) {
            NewsResult.Error(e.message.toString())
        }
    }
}