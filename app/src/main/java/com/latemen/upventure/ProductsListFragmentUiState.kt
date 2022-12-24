package com.latemen.upventure

import com.latemen.upventure.model.ui.UiFilter
import com.latemen.upventure.model.ui.UiProduct

data class ProductsListFragmentUiState(
    val filters: Set<UiFilter>,
    val products: List<UiProduct>
)
