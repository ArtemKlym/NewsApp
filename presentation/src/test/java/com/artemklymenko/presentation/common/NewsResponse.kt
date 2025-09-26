package com.artemklymenko.presentation.common

import com.artemklymenko.domain.models.Article
import com.artemklymenko.domain.models.News
import com.artemklymenko.domain.models.Source

fun fakeNews(): News {
    return News(
        status = "success",
        totalResults = 1,
        articles = listOf(
            Article(
                source = Source(name = "source", id = ""),
                title = "Title",
                description = "description",
                url = "",
                publishedAt = "publishedAt",
                author = "author",
                urlToImage = null,
                content = ""
            )
        )
    )
}