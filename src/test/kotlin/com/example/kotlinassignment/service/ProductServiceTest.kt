package com.example.kotlinassignment.service

import com.example.kotlinassignment.api.InventoryApi
import com.example.kotlinassignment.api.ProductApi
import com.example.kotlinassignment.model.Inventory
import com.example.kotlinassignment.model.Product
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Response

internal class ProductServiceTest {

    private lateinit var productService: ProductService
    private lateinit var productApi: ProductApi
    private lateinit var inventoryApi: InventoryApi

    @BeforeEach
    fun setUp() {
        productApi = Mockito.mock(ProductApi::class.java)
        inventoryApi = Mockito.mock(InventoryApi::class.java)
        productService = ProductService(productApi, inventoryApi)
    }

    @Test
    fun `getProductInfo should return processed product info when API responses are successful`() {
        // Arrange
        val products = listOf(
            Product("1", "ABC123", "Watch", 299.99, "NORMAL", "image1.jpg"),
            Product("2", "DEF456", "Shoes", 499.6, "HIGH_DEMAND", "image2.jpg")
        )
        val inventories = listOf(
            Inventory("1", "ABC123", "CN_NORTH", 120),
            Inventory("2", "DEF456", "EU_CENTRAL", 85)
        )

        // Suppress unchecked cast warning for Call<List<Product>>
        @Suppress("UNCHECKED_CAST")
        val productCall = Mockito.mock(Call::class.java) as Call<List<Product>>

        // Suppress unchecked cast warning for Call<List<Inventory>>
        @Suppress("UNCHECKED_CAST")
        val inventoryCall = Mockito.mock(Call::class.java) as Call<List<Inventory>>

        Mockito.`when`(productCall.execute()).thenReturn(Response.success(products))
        Mockito.`when`(inventoryCall.execute()).thenReturn(Response.success(inventories))
        Mockito.`when`(productApi.getProducts()).thenReturn(productCall)
        Mockito.`when`(inventoryApi.getInventories()).thenReturn(inventoryCall)

        // Act
        val productInfoList = productService.getProductInfo()

        // Assert
        assertEquals(2, productInfoList.size)

        val productInfo1 = productInfoList[0]
        assertEquals("1", productInfo1.id)
        assertEquals("ABC123", productInfo1.SKU)
        assertEquals(299.99, productInfo1.price)
        assertEquals(120, productInfo1.quantity)

        val productInfo2 = productInfoList[1]
        assertEquals("2", productInfo2.id)
        assertEquals("DEF456", productInfo2.SKU)
        assertEquals(499.6 * 1.2, productInfo2.price) // 120% of original price
        assertEquals(85, productInfo2.quantity)
    }

    @Test
    fun `getProductInfo should return empty list when API responses fail`() {
        // Arrange
        @Suppress("UNCHECKED_CAST")
        val productCall = Mockito.mock(Call::class.java) as Call<List<Product>>
        @Suppress("UNCHECKED_CAST")
        val inventoryCall = Mockito.mock(Call::class.java) as Call<List<Inventory>>

        Mockito.`when`(productApi.getProducts()).thenReturn(productCall)
        Mockito.`when`(inventoryApi.getInventories()).thenReturn(inventoryCall)

        // Act
        val productInfoList = productService.getProductInfo()

        // Assert
        assertTrue(productInfoList.isEmpty())
    }

    @Test
    fun `processProduct should calculate total quantity and correct prices`() {
        // Arrange
        val products = listOf(
            Product("1", "ABC123", "Watch", 299.99, "NORMAL", "image1.jpg"),
            Product("2", "DEF456", "Shoes", 499.6, "HIGH_DEMAND", "image2.jpg")
        )
        val inventories = listOf(
            Inventory("1", "ABC123", "CN_NORTH", 50),
            Inventory("2", "DEF456", "EU_CENTRAL", 20)
        )

        // Act
        val productInfoList = productService.processProduct(products, inventories)

        // Assert
        assertEquals(2, productInfoList.size)

        val productInfo1 = productInfoList[0]
        assertEquals("1", productInfo1.id)
        assertEquals("ABC123", productInfo1.SKU)
        assertEquals(299.99, productInfo1.price)
        assertEquals(50, productInfo1.quantity)

        val productInfo2 = productInfoList[1]
        assertEquals("2", productInfo2.id)
        assertEquals("DEF456", productInfo2.SKU)
        assertEquals(499.6 * 1.5, productInfo2.price) // 150% of original price
        assertEquals(20, productInfo2.quantity)
    }
}