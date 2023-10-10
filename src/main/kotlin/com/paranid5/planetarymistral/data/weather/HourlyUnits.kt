package com.paranid5.planetarymistral.data.weather

import lombok.NoArgsConstructor

@NoArgsConstructor
data class HourlyUnits(
    val time: String,
    val rain: String,
    val showers: String,
    val snowfall: String,
    val cloudcover: String
)