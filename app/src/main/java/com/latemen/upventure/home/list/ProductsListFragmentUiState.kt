package com.latemen.upventure.home.list

import com.latemen.upventure.model.ui.UiFilter
import com.latemen.upventure.model.ui.UiProduct

sealed interface ProductsListFragmentUiState {

    data class Success(
        val filters: Set<UiFilter>,
        val products: List<UiProduct>
    ): ProductsListFragmentUiState

    object Loading: ProductsListFragmentUiState
}
