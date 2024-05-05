package com.triplerock.codeforce.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import com.triplerock.codeforce.data.Event
import com.triplerock.codeforce.data.EventType
import com.triplerock.codeforce.ui.theme.Blue40
import com.triplerock.codeforce.ui.theme.CodeForceTheme
import com.triplerock.codeforce.ui.theme.Gray10
import com.triplerock.codeforce.ui.theme.Green40
import com.triplerock.codeforce.ui.theme.Orange10

@DarkPreview
@Composable
fun PreviewEventsView() {
    CodeForceTheme {
        EventsView()
    }
}

@Composable
fun EventsView() {
    Scaffold(topBar = { TitleBar("Events") }) {
        EventsList(Modifier.padding(it))
    }
}

@DarkPreview
@Composable
fun PreviewEventsViewShort() {
    CodeForceTheme {
        EventsViewShort()
    }
}

@Composable
fun EventsViewShort() {
    CustomCard(
        modifier = Modifier.height(150.dp)
    ) {
        EventsList()
    }
}

@Composable
fun EventsList(modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        items(EventType.entries) {
            EventItem(
                Event(
                    type = it,
                    message = "Something happened somewhere",
                    time = System.currentTimeMillis()
                )
            )
        }
    }
}

val eventViewConstraints = ConstraintSet {
    val event = createRefFor("event")
    val time = createRefFor("time")
    val message = createRefFor("message")

    constrain(event) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
    }

    constrain(time) {
        top.linkTo(parent.top)
        start.linkTo(event.end, 10.dp)
    }

    constrain(message) {
        top.linkTo(event.bottom)
        start.linkTo(parent.start)
    }
}

@Composable
fun EventItem(event: Event) {
    ConstraintLayout(eventViewConstraints) {
        val color =
            if (event.type.name.startsWith("Employee")) Green40
            else if (event.type.name.startsWith("Feature")) Orange10
            else if (event.type.name.startsWith("Project")) Blue40
            else Gray10
        Text(
            text = "[ ${event.type} ]",
            modifier = Modifier.layoutId("event"),
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = "July 14, 2024 4:55pm", modifier = Modifier.layoutId("time"),
            color = colorScheme.onBackground.copy(alpha = 0.5f)
        )
        Text(
            text = event.message, modifier = Modifier.layoutId("message"),
            color = colorScheme.onBackground
        )
    }
}
