package com.paranid5.planetarymistral

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude=[DataSourceAutoConfiguration::class])
class PlanetaryMistralApplication

fun main(args: Array<String>) {
    runApplication<PlanetaryMistralApplication>(*args)
}
