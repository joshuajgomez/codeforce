package com.triplerock.codeforce.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.triplerock.codeforce.data.Room
import com.triplerock.codeforce.data.RoomType
import com.triplerock.codeforce.data.roomDescription
import com.triplerock.codeforce.ui.theme.CodeForceTheme

@DarkPreview
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
            addEmployeeRef,
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
        Text(text = "${room.type}'s room",
            fontSize = 25.sp,
            color = colorScheme.primary,
            modifier = Modifier.constrainAs(nameRef) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        Text(text = "capacity",
            fontSize = 20.sp,
            color = colorScheme.onBackground.copy(alpha = 0.6f),
            modifier = Modifier.constrainAs(capacityLabelRef) {
                end.linkTo(parent.end)
                top.linkTo(iconRef.top, 10.dp)
            })
        Text(text = "${room.count}/${room.capacity}",
            fontSize = 30.sp,
            color = colorScheme.onBackground,
            modifier = Modifier.constrainAs(capacityRef) {
                end.linkTo(parent.end)
                top.linkTo(capacityLabelRef.bottom)
            })
        Text(text = roomDescription(room.type),
            fontSize = 20.sp,
            color = colorScheme.onBackground,
            modifier = Modifier.constrainAs(descriptionRef) {
                start.linkTo(parent.start)
                top.linkTo(iconRef.bottom, 30.dp)
            })
        AddEmployeeView(modifier = Modifier.constrainAs(addEmployeeRef) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(descriptionRef.bottom, 30.dp)
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

@DarkPreview
@Composable
fun PreviewAddEmployeeView(modifier: Modifier = Modifier) {
    CodeForceTheme {
        AddEmployeeView()
    }
}

@Composable
fun AddEmployeeView(modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            val (textRef, btnRef, availableLabelRef, availableRef) = createRefs()
            Text(text = "Add developers",
                fontSize = 22.sp,
                modifier = Modifier.constrainAs(textRef) {
                    start.linkTo(parent.start)
                })
            NumberSelector(modifier = Modifier.constrainAs(btnRef) {
                start.linkTo(parent.start)
                top.linkTo(textRef.bottom, 20.dp)
            })
            Text(text = "Available",
                fontSize = 18.sp,
                color = colorScheme.onBackground.copy(alpha = 0.7f),
                modifier = Modifier.constrainAs(availableLabelRef) {
                    end.linkTo(parent.end)
                    top.linkTo(btnRef.top)
                })
            Text(text = "6/36",
                fontSize = 25.sp,
                modifier = Modifier.constrainAs(availableRef) {
                    end.linkTo(parent.end)
                    top.linkTo(availableLabelRef.bottom)
                })
        }
    }
}

@DarkPreview
@Composable
fun NumberSelector(
    modifier: Modifier = Modifier,
    onAddPress: () -> Unit = {},
    onMinusPress: () -> Unit = {},
    iconSize: Dp = 50.dp
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Remove,
            contentDescription = null,
            tint = colorScheme.onPrimary,
            modifier = Modifier
                .size(iconSize)
                .background(colorScheme.primary, CircleShape)
                .padding(5.dp)
        )
        Text(text = "3", fontSize = (iconSize.value-10).sp)
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            tint = colorScheme.onPrimary,
            modifier = Modifier
                .size(iconSize)
                .background(colorScheme.primary, CircleShape)
                .padding(5.dp)
        )
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
