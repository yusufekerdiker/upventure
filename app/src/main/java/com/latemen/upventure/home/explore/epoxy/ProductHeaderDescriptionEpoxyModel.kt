package com.latemen.upventure.home.explore.epoxy

import com.latemen.upventure.R
import com.latemen.upventure.databinding.EpoxyModelExploreProductHeaderBinding
import com.latemen.upventure.epoxy.ViewBindingKotlinModel
import com.latemen.upventure.model.ui.UiProduct
import java.text.NumberFormat

data class ProductHeaderDescriptionEpoxyModel(
    val uiProduct: UiProduct
): ViewBindingKotlinModel<EpoxyModelExploreProductHeaderBinding>(
    R.layout.epoxy_model_explore_product_header
) {
    private val currencyFormatter = NumberFormat.getCurrencyInstance()

    override fun EpoxyModelExploreProductHeaderBinding.bind() {
        productTitleTextView.text = uiProduct.product.title
        productDescriptionTextView.text = uiProduct.product.description
        productCategoryTextView.text = uiProduct.product.category
        productPriceTextView.text = currencyFormatter.format(uiProduct.product.price)
    }
}