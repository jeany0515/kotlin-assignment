package com.example.kotlinassignment.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class ProductInfo @JsonCreator constructor(
    @JsonProperty("id") val id: String,
    @JsonProperty("SKU") val SKU: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("price") val price: Double,
    @JsonProperty("type") val type: String,
    @JsonProperty("image") val image: String,
    @JsonProperty("quantity") val quantity: Int
)
