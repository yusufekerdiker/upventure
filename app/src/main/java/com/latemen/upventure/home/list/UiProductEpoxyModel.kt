package com.latemen.upventure.home.list

import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import coil.load
import com.latemen.upventure.R
import com.latemen.upventure.databinding.EpoxyModelProductItemBinding
import com.latemen.upventure.epoxy.ViewBindingKotlinModel
import com.latemen.upventure.model.ui.UiProduct
import java.text.NumberFormat
import kotlin.math.roundToInt

data class UiProductEpoxyModel(
    val uiProduct: UiProduct?,
    val onFavoriteIconClicked: (Int) -> Unit,
    val onUiProductClicked: (Int) -> Unit,
    val onAddToCartClicked: (Int) -> Unit
) : ViewBindingKotlinModel<EpoxyModelProductItemBinding>(R.layout.epoxy_model_product_item) {

    private val currencyFormatter = NumberFormat.getCurrencyInstance()

    override fun EpoxyModelProductItemBinding.bind() {
        // todo
//        swipeToFavoriteTextView.translationX = 0f
        shimmerLayout.isVisible = uiProduct == null
        cardView.isInvisible = uiProduct == null

        uiProduct?.let { uiProduct ->
            shimmerLayout.stopShimmer()

            // Setup our text
            productTitleTextView.text = uiProduct.product.title
            productDescriptionTextView.text = uiProduct.product.description
            productCategoryTextView.text = uiProduct.product.category
            productPriceTextView.text = currencyFormatter.format(uiProduct.product.price)

            // Expanded state
            productDescriptionTextView.isVisible = uiProduct.isExpanded
            root.setOnClickListener { onUiProductClicked(uiProduct.product.id) }

            // Favorite icon
            val imageRes = if (uiProduct.isFavorite) {
                R.drawable.round_favorite_24
            } else {
                R.drawable.round_favorite_border_24
            }
            favoriteImageView.setIconResource(imageRes)
            favoriteImageView.setOnClickListener {
                onFavoriteIconClicked(uiProduct.product.id)
            }

            // In cart status
            inCartView.isVisible = uiProduct.isInCart
            addToCartButton.setOnClickListener {
                onAddToCartClicked(uiProduct.product.id)
            }

            // Load our image
            productImageViewLoadingProgressBar.isVisible = true
            productImageView.load(data = uiProduct.product.image) {
                listener { request, result ->
                    productImageViewLoadingProgressBar.isGone = true
                }
            }
            ratingIndicator.progress = (uiProduct.product.rating.value * 10).roundToInt()
            ratingTextView.text = "${uiProduct.product.rating.value}"
        } ?: shimmerLayout.startShimmer()
    }
}

//            productImageView.resources.getColor(android.R.color.transparent)
//            productImageView.setBackgroundColor(Color.parseColor("#80000000"))
//            productImageView.setBackgroundColor(getResources().getColor(android.R.color.transparent))