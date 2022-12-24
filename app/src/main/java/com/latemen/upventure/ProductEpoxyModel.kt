package com.latemen.upventure

import androidx.core.view.isGone
import androidx.core.view.isVisible
import coil.load
import com.latemen.upventure.databinding.EpoxyModelProductItemBinding
import com.latemen.upventure.epoxy.ViewBindingKotlinModel
import com.latemen.upventure.model.domain.Product
import java.text.NumberFormat

data class ProductEpoxyModel(
    val product: Product
) : ViewBindingKotlinModel<EpoxyModelProductItemBinding>(R.layout.epoxy_model_product_item) {

    private val currencyFormatter = NumberFormat.getCurrencyInstance()

    override fun EpoxyModelProductItemBinding.bind() {
        // Load text
        productTitleTextView.text = product.title
        productDescriptionTextView.text = product.description
        productCategoryTextView.text = product.category
        productPriceTextView.text = currencyFormatter.format(product.price)

        // Load image
        productImageViewLoadingProgressBar.isVisible = true
        productImageView.load(data = product.image) {
            listener { request, result ->
                productImageViewLoadingProgressBar.isGone = true
            }
        }
    }
}
