package com.example.kotlinassignment.service

import com.example.kotlinassignment.api.InventoryApi
import com.example.kotlinassignment.api.ProductApi
import com.example.kotlinassignment.model.Inventory
import com.example.kotlinassignment.model.Product
import com.example.kotlinassignment.model.ProductInfo
import org.springframework.stereotype.Service
import retrofit2.Call
import retrofit2.Response

@Service
class ProductService(
    private val productApi: ProductApi,
    private val inventoryApi: InventoryApi
) {
    fun getProductInfo(): List<ProductInfo> {
        val productCall: Call<List<Product>> = productApi.getProducts()
        val inventoryCall: Call<List<Inventory>> = inventoryApi.getInventories()

        return try {
            val productResponse: Response<List<Product>> = productCall.execute()
            val inventoryResponse: Response<List<Inventory>> = inventoryCall.execute()

            if (!productResponse.isSuccessful) {
                throw RuntimeException("Failed to load products: ${productResponse.errorBody()?.string()}")
            }

            if (!inventoryResponse.isSuccessful) {
                throw RuntimeException("Failed to load inventories: ${inventoryResponse.errorBody()?.string()}")
            }

            val products = productResponse.body() ?: emptyList()
            val inventories = inventoryResponse.body() ?: emptyList()

            processProduct(products, inventories)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun processProduct(products: List<Product>, inventories: List<Inventory>): List<ProductInfo>  {
        return products.map { product ->
            val totalQuantity = inventories
                .filter { it.SKU == product.SKU }
                .sumOf { it.quantity }

            val actualPrice = when (product.type) {
                "NORMAL" -> product.price
                "HIGH_DEMAND" -> calculateHighDemandPrice(product.price, totalQuantity)
                else -> product.price
            }

            ProductInfo(
                id = product.id,
                SKU = product.SKU,
                name = product.name,
                price = actualPrice,
                type = product.type,
                image = product.image,
                quantity = totalQuantity
            )
        }

    }

    private fun calculateHighDemandPrice(originalPrice: Double, quantity: Int): Double {
        return when {
            quantity > 100 -> originalPrice
            quantity in 31..100 -> originalPrice * 1.2
            quantity <= 30 -> originalPrice * 1.5
            else -> originalPrice
        }
    }
}