package com.triplerock.codeforce.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.triplerock.codeforce.data.Customer
import com.triplerock.codeforce.data.Feature
import com.triplerock.codeforce.data.FeatureRequirement
import com.triplerock.codeforce.data.Project
import com.triplerock.codeforce.data.RoomType
import com.triplerock.codeforce.data.displayCost
import com.triplerock.codeforce.data.roomDescription
import com.triplerock.codeforce.data.sampleFeatureList
import com.triplerock.codeforce.data.sampleProjectNames
import com.triplerock.codeforce.data.sampleRewards
import com.triplerock.codeforce.data.totalCw
import com.triplerock.codeforce.ui.theme.CodeForceTheme

@DarkPreview
@Composable
fun PreviewProjectsView() {
    CodeForceTheme {
        ProjectsView()
    }
}


@Composable
fun ProjectsView() {
    Scaffold(topBar = { TitleBar("Current projects") }) {
        AllProjects(Modifier.padding(it))
    }
}

val sampleProject = Project(
    sampleProjectNames.random(),
    Customer.entries.random(),
    listOf(
        FeatureRequirement(Feature.AOSP, 20),
        FeatureRequirement(Feature.HMI, 25),
        FeatureRequirement(Feature.Voice, 30),
        FeatureRequirement(Feature.Radio, 15),
    ),
    sampleRewards.random(),
)

@Composable
fun AllProjects(
    modifier: Modifier = Modifier,
    projects: List<Project> = listOf()
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.padding(horizontal = 10.dp)
    ) {
        items(5) {
            ProjectCard(sampleProject)
        }
    }
}

@Composable
fun ProjectCard(project: Project) {
    Card {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 20.dp)
        ) {
            val (iconRef,
                nameRef,
                customerRef,
                rewardRef,
                featureRef,
                descRef,
                totalCwRef,
                btnRef
            ) = createRefs()
            Image(
                painter = painterResource(id = project.customer.iconRes),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(iconRef) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }
                    .size(100.dp)
                    .background(colorScheme.onPrimary, RoundedCornerShape(10.dp))
            )

            Text(text = project.customer.name,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(customerRef) {
                    start.linkTo(iconRef.end, 10.dp)
                    top.linkTo(iconRef.top)
                })

            LabelValue(modifier = Modifier.constrainAs(nameRef) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }, "project", project.name)

            LabelValue(modifier = Modifier.constrainAs(totalCwRef) {
                end.linkTo(parent.end)
                top.linkTo(nameRef.bottom, 10.dp)
            }, "duration", "${project.totalCw()} CWs")

            FeatureTags(modifier = Modifier.constrainAs(featureRef) {
                start.linkTo(parent.start)
                top.linkTo(totalCwRef.bottom, 20.dp)
            })
            Text(text = roomDescription(RoomType.Developer),
                modifier = Modifier.constrainAs(descRef) {
                    start.linkTo(parent.start)
                    top.linkTo(featureRef.bottom, 20.dp)
                })
            Text(
                text = project.reward.displayCost(),
                fontSize = 20.sp,
                modifier = Modifier
                    .constrainAs(rewardRef) {
                        start.linkTo(iconRef.end, 10.dp)
                        top.linkTo(customerRef.bottom, 10.dp)
                    }
                    .background(
                        colorScheme.primaryContainer,
                        RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            )
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .constrainAs(btnRef) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(descRef.bottom, 20.dp)
                    }
                    .fillMaxWidth()
            ) {
                Text(text = "Submit quote", fontSize = 20.sp)
            }
        }
    }
}

@DarkPreview
@Composable
fun FeatureTags(
    modifier: Modifier = Modifier,
    featureRequirements: List<FeatureRequirement> = sampleFeatureList
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(featureRequirements) {
            FeatureTag(it)
        }
    }
}

@Composable
fun FeatureTag(featureRequirement: FeatureRequirement) {
    Text(
        text = featureRequirement.feature.name,
        color = colorScheme.onPrimary,
        modifier = Modifier
            .background(
                colorScheme.primary,
                RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp, vertical = 5.dp)
    )
}

