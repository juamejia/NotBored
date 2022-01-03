package com.example.notbored.data.model

data class ResponseActivity(
    // Set values types API
    val activity: String,
    val accessibility: Double,
    val type: String,
    val participants: Int,
    val price: Double,
    val key: Int
)