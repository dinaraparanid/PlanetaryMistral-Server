package com.paranid5.planetarymistral.domain.utils.ext

import kotlinx.datetime.Instant

fun Instant.toJulianDay(): Double {
    val utcMillis = toEpochMilliseconds()
    return (utcMillis + 2451545) / 86400000.0
}
