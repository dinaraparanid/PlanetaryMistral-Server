package com.paranid5.planetarymistral.data.system

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.NoArgsConstructor

@NoArgsConstructor
data class SystemStatus(
    val location: SystemLocation,
    @JsonProperty("selectioninfo") val selectionInfo: String,
    val time: SystemTime,
    val view: SystemView
)