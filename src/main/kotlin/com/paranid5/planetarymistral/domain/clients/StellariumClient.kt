package com.paranid5.planetarymistral.domain.clients

import com.paranid5.planetarymistral.data.space.SpaceObject
import com.paranid5.planetarymistral.data.space.SpaceObjectType
import com.paranid5.planetarymistral.data.system.SystemStatus
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.*

suspend inline fun WebClient.getSystemStatus() =
    get()
        .uri("/main/status")
        .accept(MediaType.APPLICATION_JSON)
        .awaitExchange { response -> response.toResult<SystemStatus>() }

suspend inline fun WebClient.setTime(jday: Double, timeRate: Double) =
    post()
        .uri { builder ->
            builder.path("/main/time")
                .queryParam("time", jday)
                .queryParam("timerate", timeRate)
                .build()
        }
        .accept(MediaType.TEXT_PLAIN)
        .awaitExchange { response -> response.toResult<String>() }

suspend inline fun WebClient.setLocation(latitude: Double, longitude: Double) =
    post()
        .uri { builder ->
            builder.path("/location/setlocationfields")
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .build()
        }
        .accept(MediaType.TEXT_PLAIN)
        .awaitExchange { response -> response.toResult<String>() }

suspend inline fun WebClient.getListSpaceObjectTypes() =
    get()
        .uri("/objects/listobjecttypes")
        .accept(MediaType.APPLICATION_JSON)
        .awaitExchange { response -> response.toResult<List<SpaceObjectType>>() }

suspend inline fun WebClient.getListSpaceObjectsByType(type: String) =
    get()
        .uri { builder ->
            builder.path("/objects/listobjectsbytype")
                .queryParam("type", type)
                .build()
        }
        .accept(MediaType.APPLICATION_JSON)
        .awaitExchange { response -> response.toResult<List<String>>() }

suspend inline fun WebClient.getSpaceObjectInfo(name: String) =
    get()
        .uri { builder ->
            builder.path("/objects/info")
                .queryParam("name", name)
                .queryParam("format", "json")
                .build()
        }
        .accept(MediaType.APPLICATION_JSON)
        .awaitExchange { response -> response.toResult<SpaceObject>() }

suspend inline fun <reified T : Any> ClientResponse.toResult() = when (statusCode()) {
    HttpStatus.OK -> Result.success(awaitBody<T>())
    else -> Result.failure(createExceptionAndAwait())
}