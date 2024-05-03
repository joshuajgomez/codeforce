package com.triplerock.codeforce.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.triplerock.codeforce.data.Room
import com.triplerock.codeforce.data.RoomType
import com.triplerock.codeforce.data.roomDescription
import com.triplerock.codeforce.ui.theme.CodeForceTheme

@Preview(showBackground = true)
@Composable
fun PreviewRoomView() {
    CodeForceTheme {
        RoomView()
    }
}

@Composable
fun RoomView(
    room: Room = Room(RoomType.Developer),
    onEvictButtonClick: () -> Unit = {}
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        val (
            closeIconRef,
            iconRef,
            nameRef,
            capacityRef,
            capacityLabelRef,
            descriptionRef,
            evictBtnRef
        ) = createRefs()
        CloseIcon(modifier = Modifier.constrainAs(closeIconRef) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
        }) {}
        RoomIcon(modifier = Modifier
            .constrainAs(iconRef) {
                start.linkTo(parent.start)
                top.linkTo(closeIconRef.bottom, 20.dp)
            }
            .size(100.dp),
            room = room)
        Text(text = "${room.type}",
            fontSize = 30.sp,
            modifier = Modifier.constrainAs(nameRef) {
                end.linkTo(parent.end)
                top.linkTo(iconRef.top)
            })
        Text(text = "capacity",
            fontSize = 20.sp,
            color = colorScheme.onBackground.copy(alpha = 0.6f),
            modifier = Modifier.constrainAs(capacityLabelRef) {
                end.linkTo(parent.end)
                top.linkTo(nameRef.bottom, 10.dp)
            })
        Text(text = "${room.count}/${room.capacity}",
            fontSize = 25.sp,
            modifier = Modifier.constrainAs(capacityRef) {
                end.linkTo(parent.end)
                top.linkTo(capacityLabelRef.bottom)
            })
        Text(text = roomDescription(room.type),
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(descriptionRef) {
                start.linkTo(parent.start)
                top.linkTo(iconRef.bottom, 30.dp)
            })
        Button(
            onClick = { onEvictButtonClick() },
            modifier = Modifier.constrainAs(evictBtnRef) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom, 30.dp)
            }) {
            Text(text = "EVICT ROOM", fontSize = 20.sp)
        }
    }
}

@Composable
fun CloseIcon(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(onClick = { onClick() }) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            tint = colorScheme.onPrimary,
            modifier = modifier
                .size(50.dp)
                .padding(5.dp)
        )
    }
}
