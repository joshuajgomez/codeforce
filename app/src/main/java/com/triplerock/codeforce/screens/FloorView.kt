package com.triplerock.codeforce.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.triplerock.codeforce.data.Room
import com.triplerock.codeforce.data.RoomType
import com.triplerock.codeforce.data.sampleRooms
import com.triplerock.codeforce.ui.theme.CodeForceTheme

@DarkPreview
@Composable
private fun PreviewFloorView() {
    CodeForceTheme {
        FloorView()
    }
}

@Composable
fun FloorView(rooms: List<Room> = sampleRooms) {
    CfCard(
        modifier = Modifier,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(350.dp)
        ) {
            Column(
                Modifier
                    .padding(10.dp)
            ) {
                repeat(5) { x ->
                    Row {
                        repeat(5) { y ->
                            var room = rooms.find {
                                it.x == x && it.y == y
                            }
                            room = room ?: Room(x = x, y = y)
                            RoomBox(room)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RoomBox(
    room: Room = Room(RoomType.Developer),
    onRoomClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .size(60.dp)
            .border(1.dp, colorScheme.outline.copy(alpha = 0.1f))
            .clickable { onRoomClick() }
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (room.type != RoomType.Empty) {
            RoomIcon(roomType = room.type)
        } else {
            Text(
                text = "${room.x}${room.y}",
                color = colorScheme.outlineVariant
            )
        }
    }
}
