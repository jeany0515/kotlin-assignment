package com.example.kotlinassignment.controller

import com.example.kotlinassignment.model.ProductInfo
import com.example.kotlinassignment.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(private val productService: ProductService) {

    @GetMapping("/products")
    fun getProductInfo(): List<ProductInfo>{
        return productService.getProductInfo()
    }
}