package com.paranid5.planetarymistral.data.space

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.NoArgsConstructor

@NoArgsConstructor
data class SpaceObjectType(
    val key: String,
    val name: String,
    @JsonProperty("name_i18n") val nameI18n: String
)