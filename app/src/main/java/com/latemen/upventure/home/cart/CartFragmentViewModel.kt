package com.latemen.upventure.home.cart

import androidx.lifecycle.ViewModel
import com.latemen.upventure.redux.ApplicationState
import com.latemen.upventure.redux.Store
import com.latemen.upventure.redux.reducer.UiProductListReducer
import com.latemen.upventure.redux.updater.UiProductFavoriteUpdater
import com.latemen.upventure.redux.updater.UiProductInCartUpdater
import com.latemen.upventure.redux.updater.UiProductQuantityUpdater
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartFragmentViewModel @Inject constructor(
    val store: Store<ApplicationState>,
    val uiProductListReducer: UiProductListReducer,
    val uiProductFavoriteUpdater: UiProductFavoriteUpdater,
    val uiProductInCartUpdater: UiProductInCartUpdater,
    val uiProductQuantityUpdater: UiProductQuantityUpdater
): ViewModel() {
}