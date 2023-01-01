package com.latemen.upventure.home.cart

import androidx.lifecycle.ViewModel
import com.latemen.upventure.redux.ApplicationState
import com.latemen.upventure.redux.Store
import com.latemen.upventure.redux.reducer.UiProductListReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartFragmentViewModel @Inject constructor(
    val store: Store<ApplicationState>,
    val uiProductListReducer: UiProductListReducer
): ViewModel() {
}