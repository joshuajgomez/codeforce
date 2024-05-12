package com.triplerock.codeforce.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import com.triplerock.codeforce.data.Customer
import com.triplerock.codeforce.data.Feature
import com.triplerock.codeforce.data.FeatureRequirement
import com.triplerock.codeforce.data.Project
import com.triplerock.codeforce.data.ProjectState
import com.triplerock.codeforce.data.RoomType
import com.triplerock.codeforce.data.completedCw
import com.triplerock.codeforce.data.displayCost
import com.triplerock.codeforce.data.roomDescription
import com.triplerock.codeforce.data.sampleFeatureList
import com.triplerock.codeforce.data.sampleProjectNames
import com.triplerock.codeforce.data.sampleProjects
import com.triplerock.codeforce.data.sampleRewards
import com.triplerock.codeforce.data.totalCw
import com.triplerock.codeforce.screens.ui.theme.Blue40
import com.triplerock.codeforce.ui.theme.CodeForceTheme
import com.triplerock.codeforce.screens.ui.theme.Gray10
import com.triplerock.codeforce.screens.ui.theme.Green50
import com.triplerock.codeforce.screens.ui.theme.Orange10
import com.triplerock.codeforce.screens.ui.theme.Purple20
import com.triplerock.codeforce.screens.ui.theme.Red10

@DarkPreview
@Composable
fun PreviewProjectsScreen() {
    CodeForceTheme {
        ProjectsScreen()
    }
}

@Composable
fun ProjectsScreen() {
    Scaffold(topBar = { TitleBar("Projects") }) {
        ProjectsView(Modifier.padding(it))
    }
}

@Composable
fun ProjectsView(modifier: Modifier) {
    Column(
        modifier.padding(horizontal = 15.dp),
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        FilterTags(
            ProjectState.entries.map { it.name } + "ALL",
            ProjectState.NEW.name
        ) {

        }
        Spacer(modifier = Modifier.height(20.dp))
        ProjectsList()
    }
}

fun sampleProject() = Project(
    sampleProjectNames.random(),
    Customer.entries.random(),
    listOf(
        FeatureRequirement(Feature.AOSP, 20),
        FeatureRequirement(Feature.HMI, 25),
        FeatureRequirement(Feature.Voice, 30),
        FeatureRequirement(Feature.Radio, 15),
    ),
    sampleRewards.random(),
    ProjectState.entries.random()
)

@Composable
fun ProjectsList(
    modifier: Modifier = Modifier,
    projects: List<Project> = sampleProjects(),
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        projects
            .sortedBy { it.state }
            .groupBy { it.state }
            .forEach { (projectState, list) ->
                item {
                    ProjectCardHeader(projectState, list.size)
                }
                items(list) {
                    ProjectCard(it)
                }
            }
    }
}

@Composable
fun ProjectCardHeader(
    projectState: ProjectState = ProjectState.COMPLETE,
    count: Int = 5
) {
    Text(
        text = "${projectState.name} ($count)",
        modifier = Modifier.padding(top = 20.dp, start = 10.dp)
    )
}

//@DarkPreview
@Composable
fun PreviewProjectCards(
) {
    CodeForceTheme {
        Column {
            ProjectCard()
            Spacer(modifier = Modifier.height(20.dp))
            ProjectCard(isExpanded = true)
        }
    }
}

private fun projectCardConstraints(): ConstraintSet {
    return ConstraintSet {
        val logo = createRefFor("customer_logo")
        val projectName = createRefFor("project_name")
        val state = createRefFor("project_state")
        val reward = createRefFor("reward")

        constrain(logo) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
        }
        constrain(projectName) {
            start.linkTo(logo.end, 20.dp)
            top.linkTo(parent.top)
        }
        constrain(state) {
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        }
        constrain(reward) {
            start.linkTo(projectName.start)
            top.linkTo(projectName.bottom, 10.dp)
        }
    }
}

private fun expandedProjectCardConstraints(): ConstraintSet {
    return ConstraintSet {
        val customerName = createRefFor("customer_name")
        val features = createRefFor("features")
        val description = createRefFor("description")
        val button = createRefFor("button")

        // expanded ui
        constrain(customerName) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
        }
        constrain(features) {
            start.linkTo(parent.start)
            top.linkTo(customerName.bottom, 20.dp)
        }
        constrain(description) {
            start.linkTo(parent.start)
            top.linkTo(features.bottom, 20.dp)
        }
        constrain(button) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            top.linkTo(description.bottom, 30.dp)
        }
    }
}

@Composable
fun ProjectCard(
    project: Project = sampleProject(),
    isExpanded: Boolean = false,
    onSubmitClick: () -> Unit = {},
) {
    var expanded by remember { mutableStateOf(isExpanded) }
    CfCard(radius = 5.dp) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .clickable { expanded = !expanded }
        ) {
            ConstraintLayout(
                constraintSet = projectCardConstraints(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = project.customer.iconRes),
                    contentDescription = null,
                    modifier = Modifier
                        .layoutId("customer_logo")
                        .size(50.dp)
                        .background(colorScheme.onPrimary, RoundedCornerShape(10.dp))
                )
                State(
                    modifier = Modifier.layoutId("project_state"),
                    project = project
                )
                Text(
                    text = project.reward.displayCost(),
                    color = colorScheme.onBackground,
                    modifier = Modifier.layoutId("reward")
                )
                Text(
                    modifier = Modifier.layoutId("project_name"),
                    text = project.name,
                    color = colorScheme.onBackground,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

            }
            // expanded ui
            AnimatedVisibility(visible = expanded) {
                ProjectDetail(project) {
                    onSubmitClick()
                }
            }
        }
    }
}

@Composable
fun ProjectDetail(
    project: Project,
    onSubmitClick: () -> Unit = {},
) {
    ConstraintLayout(
        expandedProjectCardConstraints(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {

        InfoBox(
            modifier =
            Modifier.layoutId("customer_name"),
            hashMapOf(
                "customer" to project.customer.name,
                "duration" to "${project.totalCw()} CW",
                "engineers" to "27 pax",
            )
        )

        FeatureTags(
            modifier = Modifier.layoutId("features")
        )
        Text(
            text = roomDescription(RoomType.Developer),
            color = colorScheme.onBackground,
            modifier = Modifier.layoutId("description")
        )
        CfLargeButton(
            text = "Submit quote",
            onClick = { onSubmitClick() },
            modifier = Modifier.layoutId("button")
        )
    }
}

//@DarkPreview
@Composable
fun PreviewState() {
    CodeForceTheme {
        CfCard {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                ProjectState.entries.forEach {
                    State(project = sampleProject().apply {
                        state = it
                    })
                }
            }
        }
    }
}

@Composable
fun State(modifier: Modifier = Modifier, project: Project) {
    var text = project.state.name
    if (project.state == ProjectState.ONGOING)
        text += " ${project.completedCw()}/${project.totalCw()}"

    val backgroundColor = when (project.state) {
        ProjectState.NEW -> Red10
        ProjectState.QUOTED -> Blue40
        ProjectState.AWARDED -> Green50
        ProjectState.ONGOING -> Orange10
        ProjectState.COMPLETE -> Purple20
        ProjectState.LOST -> Gray10
    }

    Text(
        color = colorScheme.onBackground,
        modifier = modifier
            .background(
                backgroundColor,
                RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp, vertical = 3.dp),
        text = text,
    )
}

//@DarkPreview
@Composable
fun FeatureTags(
    modifier: Modifier = Modifier,
    featureRequirements: List<FeatureRequirement> = sampleFeatureList,
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

