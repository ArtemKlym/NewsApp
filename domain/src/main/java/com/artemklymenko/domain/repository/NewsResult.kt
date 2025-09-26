package com.artemklymenko.domain.repository

import com.artemklymenko.domain.models.News

interface NewsResult {

    fun <T: Any> map(mapper: Mapper<T>): T

    interface  Mapper<T: Any> {

        fun mapSuccess(news: News): T

        fun mapError(message: String): T
    }

    data class Success(private val news: News): NewsResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapSuccess(news)
        }
    }

    data class Error(private val message: String): NewsResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapError(message)
        }
    }
}