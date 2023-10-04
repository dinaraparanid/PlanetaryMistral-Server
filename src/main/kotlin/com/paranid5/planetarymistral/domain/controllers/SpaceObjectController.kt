package com.paranid5.planetarymistral.domain.controllers

import com.paranid5.planetarymistral.data.space.SpaceObjectType
import com.paranid5.planetarymistral.domain.clients.getListSpaceObjectTypes
import com.paranid5.planetarymistral.domain.clients.getListSpaceObjectsByType
import com.paranid5.planetarymistral.domain.utils.ext.toResponseEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient

@RestController
@RequestMapping("space-objects")
class SpaceObjectController {
    @Autowired
    @Qualifier("stellarium-client")
    private lateinit var stellariumClient: WebClient

    @GetMapping("/types", produces = ["application/json"])
    suspend fun getSpaceObjectTypes() =
        stellariumClient.getListSpaceObjectTypes().toResponseEntity()

    private suspend inline fun getSpaceObjectsByTypeImpl(type: String) =
        stellariumClient.getListSpaceObjectsByType(type).toResponseEntity()

    @GetMapping("/by-types", produces = ["application/json"])
    suspend fun getSpaceObjectsByType(@RequestBody types: List<SpaceObjectType>) =
        types
            .mapNotNull { stellariumClient.getListSpaceObjectsByType(it.key).getOrNull() }
            .flatten()
            .let { ResponseEntity(it, HttpStatus.OK) }

    @GetMapping("/info", produces = ["application/json"])
    suspend fun getSpaceObjectsByType(@RequestParam params: Map<String, String>) =
        params["name"]
            ?.let { stellariumClient.getListSpaceObjectsByType(it).toResponseEntity() }
            ?: ResponseEntity("`name` argument is not passed", HttpStatus.BAD_REQUEST)
}