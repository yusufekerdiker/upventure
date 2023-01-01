package com.latemen.upventure.home.cart.epoxy

import android.view.View
import com.latemen.upventure.R
import com.latemen.upventure.databinding.EpoxyModelCartEmptyBinding
import com.latemen.upventure.epoxy.ViewBindingKotlinModel

data class CartEmptyEpoxyModel(
    private val onClick: (View) -> Unit
) : ViewBindingKotlinModel<EpoxyModelCartEmptyBinding>(R.layout.epoxy_model_cart_empty) {

    override fun EpoxyModelCartEmptyBinding.bind() {
        button.setOnClickListener(onClick)
    }
}