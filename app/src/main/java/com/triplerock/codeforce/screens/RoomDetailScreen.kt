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
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
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
        RoomView(
            modifier = Modifier.padding(it),
            room = room,
            onEvictButtonClick = onEvictButtonClick
        )
    }
}

enum class LayoutId {
    Icon,
    Feature,
    Capacity,
    Description,
    AddEmployeeCard,
    Button,
}

private val roomDetailConstraints = ConstraintSet {
    val icon = createRefFor(LayoutId.Icon)
    val capacity = createRefFor(LayoutId.Capacity)
    val desc = createRefFor(LayoutId.Description)
    val add = createRefFor(LayoutId.AddEmployeeCard)
    val button = createRefFor(LayoutId.Button)
    val feature = createRefFor(LayoutId.Feature)

    constrain(icon) {
        start.linkTo(parent.start)
        top.linkTo(parent.top)
    }
    constrain(capacity) {
        end.linkTo(parent.end, 10.dp)
        top.linkTo(feature.bottom, 10.dp)
    }
    constrain(desc) {
        start.linkTo(parent.start)
        top.linkTo(icon.bottom, 50.dp)
    }
    constrain(add) {
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(desc.bottom, 60.dp)
    }
    constrain(button) {
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom, 30.dp)
    }
    constrain(feature) {
        end.linkTo(parent.end)
        top.linkTo(parent.top)
    }
}

@Composable
private fun RoomView(
    modifier: Modifier = Modifier,
    room: Room,
    onEvictButtonClick: () -> Unit,
) {
    ConstraintLayout(
        roomDetailConstraints,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        RoomIcon(
            modifier = Modifier
                .layoutId(LayoutId.Icon)
                .size(80.dp),
            roomType = room.type
        )
        CfDropDownButton(
            Modifier.layoutId(LayoutId.Feature),
            "Voice"

        )
        LabelValue(
            Modifier.layoutId(LayoutId.Capacity),
            "capacity", "${room.count}/${room.capacity}"
        )
        Text(
            text = roomDescription(room.type),
            color = colorScheme.onBackground,
            modifier = Modifier.layoutId(LayoutId.Description)
        )
        AddEmployeeCard(Modifier.layoutId(LayoutId.AddEmployeeCard))
        CfErrorButton(
            modifier = Modifier.layoutId(LayoutId.Button),
            text = "Evict Room",
            onClick = { onEvictButtonClick() },
        )
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
