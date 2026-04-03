package com.route.newsc43.ui.screens.home.composables.news

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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.newsc43.ui.composables.DefaultErrorMessage
import com.route.newsc43.ui.composables.DefaultLoadingView
import com.route.newsc43.ui.model.Category
import com.route.newsc43.ui.screens.home.NewsViewModel
import com.route.newsc43.ui.theme.Black
import com.route.newsc43.ui.theme.NewsDarkTypography

@Composable
fun NewsTab(category: Category) {

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val viewModel = viewModel<NewsViewModel>()
    val isLoading = viewModel.isLoading.observeAsState()
    val errorMessage = viewModel.errorMessage.observeAsState()
    val tabs = viewModel.tabs.observeAsState()


    DisposableEffect(Unit) {
        viewModel.getSources(category.title)
        onDispose {}
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading.value == true) {
            DefaultLoadingView()
        }
        if (!tabs.value.isNullOrEmpty()) {
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
                for (i in 0 until (tabs.value?.size ?: -1)) {
                    var isSelected = selectedTabIndex == i
                    Tab(
                        selected = selectedTabIndex == i,
                        onClick = {
                            selectedTabIndex = i
                        }, modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            tabs.value!![i].name ?: "",
                            style = if (isSelected) NewsDarkTypography.bodyMedium else NewsDarkTypography.bodySmall
                        )
                    }
                }
            }
            ArticlesList(source = tabs.value!![selectedTabIndex].id ?: "")
        }

        if (errorMessage.value?.isNotEmpty() == true) {
            DefaultErrorMessage(errorMessage.value!!) {

            }
        }
    }

}