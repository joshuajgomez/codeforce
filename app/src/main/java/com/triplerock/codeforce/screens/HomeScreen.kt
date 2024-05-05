package com.triplerock.codeforce.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.triplerock.codeforce.ui.theme.CodeForceTheme

@DarkPreview
@Composable
fun PreviewHomeScreen() {
    CodeForceTheme {
        HomeScreen()
    }
}

@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { HomeTitle() }) {
        HomeBody(Modifier.padding(it))
    }
}

@Composable
fun HomeBody(modifier: Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FloorView()
        Spacer(modifier = Modifier.height(30.dp))
        EventsViewShort()
    }
}

@Composable
fun HomeTitle() {
    Text(text = "home title")
}
