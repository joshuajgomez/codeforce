package com.triplerock.codeforce.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.RadioButton
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
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.padding(10.dp),
    ) {

        CfDropDownButton()

        InfoBox(
            keyValue = hashMapOf(
                "idle developers" to "7/60",
                "cash balance" to 7800000.displayCost(),
                "candidates" to "36",
            ),
            color = colorScheme.background
        )
        HireCard(
            "Hire one candidate",
            cost = "${3000.displayCost()} / pax",
            isSelected = true,
        )
        HireCard(
            "Hire all candidates (46)",
            cost = 56600.displayCost()
        )
        HireCard(
            "Select number to hire",
            cost = 56600.displayCost(),
            isShowNumberSelector = true
        )
        Spacer(modifier = Modifier.height(10.dp))

        CfLargeButton("Hire")

        CfErrorButton(text = "Reject all")
    }
}

fun hireCardConstraints(isShowNumberSelector: Boolean) = ConstraintSet {
    val label = createRefFor("label")
    val cost = createRefFor("cost")
    val radio = createRefFor("radio")
    val selector = createRefFor("selector")

    constrain(label) {
        start.linkTo(parent.start)
        top.linkTo(parent.top)
    }

    if (isShowNumberSelector) {
        constrain(selector) {
            top.linkTo(label.bottom, 15.dp)
        }
        constrain(cost) {
            start.linkTo(parent.start)
            top.linkTo(selector.bottom, 10.dp)
        }
    } else {
        constrain(cost) {
            start.linkTo(parent.start)
            top.linkTo(label.bottom, 10.dp)
        }
    }

    constrain(radio) {
        end.linkTo(parent.end)
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
    }

}

@Composable
fun HireCard(
    text: String = "Hire one candidate",
    cost: String = "$ 5000 /  engineer",
    isSelected: Boolean = false,
    isShowNumberSelector: Boolean = false,
) {
    CfCard {
        ConstraintLayout(
            hireCardConstraints(isShowNumberSelector),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
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
