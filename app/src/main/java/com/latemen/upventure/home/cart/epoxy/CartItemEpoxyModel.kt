package com.latemen.upventure.home.cart.epoxy

import android.view.ViewGroup
import androidx.annotation.Dimension
import androidx.core.view.updateLayoutParams
import coil.load
import com.latemen.upventure.R
import com.latemen.upventure.databinding.EpoxyModelCartProductItemBinding
import com.latemen.upventure.epoxy.ViewBindingKotlinModel
import com.latemen.upventure.model.ui.UiProduct
import com.latemen.upventure.model.ui.UiProductInCart
import java.math.BigDecimal
import java.text.NumberFormat

data class CartItemEpoxyModel(
    val uiProductInCart: UiProductInCart,
    @Dimension(unit = Dimension.PX) private val horizontalMargin: Int,
    private val onFavoriteClicked: () -> Unit,
    private val onDeleteClicked: () -> Unit,
    private val onQuantityChanged: (Int) -> Unit
) : ViewBindingKotlinModel<EpoxyModelCartProductItemBinding>(R.layout.epoxy_model_cart_product_item) {

    private val currencyFormatter = NumberFormat.getCurrencyInstance()

    override fun EpoxyModelCartProductItemBinding.bind() {
        swipeToDismissTextView.translationX = 0f
        // Setup our text
        productTitleTextView.text = uiProductInCart.uiProduct.product.title

        // Favorite icon
        val imageRes = if (uiProductInCart.uiProduct.isFavorite) {
            R.drawable.round_favorite_24
        } else {
            R.drawable.round_favorite_border_24
        }
        favoriteImageView.setIconResource(imageRes)
        favoriteImageView.setOnClickListener { onFavoriteClicked() }

        deleteIconImageView.setOnClickListener { onDeleteClicked() }

        // Load our image
        productImageView.load(data = uiProductInCart.uiProduct.product.image)

        root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            setMargins(horizontalMargin, 0, horizontalMargin, 0)
        }

        quantityView.apply {
            quantityTextView.text = uiProductInCart.quantity.toString()
            minusImageView.setOnClickListener { onQuantityChanged(uiProductInCart.quantity - 1) }
            plusImageView.setOnClickListener { onQuantityChanged(uiProductInCart.quantity + 1) }
        }

        val totalPrice = uiProductInCart.uiProduct.product.price * BigDecimal(uiProductInCart.quantity)
        totalProductPriceTextView.text = currencyFormatter.format(totalPrice)
    }
}