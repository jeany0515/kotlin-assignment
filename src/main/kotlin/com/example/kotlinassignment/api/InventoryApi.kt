package com.example.kotlinassignment.api

import com.example.kotlinassignment.model.Inventory
import retrofit2.Call
import retrofit2.http.GET

interface InventoryApi {
    @GET("inventories")
    fun getInventories(): Call<List<Inventory>>
}