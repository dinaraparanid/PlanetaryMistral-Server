package com.paranid5.planetarymistral.di

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class AppConfig {
    @Bean
    fun stellariumClient() =
        WebClient.create("http://localhost:8090/api")
}