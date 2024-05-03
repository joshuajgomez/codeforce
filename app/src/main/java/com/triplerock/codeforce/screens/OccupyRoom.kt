package com.triplerock.codeforce.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.triplerock.codeforce.data.RoomType
import com.triplerock.codeforce.data.costOf
import com.triplerock.codeforce.data.displayCost
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

val availableRoomTypes = RoomType.entries.filter {
    it != RoomType.Empty
}

@Composable
fun OccupyRoom(
    roomTypes: List<RoomType> = availableRoomTypes
) {
    Column(
        modifier = Modifier.padding(
            horizontal = 10.dp,
            vertical = 20.dp
        )
    ) {
        Scaffold(topBar = { TitleBar("Select type of room you want to add here") })
        {
            RoomTypeList(Modifier.padding(it), roomTypes)
        }
    }
}

@Composable
fun RoomTypeList(
    modifier: Modifier,
    roomTypes: List<RoomType>
) {
    LazyColumn(
        modifier = modifier
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
            val (iconRef, typeRef, descRef, btnRef, capacityRef, costRef) = createRefs()
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

            PriceTag(
                modifier = Modifier.constrainAs(costRef) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
                text = costOf(roomType).displayCost()
            )

            LabelValue(modifier = Modifier.constrainAs(capacityRef) {
                end.linkTo(parent.end, 10.dp)
                top.linkTo(costRef.bottom, 10.dp)
            }, "capacity", maxCapacityOf(roomType).toString())

        }
    }
}

@Composable
fun PriceTag(modifier: Modifier, text: String) {
    Text(
        text = text,
        fontSize = 25.sp,
        modifier = modifier
            .background(
                colorScheme.primaryContainer,
                RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 15.dp, vertical = 5.dp)
    )
}


