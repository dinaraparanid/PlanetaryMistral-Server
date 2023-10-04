package com.paranid5.planetarymistral.domain.controllers

import com.paranid5.planetarymistral.domain.clients.getSystemStatus
import com.paranid5.planetarymistral.domain.clients.setLocation
import com.paranid5.planetarymistral.domain.clients.setTime
import com.paranid5.planetarymistral.domain.utils.ext.toJulianDay
import com.paranid5.planetarymistral.domain.utils.ext.toResponseEntity
import kotlinx.datetime.Instant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient

@RestController
@RequestMapping("system")
class SystemController {
    @Autowired
    @Qualifier("stellarium-client")
    private lateinit var stellariumClient: WebClient

    @GetMapping("/system_status", produces = ["application/json"])
    suspend fun getSystemStatus() =
        stellariumClient.getSystemStatus().toResponseEntity()

    @PostMapping("/time", produces = ["plain/text"])
    suspend fun setTime(@RequestParam params: Map<String, String>): ResponseEntity<String> {
        val jday = params["time"]
            ?.let { time -> runCatching { Instant.parse(time) }.getOrNull() }
            ?.let(Instant::toJulianDay)
            ?: return ResponseEntity("`time` argument is not passed", HttpStatus.BAD_REQUEST)

        val timeRate = params["timerate"]
            ?.toDoubleOrNull()
            ?: return ResponseEntity("`timerate` argument is not passed", HttpStatus.BAD_REQUEST)

        return stellariumClient
            .setTime(jday, timeRate)
            .getOrNull()
            ?.let { ResponseEntity("OK", HttpStatus.OK) }
            ?: ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE)
    }

    @PostMapping("/location", produces = ["plain/text"])
    suspend fun setLocation(@RequestParam params: Map<String, String>): ResponseEntity<String> {
        val latitude = params["latitude"]
            ?.toDoubleOrNull()
            ?: return ResponseEntity("`latitude` argument is not passed", HttpStatus.BAD_REQUEST)

        val longitude = params["longitude"]
            ?.toDoubleOrNull()
            ?: return ResponseEntity("`longitude` argument is not passed", HttpStatus.BAD_REQUEST)

        return stellariumClient
            .setLocation(latitude, longitude)
            .getOrNull()
            ?.let { ResponseEntity("OK", HttpStatus.OK) }
            ?: ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE)
    }
}