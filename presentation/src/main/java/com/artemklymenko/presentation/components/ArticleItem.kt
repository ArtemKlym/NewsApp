package com.artemklymenko.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.artemklymenko.domain.models.Article
import com.artemklymenko.domain.models.Source

@Composable
fun ArticleItem(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        article.urlToImage?.let { imageUrl ->
            AsyncImage(
                model = imageUrl,
                contentDescription = article.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        Text(
            text = article.title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = article.description,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )

        HorizontalDivider(modifier = Modifier.padding(top = 12.dp))
    }
}




@Preview(showBackground = true)
@Composable
private fun ArticleItemPreview() {
    MaterialTheme {
        Column {
            ArticleItem(
                article = Article(
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
            ArticleItem(
                article = Article(
                    source = Source(name = "source", id = ""),
                    title = "Title",
                    description = "description",
                    url = "",
                    publishedAt = "publishedAt",
                    author = "author",
                    urlToImage = "",
                    content = ""
                )
            )
            ArticleItem(
                article = Article(
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
        }
    }
}