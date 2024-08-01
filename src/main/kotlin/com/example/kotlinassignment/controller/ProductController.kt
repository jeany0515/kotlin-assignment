package com.example.kotlinassignment.controller

import com.example.kotlinassignment.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController() {

    @GetMapping("/products")
    fun getProducts(): String{
        return "products"
    }
}