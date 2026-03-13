package com.route.newsc43.ui.screens.home.composables.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.route.newsc43.R
import com.route.newsc43.ui.theme.Black
import com.route.newsc43.ui.theme.NewsDarkTypography
import com.route.newsc43.ui.theme.White

data class Article(val image: Int, val title: String, val author: String, val date: String)

val articles = listOf(
    Article(
        image = R.drawable.ic_person,
        title = "Why are football fans in many countries can't sleep at night?",
        author = "ABC News",
        date = "3 hours ago"
    ),
    Article(
        image = R.drawable.ic_person,
        title = "The future of AI in Android development: What to expect in 2025",
        author = "TechCrunch",
        date = "5 hours ago"
    ),
    Article(
        image = R.drawable.ic_person,
        title = "New features coming to Jetpack Compose in the next release",
        author = "Android Developers",
        date = "1 day ago"
    ),
    Article(
        image = R.drawable.ic_person,
        title = "Global economy trends: Experts weigh in on the next quarter",
        author = "Reuters",
        date = "2 days ago"
    ),
    Article(
        image = R.drawable.ic_person,
        title = "Top 10 travel destinations for the upcoming summer season",
        author = "National Geographic",
        date = "3 days ago"
    )
)

@Composable
fun ArticlesList() {
    LazyColumn {
        items(articles) { article ->
            ArticleItem(article)
        }
    }
}

@Composable
fun ArticleItem(article: Article) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(1.dp, White, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Black)
                .padding(2.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_person),
                modifier = Modifier
                    .fillMaxHeight(.15f)
                    .clip(RoundedCornerShape(10.dp)),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(article.title, style = NewsDarkTypography.bodyMedium)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(article.author, style = NewsDarkTypography.labelLarge)
                Text(article.date, style = NewsDarkTypography.labelLarge)
            }
        }
    }
}
