package com.example.kotlinassignment.controller

import com.example.kotlinassignment.model.Product
import com.example.kotlinassignment.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import retrofit2.Call
import retrofit2.Response

@RestController
class ProductController(private val productService: ProductService) {

    @GetMapping("/products")
    fun getProducts(): List<Product>{
        val call: Call<List<Product>> = productService.getProducts()
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
}