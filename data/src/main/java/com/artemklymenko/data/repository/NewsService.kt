package com.artemklymenko.data.repository

import com.artemklymenko.domain.models.News
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/everything")
    suspend fun getEverything(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): News

    @GET("v2/top-headlines")
    suspend fun getTopHeadlinesByCategory(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): News
}