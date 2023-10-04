package com.paranid5.planetarymistral.data.system

data class SystemStatus(
    val location: SystemLocation,
    val selectionInfo: String,
    val time: SystemTime,
    val view: SystemView
)