package com.paranid5.planetarymistral.domain.utils.ext

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun <T> Result<T>.toResponseEntity(errorCode: HttpStatus = HttpStatus.SERVICE_UNAVAILABLE) =
    getOrNull()
        ?.let { ResponseEntity(it, HttpStatus.OK) }
        ?: ResponseEntity(errorCode)