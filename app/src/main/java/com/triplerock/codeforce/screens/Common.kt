package com.triplerock.codeforce.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.EmojiFoodBeverage
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triplerock.codeforce.data.Room
import com.triplerock.codeforce.data.RoomType
import com.triplerock.codeforce.ui.theme.CodeForceTheme

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class DarkPreview

@Preview
@Composable
fun PreviewRoomIcon() {
    CodeForceTheme {
        RoomIcon()
    }
}

@Composable
fun RoomIcon(modifier: Modifier = Modifier, roomType: RoomType = RoomType.Empty) {
    val icon = when (roomType) {
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

@Composable
fun LabelValue(
    modifier: Modifier = Modifier,
    label: String = "label",
    value: String = "value"
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.End) {
        Text(
            text = label,
            color = colorScheme.onBackground.copy(alpha = 0.5f),
            fontSize = 15.sp,
        )
        Text(
            text = value,
            fontSize = 18.sp,
            color = colorScheme.onBackground
        )
    }
}

@DarkPreview
@Composable
fun TitleBar(
    title: String = "CodeForce app",
    onCloseClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorScheme.background)
            .padding(horizontal = 10.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CloseIcon {
            onCloseClick()
        }
        Text(
            text = title, fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = colorScheme.onBackground,
            textAlign = TextAlign.End
        )
    }
}