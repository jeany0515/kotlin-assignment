package com.example.kotlinassignment.service

import com.example.kotlinassignment.api.InventoryApi
import com.example.kotlinassignment.model.Inventory
import org.springframework.stereotype.Service
import retrofit2.Call
import retrofit2.Response

@Service
class InventoryService(private val inventoryApi: InventoryApi) {
    fun getInventories(): List<Inventory> {
        val call: Call<List<Inventory>> = inventoryApi.getInventories()
        return try {
            val response: Response<List<Inventory>> = call.execute()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                throw RuntimeException("Failed to load products: ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}