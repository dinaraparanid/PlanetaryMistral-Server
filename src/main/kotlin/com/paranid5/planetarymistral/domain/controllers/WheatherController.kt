package com.paranid5.planetarymistral.domain.controllers

import com.paranid5.planetarymistral.data.whether.WeatherForecast
import com.paranid5.planetarymistral.domain.clients.getForecast
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("wheather")
class WheatherController {
    @Autowired
    @Qualifier("wheather-client")
    private lateinit var wheatherClient: WebClient

    @GetMapping("/forecast", produces = ["application/json"])
    suspend fun getForecast(@RequestParam params: Map<String, String>): ResponseEntity<WeatherForecast> {
        try {
            val latitude = params["latitude"]
                ?.toDoubleOrNull()
                ?: throw IllegalArgumentException("`latitude` argument is not passed")

            val longtitude = params["longitude"]
                ?.toDoubleOrNull()
                ?: throw IllegalArgumentException("`longitude` argument is not passed")

            return wheatherClient
                .getForecast(latitude, longtitude)
                .getOrNull()
                ?.let { ResponseEntity(it, HttpStatus.OK) }
                ?: throw IllegalStateException()
        } catch (e: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        } catch (e: IllegalStateException) {
            throw ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE)
        }
    }
}