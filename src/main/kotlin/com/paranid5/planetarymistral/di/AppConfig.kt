package com.paranid5.planetarymistral.di

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class AppConfig {
    @Bean(name = ["stellarium-client"])
    fun stellariumClient() =
        WebClient.create("http://localhost:8090/api")

    @Bean(name = ["weather-client"])
    fun weatherClient() =
        WebClient.create("https://api.open-meteo.com/v1")
}