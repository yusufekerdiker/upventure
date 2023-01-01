package com.latemen.upventure.redux.reducer

import com.latemen.upventure.model.ui.UiProduct
import com.latemen.upventure.redux.ApplicationState
import com.latemen.upventure.redux.Store
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UiProductListReducer @Inject constructor() {
    fun reduce(store: Store<ApplicationState>): Flow<List<UiProduct>> {
        return combine(
            store.stateFlow.map { it.products },
            store.stateFlow.map { it.favoriteProductIds },
            store.stateFlow.map { it.expandedProductIds },
            store.stateFlow.map { it.inCartProductIds }
        ) { listOfProducts, setOfFavoriteIds, setOfExpandedProductIds, inCartProductIds ->

            if (listOfProducts.isEmpty()) {
                return@combine emptyList<UiProduct>()
            }

            return@combine listOfProducts.map { product ->
                UiProduct(
                    product = product,
                    isFavorite = setOfFavoriteIds.contains(product.id),
                    isExpanded = setOfExpandedProductIds.contains(product.id),
                    isInCart = inCartProductIds.contains(product.id)
                )
            }
        }
    }
}