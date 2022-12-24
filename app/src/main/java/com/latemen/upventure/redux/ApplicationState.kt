package com.latemen.upventure.redux

import com.latemen.upventure.model.domain.Product

data class ApplicationState(
    val products: List<Product> = emptyList(),
    val favoriteProductIds: Set<Int> = emptySet()
)