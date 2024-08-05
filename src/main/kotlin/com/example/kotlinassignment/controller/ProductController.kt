package com.example.kotlinassignment.controller

import com.example.kotlinassignment.api.ProductApi
import com.example.kotlinassignment.model.Inventory
import com.example.kotlinassignment.model.Product
import com.example.kotlinassignment.service.InventoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import retrofit2.Call
import retrofit2.Response

@RestController
class ProductController(private val productApi: ProductApi, private val inventoryService: InventoryService) {

    @GetMapping("/products")
    fun getProducts(): List<Product>{
        val call: Call<List<Product>> = productApi.getProducts()
        return try {
            val response: Response<List<Product>> = call.execute()
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

    @GetMapping("/inventories")
    fun getInventories(): List<Inventory> {
        return inventoryService.getInventories()
    }
}