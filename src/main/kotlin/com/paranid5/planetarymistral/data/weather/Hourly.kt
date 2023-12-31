package com.paranid5.planetarymistral.data.weather

import lombok.NoArgsConstructor

@NoArgsConstructor
data class Hourly(
    val time: List<String>,
    val rain: List<Double>,
    val showers: List<Double>,
    val snowfall: List<Double>,
    val cloudcover: List<Int>
)
