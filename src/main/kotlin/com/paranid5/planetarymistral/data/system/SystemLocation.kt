package com.paranid5.planetarymistral.data.system

import lombok.NoArgsConstructor

@NoArgsConstructor
data class SystemLocation(
    val altitude: Int,
    val landscapeKey: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val planet: String,
    val region: String,
    val role: String,
    val state: String
)