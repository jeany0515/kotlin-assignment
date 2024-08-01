package com.example.kotlinassignment.service

import com.example.kotlinassignment.model.Inventory
import com.example.kotlinassignment.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    fun getProducts(): Call<List<Product>>
}

interface InventoryService {
    @GET("inventories")
    fun getInventories(): Call<List<Inventory>>
}