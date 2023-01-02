package com.latemen.upventure.home.cart.epoxy

import android.view.ViewGroup
import androidx.annotation.Dimension
import androidx.core.view.updateLayoutParams
import coil.load
import com.airbnb.epoxy.TypedEpoxyController
import com.latemen.upventure.R
import com.latemen.upventure.databinding.EpoxyModelCartProductItemBinding
import com.latemen.upventure.epoxy.VerticalSpaceEpoxyModel
import com.latemen.upventure.epoxy.ViewBindingKotlinModel
import com.latemen.upventure.extensions.toPx
import com.latemen.upventure.home.cart.CartFragment
import com.latemen.upventure.model.ui.UiProduct

class CartFragmentEpoxyController : TypedEpoxyController<CartFragment.UiState>() {

    override fun buildModels(data: CartFragment.UiState?) {
        when (data) {
            null, is CartFragment.UiState.Empty -> {
                CartEmptyEpoxyModel(onClick = {
                    // todo
                }).id("empty_state").addTo(this)
            }
            is CartFragment.UiState.NonEmpty -> {
                data.products.forEachIndexed { index, uiProduct ->
                    addVerticalStyling(index)
                    CartItemEpoxyModel(
                        uiProduct = uiProduct,
                        horizontalMargin = 16.toPx(),
                        onFavoriteClicked = {
                            // todo
                        },
                        onDeleteClicked = {
                            // todo
                        }
                    ).id(uiProduct.product.id).addTo(this)
                }
            }
        }
    }

    private fun addVerticalStyling(index: Int) {
        VerticalSpaceEpoxyModel(8.toPx()).id("top_space_$index").addTo(this)

        if (index != 0) {
            DividerEpoxyModel(horizontalMargin = 16.toPx()).id("divider_$index").addTo(this)
        }

        VerticalSpaceEpoxyModel(8.toPx()).id("bottom_space_$index").addTo(this)
    }

    data class CartItemEpoxyModel(
        private val uiProduct: UiProduct,
        @Dimension(unit = Dimension.PX) private val horizontalMargin: Int,
        private val onFavoriteClicked: () -> Unit,
        private val onDeleteClicked: () -> Unit
    ) : ViewBindingKotlinModel<EpoxyModelCartProductItemBinding>(R.layout.epoxy_model_cart_product_item) {

        override fun EpoxyModelCartProductItemBinding.bind() {
            // Setup our text
            productTitleTextView.text = uiProduct.product.title

            // Favorite icon
            val imageRes = if (uiProduct.isFavorite) {
                R.drawable.round_favorite_24
            } else {
                R.drawable.round_favorite_border_24
            }
            favoriteImageView.setIconResource(imageRes)
            favoriteImageView.setOnClickListener { onFavoriteClicked() }

            deleteIconImageView.setOnClickListener { onDeleteClicked() }

            // Load our image
            productImageView.load(data = uiProduct.product.image)

            root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                setMargins(horizontalMargin, 0, horizontalMargin, 0)
            }

            quantityView.apply {
                quantityTextView.text = 9.toString()
                minusImageView.setOnClickListener {  }
                plusImageView.setOnClickListener {  }
            }
        }
    }
}