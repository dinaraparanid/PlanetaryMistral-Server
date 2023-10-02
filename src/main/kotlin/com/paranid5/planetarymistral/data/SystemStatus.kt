package com.paranid5.planetarymistral.data

data class SystemStatus(
    val location: SpaceLocation,
    val selectionInfo: String,
    val time: SpaceTime,
    val view: SystemView
)