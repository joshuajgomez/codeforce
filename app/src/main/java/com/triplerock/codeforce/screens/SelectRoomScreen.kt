package com.triplerock.codeforce.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
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
    Scaffold(topBar = { TitleBar("Select room type") })
    {
        RoomTypeList(Modifier.padding(it), roomTypes)
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

private fun roomCardConstraintSet(verticalPadding: Dp = 20.dp) = ConstraintSet {
    val icon = createRefFor("icon")
    val name = createRefFor("name")
    val cost = createRefFor("cost")
    val capacity = createRefFor("capacity")
    val description = createRefFor("description")
    val button = createRefFor("button")

    constrain(icon) {
        start.linkTo(parent.start)
        top.linkTo(parent.top)
    }

    constrain(name) {
        start.linkTo(icon.end, 20.dp)
        top.linkTo(icon.top)
        if (verticalPadding == 0.dp)
            bottom.linkTo(parent.bottom)
    }

    constrain(cost) {
        start.linkTo(name.start)
        top.linkTo(name.bottom, verticalPadding / 2)
    }

    constrain(capacity) {
        start.linkTo(parent.start)
        top.linkTo(icon.bottom, verticalPadding)
    }

    constrain(description) {
        start.linkTo(parent.start)
        top.linkTo(capacity.bottom, verticalPadding)
    }

    constrain(button) {
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(description.bottom, verticalPadding)
    }
}

@Composable
fun RoomCard(
    roomType: RoomType = RoomType.Developer,
    onClick: () -> Unit = {},
) {
    CustomCard(modifier = Modifier.padding(5.dp)) {
        var expanded by remember { mutableStateOf(false) }
        ConstraintLayout(
            constraintSet = roomCardConstraintSet(if (expanded) 20.dp else 0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(10.dp)
        ) {
            RoomIcon(
                modifier = Modifier
                    .layoutId("icon")
                    .size(if (expanded) 60.dp else 40.dp),
                roomType = roomType
            )
            Text(
                text = "$roomType",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.layoutId("name")
            )

            // expanded ui
            if (expanded) {
                PriceTag(
                    modifier = Modifier.layoutId("cost"),
                    text = costOf(roomType).displayCost()
                )

                InfoBox(
                    modifier = Modifier.layoutId("capacity"),
                    hashMapOf(
                        "capacity" to "${maxCapacityOf(roomType)} pax",
                        "running cost" to 300.displayCost()
                    ),
                )

                Text(
                    text = roomDescription(roomType),
                    color = colorScheme.onBackground.copy(alpha = 0.5f),
                    fontSize = 18.sp,
                    modifier = Modifier.layoutId("description"),
                )
                Button(
                    onClick = { onClick() },
                    modifier = Modifier
                        .layoutId("button")
                        .fillMaxWidth()
                ) {
                    Text(text = "select", fontSize = 20.sp)
                }
            }

        }
    }
}

val sampleKeyValue = hashMapOf(
    "capacity" to "5 pax",
    "duration" to "65 CW",
)

@Composable
fun PriceTag(modifier: Modifier = Modifier, text: String) {
    Text(
        text = text,
        fontSize = 25.sp,
        modifier = modifier
    )
}


