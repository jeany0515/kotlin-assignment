package com.example.kotlinassignment.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().apply {
            // Ensure there is no global naming strategy altering field names
            propertyNamingStrategy = null // No automatic naming strategy
        }
    }
}