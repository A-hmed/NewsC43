package com.route.newsc43.ui.screens.home.composables.news

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.route.newsc43.api.ApiManager
import com.route.newsc43.api.model.SourceDM
import com.route.newsc43.api.model.SourcesResponse
import com.route.newsc43.ui.composables.DefaultErrorMessage
import com.route.newsc43.ui.composables.DefaultLoadingView
import com.route.newsc43.ui.model.Category
import com.route.newsc43.ui.theme.Black
import com.route.newsc43.ui.theme.NewsDarkTypography
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun NewsTab(category: Category) {
    var tabs by remember { mutableStateOf<List<SourceDM>?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    DisposableEffect(Unit) {
        isLoading = true
        ApiManager.getWebServices().getSources(category = category.title)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse?>,
                    response: Response<SourcesResponse?>
                ) {
                    isLoading = false
                    Log.e("getSources - onResponse", "code = ${response.code()}")
                    if (response.isSuccessful) {
                        tabs = response.body()!!.sources
                    } else {
                        errorMessage = response.message()
                    }
                }

                override fun onFailure(
                    call: Call<SourcesResponse?>,
                    t: Throwable
                ) {
                    isLoading = false
                    Log.e("getSources - onFailure", "code = ${t.message}")
                    errorMessage = t.message ?: "Something went please try again later"
                }

            })

        onDispose {}
    }



    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            DefaultLoadingView()
        }
        if (!tabs.isNullOrEmpty()) {
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Black,
                indicator = { tabsPositions ->
                    Box(
                        Modifier
                            .tabIndicatorOffset(tabsPositions[selectedTabIndex])
                            .padding(top = 10.dp)
                            .height(1.dp)
                            .background(color = Color.White)
                    )
                },
                divider = {}
            ) {
                for (i in 0 until (tabs?.size ?: -1)) {
                    var isSelected = selectedTabIndex == i
                    Tab(
                        selected = selectedTabIndex == i,
                        onClick = {
                            selectedTabIndex = i
                        }, modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            tabs!![i].name ?: "",
                            style = if (isSelected) NewsDarkTypography.bodyMedium else NewsDarkTypography.bodySmall
                        )
                    }
                }
            }
            Log.e("ArticlesList", "selectedTabIndex = ${selectedTabIndex}")
            ArticlesList(source = tabs!![selectedTabIndex].id ?: "")
        }

        if (errorMessage?.isNotEmpty() == true) {
            DefaultErrorMessage(errorMessage!!) {

            }
        }

    }

}