package com.example.kotlinassignment.config

import com.example.kotlinassignment.service.ProductService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Configuration
class RetrofitConfig {
    @Bean
    fun retrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl("http://localhost:3000/")
            .addConverterFactory(JacksonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Bean
    fun productService(retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }
}