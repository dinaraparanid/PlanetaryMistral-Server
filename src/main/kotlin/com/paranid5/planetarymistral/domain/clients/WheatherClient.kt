package com.paranid5.planetarymistral.domain.clients

import com.paranid5.planetarymistral.data.whether.WeatherForecast
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitExchange

suspend inline fun WebClient.getForecast(latitude: Double, longtitude: Double) =
    get()
        .uri { builder ->
            builder.path("/forecast")
                .queryParam("latitude", latitude)
                .queryParam("longitude", longtitude)
                .queryParam("hourly", "rain,showers,snowfall,cloudcover")
                .build()
        }
        .accept(MediaType.APPLICATION_JSON)
        .awaitExchange { response -> response.toResult<WeatherForecast>() }