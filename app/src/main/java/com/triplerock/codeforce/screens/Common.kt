package com.triplerock.codeforce.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.layoutId
import com.triplerock.codeforce.R
import com.triplerock.codeforce.data.RoomType
import com.triplerock.codeforce.ui.theme.Black10
import com.triplerock.codeforce.ui.theme.Black20
import com.triplerock.codeforce.ui.theme.CodeForceTheme

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class DarkPreview

// @DarkPreview
@Composable
fun PreviewRoomIcon() {
    CodeForceTheme {
        LazyRow {
            items(RoomType.entries) {
                RoomIcon(roomType = it)
            }
        }
    }
}

@Composable
fun RoomIcon(modifier: Modifier = Modifier, roomType: RoomType = RoomType.Empty) {
    val icon = when (roomType) {
        RoomType.Developer -> R.drawable.coding_room
        RoomType.Manager -> R.drawable.manager_room
        RoomType.HR -> R.drawable.hr_room
        RoomType.Cafeteria -> R.drawable.cafe_room
        RoomType.Sales -> R.drawable.sales_room
        else -> R.drawable.coding_room
    }
    Image(
        painter = painterResource(id = icon),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun LabelValue(
    modifier: Modifier = Modifier,
    label: String = "label",
    value: String = "value",
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            color = colorScheme.onBackground.copy(alpha = 0.7f),
            fontSize = 15.sp,
        )
        Text(
            text = value,
            fontSize = 18.sp,
            color = colorScheme.onBackground
        )
    }
}

// @DarkPreview
@Composable
fun TitleBar(
    title: String = "CodeForce app",
    onCloseClick: () -> Unit = {},
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorScheme.background)
            .padding(horizontal = 10.dp, vertical = 10.dp),
    ) {
        val (iconRef, titleRef) = createRefs()
        CloseIcon(modifier = Modifier.constrainAs(iconRef) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }) {
            onCloseClick()
        }
        Text(
            text = title, fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = colorScheme.onBackground,
            textAlign = TextAlign.End,
            modifier = Modifier.constrainAs(titleRef) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )
    }
}

// @DarkPreview
@Composable
fun InfoBox(
    modifier: Modifier = Modifier,
    keyValue: HashMap<String, String> = sampleKeyValue,
) {
    LazyRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .layoutId("capacity")
            .background(
                Black20,
                RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .fillMaxWidth(),
    ) {
        items(keyValue.keys.toList()) {
            LabelValue(
                label = it,
                value = keyValue[it].toString(),
            )
        }
    }
}

// @DarkPreview
@Composable
fun PreviewCustomCard(
) {
    CodeForceTheme {
        Surface(
            modifier = Modifier
                .background(colorScheme.background)
                .padding(10.dp)
        ) {
            CustomCard {
                Text(
                    text = "this is the custom card",
                    Modifier.padding(20.dp)
                )
            }
        }
    }
}

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    color: Color = Black10,
    content: @Composable ColumnScope.() -> Unit = {},
) {
    Card(
        content = content,
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = color,
        )
    )
}

@DarkPreview
@Composable
fun PreviewNumberSelector() {
    CodeForceTheme {
        NumberSelector()
    }
}

@Composable
fun NumberSelector(
    modifier: Modifier = Modifier,
    onAddPress: () -> Unit = {},
    onMinusPress: () -> Unit = {},
    iconSize: Dp = 50.dp,
) {
    CustomCard(
        modifier = modifier,
        color = Black20
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = null,
                tint = colorScheme.primary,
                modifier = Modifier
                    .size(iconSize)
                    .padding(10.dp)
            )
            Text(
                text = "3",
                fontSize = (iconSize.value - 10).sp,
                color = colorScheme.onBackground
            )
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = colorScheme.primary,
                modifier = Modifier
                    .size(iconSize)
                    .padding(10.dp)
            )
        }
    }
}
