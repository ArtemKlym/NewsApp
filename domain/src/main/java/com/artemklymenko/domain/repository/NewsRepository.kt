package com.artemklymenko.domain.repository

interface NewsRepository {

    suspend fun getNewsByQuery(searchQuery: String): NewsResult

    suspend fun getNewsByCategory(category: String): NewsResult
}