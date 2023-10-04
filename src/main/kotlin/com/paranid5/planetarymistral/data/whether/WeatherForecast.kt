package com.paranid5.planetarymistral.data.whether

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class WeatherForecast(
    val latitude: Double,
    val longitude: Double,
    @JsonProperty("generationtime_ms") val generationTimeMs: Double,
    @JsonProperty("utc_offset_seconds") val utcOffsetSeconds: Int,
    val timezone: String,
    @JsonProperty("timezone_abbreviation") val timezoneAbbreviation: String,
    val elevation: Double,
    @JsonProperty("hourly_units") val hourlyUnits: HourlyUnits,
    val hourly: Hourly
)