package com.paranid5.planetarymistral.domain

import com.paranid5.planetarymistral.data.SpaceObject
import com.paranid5.planetarymistral.data.SpaceObjectType
import com.paranid5.planetarymistral.data.SystemStatus
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

inline val WebClient.systemStatus
    get() = get()
        .uri("/main/status")
        .accept(MediaType.APPLICATION_JSON)
        .exchangeToMono { response -> response.toResult<SystemStatus>() }

fun WebClient.setTime(jday: Double, timeRate: Double) =
    post()
        .uri { builder ->
            builder.path("/main/time")
                .queryParam("time", jday)
                .queryParam("timerate", timeRate)
                .build()
        }
        .accept(MediaType.TEXT_PLAIN)
        .exchangeToMono { response -> response.toResult<String>() }

fun WebClient.setLocation(latitude: Double, longitude: Double) =
    post()
        .uri { builder ->
            builder.path("/location/setlocationfields")
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .build()
        }
        .accept(MediaType.TEXT_PLAIN)
        .exchangeToMono { response -> response.toResult<String>() }

inline val WebClient.listSpaceObjectTypes
    get() = get()
        .uri("/objects/listobjecttypes")
        .accept(MediaType.APPLICATION_JSON)
        .exchangeToMono { response -> response.toResult<List<SpaceObjectType>>() }

fun WebClient.getSpaceObjectInfo(name: String) =
    get()
        .uri { builder ->
            builder.path("/objects/info")
                .queryParam("name", name)
                .queryParam("format", "json")
                .build()
        }
        .accept(MediaType.APPLICATION_JSON)
        .exchangeToMono { response -> response.toResult<SpaceObject>() }

inline fun <reified T : Any> ClientResponse.toResult() = when (statusCode()) {
    HttpStatus.OK -> bodyToMono<T>().map { Result.success(it!!) }
    else -> createException().map { Result.failure(it) }
}