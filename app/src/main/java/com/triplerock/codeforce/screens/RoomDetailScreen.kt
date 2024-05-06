package com.triplerock.codeforce.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.triplerock.codeforce.data.Room
import com.triplerock.codeforce.data.RoomType
import com.triplerock.codeforce.data.roomDescription
import com.triplerock.codeforce.ui.theme.CodeForceTheme

@DarkPreview
@Composable
fun PreviewRoomsScreen() {
    CodeForceTheme {
        RoomsScreen()
    }
}

@Composable
fun RoomsScreen(
    room: Room = Room(RoomType.Developer),
    onEvictButtonClick: () -> Unit = {},
) {
    Scaffold(
        topBar = { TitleBar("${room.type}'s room") },
    ) {
        CommonBody(modifier = Modifier.padding(it)) {
            RoomView(
                room = room,
                onEvictButtonClick = onEvictButtonClick
            )
        }
    }
}

@Composable
private fun RoomView(
    modifier: Modifier = Modifier,
    room: Room,
    onEvictButtonClick: () -> Unit,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        val (
            iconRef,
            capacityRef,
            descriptionRef,
            addEmployeeRef,
            evictBtnRef,
        ) = createRefs()
        RoomIcon(modifier = Modifier
            .constrainAs(iconRef) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
            .size(100.dp),
            roomType = room.type)
        LabelValue(Modifier.constrainAs(capacityRef) {
            end.linkTo(parent.end, 10.dp)
            top.linkTo(iconRef.top)
        }, "capacity", "${room.count}/${room.capacity}")
        Text(text = roomDescription(room.type),
            fontSize = 20.sp,
            color = colorScheme.onBackground,
            modifier = Modifier.constrainAs(descriptionRef) {
                start.linkTo(parent.start)
                top.linkTo(iconRef.bottom, 30.dp)
            })
        AddEmployeeCard(modifier = Modifier.constrainAs(addEmployeeRef) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(descriptionRef.bottom, 60.dp)
        })
        ElevatedButton(
            onClick = { onEvictButtonClick() },
            modifier = Modifier.constrainAs(evictBtnRef) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom, 30.dp)
            }) {
            Text(
                text = "Evict Room",
                fontSize = 20.sp,
                color = colorScheme.errorContainer
            )
        }
    }
}

@DarkPreview
@Composable
fun PreviewAddEmployeeView(modifier: Modifier = Modifier) {
    CodeForceTheme {
        AddEmployeeCard()
    }
}

@Composable
fun AddEmployeeCard(modifier: Modifier = Modifier) {
    CfCard(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "add developers",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )
            InfoBox(
                keyValue = hashMapOf(
                    "capacity" to "3/4",
                    "available" to "6/36",
                )
            )
            NumberSelector()
        }
    }
}

@Composable
fun CloseIcon(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(onClick = { onClick() }, modifier = modifier) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .padding(5.dp),
            tint = colorScheme.onBackground
        )
    }
}
