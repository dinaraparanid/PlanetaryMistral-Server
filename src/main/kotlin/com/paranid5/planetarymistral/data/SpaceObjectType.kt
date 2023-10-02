package com.paranid5.planetarymistral.data

import com.fasterxml.jackson.annotation.JsonProperty

data class SpaceObjectType(
    val key: String,
    val name: String,
    @JsonProperty("name_i18n") val nameI18n: String
)