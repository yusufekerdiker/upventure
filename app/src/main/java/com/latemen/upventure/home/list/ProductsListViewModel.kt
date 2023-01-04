package com.latemen.upventure.home.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.latemen.upventure.hilt.repository.ProductsRepository
import com.latemen.upventure.model.domain.Filter
import com.latemen.upventure.model.domain.Product
import com.latemen.upventure.redux.ApplicationState
import com.latemen.upventure.redux.Store
import com.latemen.upventure.redux.reducer.UiProductListReducer
import com.latemen.upventure.redux.updater.UiProductFavoriteUpdater
import com.latemen.upventure.redux.updater.UiProductInCartUpdater
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.http.POST
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    val store: Store<ApplicationState>,
    val uiProductListReducer: UiProductListReducer,
    val uiProductFavoriteUpdater: UiProductFavoriteUpdater,
    val uiProductInCartUpdater: UiProductInCartUpdater,
    private val productsRepository: ProductsRepository,
    private val filterGenerator: FilterGenerator
) : ViewModel() {

    /*    val myResponse: MutableLiveData<Response<Product>> = MutableLiveData()
        fun pushProduct(product: Product) {
            viewModelScope.launch {
                val response = productsRepository.pushProduct(product)
                myResponse.value = response
            }
        }*/

    fun refreshProducts() = viewModelScope.launch {
        if (store.read { it.products }.isNotEmpty()) return@launch
        val products: List<Product> = productsRepository.fetchAllProducts()
        val filters: Set<Filter> = filterGenerator.generateFrom(products)
        store.update { applicationState ->
            return@update applicationState.copy(
                products = products,
                productFilterInfo = ApplicationState.ProductFilterInfo(
                    filters = filters,
                    selectedFilter = applicationState.productFilterInfo.selectedFilter
                ),
                explorePageMetadata = ApplicationState.ExplorePageMetadata(
                    selectedProductId = products.getOrNull(0)?.id ?: 0
                )
            )
        }
    }
}