package com.paranid5.planetarymistral.data.weather

import lombok.NoArgsConstructor

@NoArgsConstructor
data class WeatherForecastError(val error: String) : WeatherForecastResponse