package com.latemen.upventure.model.network

data class NetworkProduct(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    var title: String
) {
    data class Rating(
        val count: Int,
        val rate: Double
    )
}