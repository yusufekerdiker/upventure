package com.latemen.upventure.redux.updater

import com.latemen.upventure.redux.ApplicationState
import javax.inject.Inject

class UiProductFavoriteUpdater @Inject constructor() {

    fun update(productId: Int, currentState: ApplicationState): ApplicationState {
        val currentFavoriteIds = currentState.favoriteProductIds
        val newFavoriteIds = if (currentFavoriteIds.contains(productId)) {
            currentFavoriteIds - productId
        } else {
            currentFavoriteIds + productId
        }
        return currentState.copy(favoriteProductIds = newFavoriteIds)
    }
}