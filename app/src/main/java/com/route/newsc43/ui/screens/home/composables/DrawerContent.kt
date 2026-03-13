package com.route.newsc43.ui.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.route.newsc43.ui.theme.Black
import com.route.newsc43.ui.theme.NewsDarkTypography
import com.route.newsc43.ui.theme.White

@Composable
fun DrawerContent(onGoToHomeClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth(.7f)
            .fillMaxHeight()
            .background(Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.2f)
                .background(White),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                "News App",
                textAlign = TextAlign.Center,
                style = NewsDarkTypography.titleSmall.copy(
                    color = Black,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        DrawerRow(Icons.Default.Menu, "Go to home") {
            onGoToHomeClick()
        }
        HorizontalDivider(modifier = Modifier.padding(horizontal = 12.dp))

        DrawerRow(Icons.Default.Build, "Theme") {

        }
        HorizontalDivider(modifier = Modifier.padding(horizontal = 12.dp))
        DrawerRow(Icons.Default.Info, "Language") {

        }


    }
}

@Composable
fun DrawerRow(icon: ImageVector, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                onClick()
            }
    ) {
        Icon(imageVector = icon, contentDescription = "", tint = White)
        Spacer(modifier = Modifier.width(8.dp))
        Text(title, style = NewsDarkTypography.bodyLarge)
    }
}