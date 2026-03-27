package com.route.newsc43.ui.screens.home.composables.news

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.route.newsc43.R
import com.route.newsc43.api.ApiManager
import com.route.newsc43.api.model.ArticleDM
import com.route.newsc43.api.model.ArticlesResponse
import com.route.newsc43.ui.composables.DefaultErrorMessage
import com.route.newsc43.ui.composables.DefaultLoadingView
import com.route.newsc43.ui.theme.Black
import com.route.newsc43.ui.theme.NewsDarkTypography
import com.route.newsc43.ui.theme.White
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun ArticlesList(source: String) {

    var isLoading by remember { mutableStateOf(false) }

    var errorMessage by remember { mutableStateOf<String?>(null) }
    var articles by remember { mutableStateOf<List<ArticleDM>?>(null) }

    DisposableEffect(source) {
        isLoading = true
        ApiManager.getWebServices().getArticles(source = source)
            .enqueue(object : Callback<ArticlesResponse> {
                override fun onResponse(
                    call: Call<ArticlesResponse?>,
                    response: Response<ArticlesResponse?>
                ) {
                    isLoading = false
                    Log.e("getArticles - onResponse", "code = ${response.code()}")
                    Log.e("getArticles - onResponse", "body = ${response.body()}")
                    if (response.isSuccessful) {
                        articles = response.body()!!.articles
                    } else {
                        errorMessage = response.message()
                    }
                }

                override fun onFailure(
                    call: Call<ArticlesResponse?>,
                    t: Throwable
                ) {
                    Log.e("getArticles - onFailure", "body = ${t}")
                    isLoading = false
                    errorMessage = t.message ?: "Something went wrong please try again later"
                }

            })
        onDispose { }
    }

    LazyColumn {
        if (isLoading) {
            item {
                DefaultLoadingView()
            }

        }
        if (errorMessage?.isNotEmpty() == true) {
            item {
                DefaultErrorMessage(errorMessage!!) {

                }
            }

        }
        if (!articles.isNullOrEmpty()) {
            items(articles!!) { article ->
                ArticleItem(article)
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
