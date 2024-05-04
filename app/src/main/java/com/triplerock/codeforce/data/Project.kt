package com.triplerock.codeforce.data

import com.triplerock.codeforce.R

enum class Customer(val iconRes: Int) {
    Teapot(R.drawable.ic_launcher_foreground),
    Mehica(R.drawable.ic_launcher_foreground),
    Prolice(R.drawable.ic_launcher_foreground),
    Yoland(R.drawable.ic_launcher_foreground),
    Yellowstone(R.drawable.ic_launcher_foreground),
    KLR(R.drawable.ic_launcher_foreground),
    Ramendra(R.drawable.ic_launcher_foreground),
}

enum class Feature {
    Audio,
    Media,
    Radio,
    Voice,
    HMI,
    AOSP,
    BSP,
}

enum class ProjectState {
    NEW,
    QUOTED,
    AWARDED,
    ONGOING,
    COMPLETE,
    LOST,
}

enum class FeatureState {
    NEW,
    REVIEW,
    CODING,
    TESTING,
    COMPLETE,
}

data class FeatureRequirement(
    val feature: Feature,
    val totalCw: Int,
    var spentCw: Int = 0,
    val state: FeatureState = FeatureState.NEW,
)

data class Project(
    val name: String,
    val customer: Customer,
    val featureRequirements: List<FeatureRequirement>,
    val reward: Int,
    var state: ProjectState = ProjectState.NEW,
)

fun Project.totalCw(): Int {
    var cw = 0
    featureRequirements.forEach {
        cw += it.totalCw
    }
    return cw
}

fun Project.completedCw(): Int {
    var cw = 0
    featureRequirements.forEach {
        cw += it.spentCw
    }
    return cw
}

val sampleProjectNames = listOf(
    "Maestro2.5",
    "B230",
    "Sky760",
    "Pep98",
    "Cola60",
    "Com666",
)

val sampleTotalCw = listOf(50, 40, 34, 26, 12, 78)

val sampleRewards = listOf(1000000, 2000000, 25000000, 46000000)

val sampleFeatureList = listOf(
    FeatureRequirement(Feature.entries.random(), sampleTotalCw.random()),
    FeatureRequirement(Feature.entries.random(), sampleTotalCw.random()),
    FeatureRequirement(Feature.entries.random(), sampleTotalCw.random()),
    FeatureRequirement(Feature.entries.random(), sampleTotalCw.random()),
)

fun sampleProjects() = listOf(
    Project(
        sampleProjectNames.first(),
        Customer.entries[3],
        sampleFeatureList,
        sampleRewards[1]
    ),
    Project(
        sampleProjectNames[3],
        Customer.entries[2],
        sampleFeatureList,
        sampleRewards[2],
        ProjectState.AWARDED
    ),
    Project(
        sampleProjectNames[3],
        Customer.entries[0],
        sampleFeatureList,
        sampleRewards[3],
        ProjectState.QUOTED
    ),
    Project(
        sampleProjectNames[4],
        Customer.entries[5],
        sampleFeatureList,
        sampleRewards[0]
    ),
    Project(
        sampleProjectNames[1],
        Customer.entries[3],
        sampleFeatureList,
        sampleRewards[3],
        ProjectState.COMPLETE
    ),
    Project(
        sampleProjectNames[5],
        Customer.entries[4],
        sampleFeatureList,
        sampleRewards[2]
    ),
)