package com.route.newsc43.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.route.newsc43.ui.model.Category
import com.route.newsc43.ui.screens.home.composables.DrawerContent
import com.route.newsc43.ui.screens.home.composables.categories.CategoriesTab
import com.route.newsc43.ui.screens.home.composables.news.NewsTab
import com.route.newsc43.ui.theme.Black
import com.route.newsc43.ui.theme.NewsDarkTypography
import com.route.newsc43.ui.theme.White
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedCateogry by remember { mutableStateOf<Category?>(null) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(onGoToHomeClick = {
                selectedCateogry = null
                scope.launch {
                    drawerState.close()
                }
            })
        }
    ) {
        Scaffold(
            containerColor = Black,
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                        }

                    },
                    title = {
                        Text(
                            "Home",
                            style = NewsDarkTypography.bodyLarge.copy(color = White),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    colors = TopAppBarColors(
                        containerColor = Black, titleContentColor = White,
                        scrolledContainerColor = White,
                        navigationIconContentColor = White,
                        actionIconContentColor = White
                    )
                )
            }
        ) { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                if (selectedCateogry != null) {
                    NewsTab(selectedCateogry!!)
                } else {
                    CategoriesTab { category ->
                        selectedCateogry = category
                    }
                }
            }
        }
    }
}