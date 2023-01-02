package com.latemen.upventure.redux.updater

import com.latemen.upventure.redux.ApplicationState
import javax.inject.Inject

class UiProductInCartUpdater @Inject constructor() {

    fun update(productId: Int, currentState: ApplicationState): ApplicationState {
        val currentProductIdsInCart = currentState.inCartProductIds
        val newProductIdsInCart = if (currentProductIdsInCart.contains(productId)) {
            currentProductIdsInCart - productId
        } else {
            currentProductIdsInCart + productId
        }
        return currentState.copy(inCartProductIds = newProductIdsInCart)
    }
}