package com.latemen.upventure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latemen.upventure.model.domain.Filter
import com.latemen.upventure.model.domain.Product
import com.latemen.upventure.redux.ApplicationState
import com.latemen.upventure.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    val store: Store<ApplicationState>,
    private val productsRepository: ProductsRepository
): ViewModel() {

    fun refreshProducts() = viewModelScope.launch {
        val products: List<Product> = productsRepository.fetchAllProducts()
        store.update { applicationState ->
            return@update applicationState.copy(
                products = products,
                productFilterInfo = ApplicationState.ProductFilterInfo(
                    filters = products.map {
                        Filter(value = it.category, displayText = it.category) // todo
                    }.toSet(),
                    selectedFilter = null
                )
            )
        }
    }
}