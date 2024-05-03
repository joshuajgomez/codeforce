package com.triplerock.codeforce.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.triplerock.codeforce.data.RoomType
import com.triplerock.codeforce.data.maxCapacityOf
import com.triplerock.codeforce.data.roomDescription
import com.triplerock.codeforce.ui.theme.CodeForceTheme

@DarkPreview
@Composable
fun PreviewOccupyRoom() {
    CodeForceTheme {
        OccupyRoom()
    }
}

@Composable
fun OccupyRoom() {
    Column(
        modifier = Modifier.padding(
            horizontal = 10.dp,
            vertical = 20.dp
        )
    ) {
        CloseIcon {

        }
        RoomTypeList()
    }
}

@Composable
fun RoomTypeList(
    roomTypes: List<RoomType> = RoomType.entries.filter {
        it != RoomType.Empty
    }
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        items(roomTypes) {
            RoomCard(it)
        }
    }
}

@Composable
fun RoomCard(roomType: RoomType, onClick: () -> Unit = {}) {
    Card(modifier = Modifier.padding(5.dp)) {
        ConstraintLayout(
            modifier = Modifier
                .padding(20.dp)
        ) {
            val (iconRef, typeRef, descRef, btnRef, capacityLabelRef, capacityRef) = createRefs()
            RoomIcon(
                modifier = Modifier
                    .size(70.dp)
                    .constrainAs(iconRef) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    },
                roomType = roomType
            )
            Text(
                text = "$roomType",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(typeRef) {
                    start.linkTo(parent.start)
                    top.linkTo(iconRef.bottom, 20.dp)
                }
            )
            Text(
                text = roomDescription(roomType),
                color = colorScheme.onBackground.copy(alpha = 0.5f),
                fontSize = 18.sp,
                modifier = Modifier.constrainAs(descRef) {
                    start.linkTo(parent.start)
                    top.linkTo(typeRef.bottom, 10.dp)
                }
            )
            Button(
                onClick = { onClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(btnRef) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(descRef.bottom, 20.dp)
                    }
            ) {
                Text(text = "select", fontSize = 20.sp)
            }
            Text(text = "capacity",
                color = colorScheme.onBackground.copy(alpha = 0.5f),
                modifier = Modifier.constrainAs(capacityLabelRef) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, 10.dp)
                })
            Text(text = maxCapacityOf(roomType).toString(),
                fontSize = 25.sp,
                modifier = Modifier.constrainAs(capacityRef) {
                    end.linkTo(parent.end)
                    top.linkTo(capacityLabelRef.bottom)
                })
        }
    }
}
