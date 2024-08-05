package com.example.kotlinassignment.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Inventory @JsonCreator constructor(
    @JsonProperty("id") val id: String,
    @JsonProperty("SKU") val SKU: String,
    @JsonProperty("zone") val zone: String,
    @JsonProperty("quantity") val quantity: Int
)