package com.triplerock.codeforce.data

data class Event(
    val type: EventType,
    val message: String,
    val time: Long
)

enum class EventType {
    FeatureStarted,
    FeatureComplete,
    FeatureReleased,
    FeatureBlocked,

    ProjectAwarded,
    ProjectKickOff,
    ProjectComplete,
    ProjectLost,

    EmployeeJoined,
    EmployeeResigned,
    EmployeeRecognitionAward,
}