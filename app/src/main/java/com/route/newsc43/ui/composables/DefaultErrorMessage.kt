package com.route.newsc43.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.route.newsc43.ui.theme.White

@Composable
fun DefaultErrorMessage(message: String, onRetry: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(message, style = TextStyle(color = White, fontSize = 18.sp))
        ElevatedButton(onClick = {
            onRetry()
        }) {
            Text("Retry")
        }
    }
}