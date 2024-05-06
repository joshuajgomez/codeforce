package com.triplerock.codeforce.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.triplerock.codeforce.data.displayCost
import com.triplerock.codeforce.ui.theme.CodeForceTheme

@DarkPreview
@Composable
fun PreviewHireEmployeeScreen() {
    CodeForceTheme {
        HireEmployeeScreen()
    }
}

@Composable
fun HireEmployeeScreen() {
    Scaffold(topBar = { TitleBar("Hire Employees") }) {
        CandidateListContainer(Modifier.padding(it))
    }
}

@Composable
fun CandidateListContainer(modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier.padding(10.dp),
    ) {
        InfoBox(
            keyValue = hashMapOf(
                "employees" to "78",
                "cash balance" to 7800000.displayCost(),
                "candidates" to "36",
            ),
            color = colorScheme.background
        )
        HireCard(
            "Accept one",
            cost = "${3000.displayCost()} / pax",
            isSelected = true,
        )
        HireCard(
            "Accept all (46)",
            cost = 56600.displayCost()
        )
        HireCard(
            "Accept some",
            cost = 56600.displayCost(),
            isShowNumberSelector = true
        )
        ElevatedButton(
            onClick = { /*TODO*/ }, modifier = Modifier
        ) {
            Text(
                text = "Hire",
                color = colorScheme.primary
            )
        }
        ElevatedButton(onClick = { /*TODO*/ }) {
            Text(
                text = "Reject all",
                color = colorScheme.error
            )
        }
    }
}

val hireCardConstraints = ConstraintSet {
    val label = createRefFor("label")
    val cost = createRefFor("cost")
    val radio = createRefFor("radio")
    val selector = createRefFor("selector")

    constrain(label) {
        start.linkTo(parent.start)
        top.linkTo(parent.top)
    }

    constrain(cost) {
        start.linkTo(parent.start)
        bottom.linkTo(parent.bottom)
    }

    constrain(radio) {
        end.linkTo(parent.end)
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
    }

    constrain(selector) {
        start.linkTo(parent.start)
        top.linkTo(label.bottom, 10.dp)
        bottom.linkTo(cost.top, 10.dp)
    }

}

@Composable
fun HireCard(
    text: String = "Accept one",
    cost: String = "$ 5000 /  engineer",
    isSelected: Boolean = false,
    isShowNumberSelector: Boolean = false,
) {
    CfCard {
        ConstraintLayout(
            hireCardConstraints,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            Text(
                text = text,
                color = colorScheme.onBackground.copy(alpha = 0.5f),
                modifier = Modifier.layoutId("label")
            )
            Text(
                text = cost,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.layoutId("cost")
            )
            RadioButton(
                onClick = { /*TODO*/ },
                selected = isSelected,
                modifier = Modifier.layoutId("radio")
            )
            if (isShowNumberSelector)
                NumberSelector(
                    modifier = Modifier.layoutId("selector"),
                    iconSize = 40.dp
                )
        }
    }
}