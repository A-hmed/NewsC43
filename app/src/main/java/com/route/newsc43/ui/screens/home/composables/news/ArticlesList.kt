package com.route.newsc43.ui.screens.home.composables.news

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.route.newsc43.R
import com.route.newsc43.data.api.model.ArticleDM
import com.route.newsc43.ui.composables.DefaultErrorMessage
import com.route.newsc43.ui.composables.DefaultLoadingView
import com.route.newsc43.ui.screens.home.NewsViewModel
import com.route.newsc43.ui.theme.Black
import com.route.newsc43.ui.theme.NewsDarkTypography
import com.route.newsc43.ui.theme.White


@Composable
fun ArticlesList(source: String) {
    val viewModel = viewModel<NewsViewModel>()
    val isLoading = viewModel.isLoadingArticles.observeAsState()
    val errorMessage = viewModel.articlesErrorMessage.observeAsState()
    val articles = viewModel.articles.observeAsState()

    DisposableEffect(source) {
        viewModel.getArticles(source)
        onDispose { }
    }

    LazyColumn {
        if (isLoading.value!!) {
            item {
                DefaultLoadingView()
            }

        }
        if (errorMessage.value?.isNotEmpty() == true) {
            item {
                DefaultErrorMessage(errorMessage.value!!) {

                }
            }

        }
        if (articles.value != null) {
            if (articles.value!!.isNotEmpty()) {
                items(articles.value!!) { article ->
                    ArticleItem(article)
                }
            } else {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Text(
                            text = "No articles in this source",
                            style = TextStyle(color = White, fontSize = 18.sp)
                        )
                    }

                }
            }
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArticleItem(article: ArticleDM) {
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
            println("ahmed: ${article.urlToImage}")
            GlideImage(
                model = article.urlToImage,
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)),
                // Optional: Add placeholders
                loading = placeholder(R.drawable.ic_launcher_background),
                failure = placeholder(R.drawable.ic_launcher_background)
            )
//            AsyncImage(
//                model = article.urlToImage,
//                modifier = Modifier
//                    .height(200.dp)
//                    .fillMaxHeight(.15f)
//                    .clip(RoundedCornerShape(10.dp)),
//                contentDescription = ""
//            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(article.title ?: "", style = NewsDarkTypography.bodyMedium)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(article.author ?: "", style = NewsDarkTypography.labelLarge)
                Text(article.publishedAt ?: "", style = NewsDarkTypography.labelLarge)
            }
        }
    }
}
