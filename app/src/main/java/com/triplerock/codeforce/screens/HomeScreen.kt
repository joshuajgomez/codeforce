package com.triplerock.codeforce.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triplerock.codeforce.R
import com.triplerock.codeforce.data.RoomType
import com.triplerock.codeforce.data.sampleProjectNames
import com.triplerock.codeforce.screens.ui.theme.Green50
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
        topBar = { HomeTitle() },
        bottomBar = { EventsViewShort() }) {
        HomeBody(Modifier.padding(it))
    }
}

@Composable
fun HomeBody(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .fillMaxSize(),
    ) {
        Text(text = "Idle engineers", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Stats()
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Ongoing projects", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        OngoingProjects()
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Floor view", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        FloorView()
    }
}

@Composable
fun OngoingProjects() {
    CfCard {
        LazyColumn {
            items(2) {
                OngoingProjectCard()
            }
        }
    }
}

@Composable
fun OngoingProjectCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        StatIcon()
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = sampleProjectNames.random())
    }
}

@Composable
fun StatIcon() {
    Text(
        text = "56/121",
        Modifier
            .background(Green50, RoundedCornerShape(10.dp))
            .padding(10.dp)
    )
}

@Composable
fun Stats() {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        InfoCard("Developers", "5/34")
        InfoCard("Managers", "2/13")
    }
}

@Composable
fun InfoCard(label: String, value: String) {
    CfCard(Modifier.width(182.dp)) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            RoomIcon(
                modifier = Modifier.size(30.dp),
                roomType = RoomType.Developer
            )
            Text(
                text = label,
            )
            Text(
                text = value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun HomeTitle() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_full),
            contentDescription = null,
            modifier = Modifier.height(45.dp)
        )
    }
}
