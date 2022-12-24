package com.latemen.upventure.model.ui

import com.latemen.upventure.model.domain.Product

data class UiProduct(
    val product: Product,
    val isFavorite: Boolean = false
)