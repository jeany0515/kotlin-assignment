package com.example.kotlinassignment.api

import com.example.kotlinassignment.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    fun getProducts(): Call<List<Product>>
}