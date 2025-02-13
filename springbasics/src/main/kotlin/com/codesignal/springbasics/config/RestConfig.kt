package com.codesignal.springbasics.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Configuration
class RestConfig {
    @Bean("restTemplate")
    fun restTemplate():RestTemplate{
        return RestTemplate()
    }
}