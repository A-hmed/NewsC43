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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.route.newsc43.ui.model.Category
import com.route.newsc43.ui.theme.Black
import com.route.newsc43.ui.theme.NewsDarkTypography

@Composable
fun NewsTab(category: Category) {
    val tabs = listOf<String>("Tab1 ", "Tab2", "Tab3", "Tab4")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
            for (i in 0 until tabs.size) {
                var isSelected = selectedTabIndex == i
                Tab(
                    selected = selectedTabIndex == i,
                    onClick = {
                        selectedTabIndex = i
                    }) {
                    Text(
                        tabs[i],
                        style = if (isSelected) NewsDarkTypography.bodyMedium else NewsDarkTypography.bodySmall
                    )
                }
            }
        }
        ArticlesList()
    }

}