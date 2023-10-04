package com.paranid5.planetarymistral.domain.controllers

import com.paranid5.planetarymistral.data.weather.WeatherForecastError
import com.paranid5.planetarymistral.data.weather.WeatherForecastResponse
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

@RestController
@RequestMapping("weather")
class WeatherController {
    @Autowired
    @Qualifier("weather-client")
    private lateinit var wheatherClient: WebClient

    @GetMapping("/forecast", produces = ["application/json"])
    suspend fun getForecast(@RequestParam params: Map<String, String>): ResponseEntity<WeatherForecastResponse> {
        val latitude = params["latitude"]
            ?.toDoubleOrNull()
            ?: return ResponseEntity(
                WeatherForecastError("`latitude` argument is not passed"),
                HttpStatus.BAD_REQUEST
            )

        val longtitude = params["longitude"]
            ?.toDoubleOrNull()
            ?: return ResponseEntity(
                WeatherForecastError("`longitude` argument is not passed"),
                HttpStatus.BAD_REQUEST
            )

        return wheatherClient
            .getForecast(latitude, longtitude)
            .getOrNull()
            ?.let { ResponseEntity(it, HttpStatus.OK) }
            ?: throw IllegalStateException()
    }
}