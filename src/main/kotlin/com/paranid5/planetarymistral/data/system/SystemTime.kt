package com.paranid5.planetarymistral.data.system

import lombok.NoArgsConstructor

@NoArgsConstructor
data class SystemTime(
    val deltaT: Double,
    val gmtShift: Double,
    val isTimeNow: Boolean,
    val jday: Double,
    val local: String,
    val timeZone: String,
    val timerate: Double,
    val utc: String
)