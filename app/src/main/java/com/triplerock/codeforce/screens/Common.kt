package com.triplerock.codeforce.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.BlurCircular
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.EmojiFoodBeverage
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.triplerock.codeforce.data.Room
import com.triplerock.codeforce.data.RoomType
import com.triplerock.codeforce.ui.theme.CodeForceTheme

@Preview
@Composable
fun PreviewRoomIcon() {
    CodeForceTheme {
        RoomIcon()
    }
}

@Composable
fun RoomIcon(modifier: Modifier = Modifier, room: Room = Room()) {
    val icon = when (room.type) {
        RoomType.Developer -> Icons.Default.Code
        RoomType.Manager -> Icons.Default.SupervisedUserCircle
        RoomType.HR -> Icons.Default.HealthAndSafety
        RoomType.Cafeteria -> Icons.Default.EmojiFoodBeverage
        RoomType.Sales -> Icons.Default.AttachMoney
        else -> Icons.Default.Add
    }
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onPrimary,
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.primary,
                CircleShape
            )
            .size(40.dp)
            .padding(5.dp)
    )
}