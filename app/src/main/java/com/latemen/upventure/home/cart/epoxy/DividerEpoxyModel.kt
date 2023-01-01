package com.latemen.upventure.home.cart.epoxy

import android.view.ViewGroup
import androidx.annotation.Dimension
import androidx.annotation.Dimension.Companion.PX
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import com.latemen.upventure.R
import com.latemen.upventure.databinding.EpoxyModelDividerBinding
import com.latemen.upventure.epoxy.ViewBindingKotlinModel

data class DividerEpoxyModel(
    @Dimension(unit = PX) private val horizontalMargin: Int = 0,
    @Dimension(unit = PX) private val verticalMargin: Int = 0
) : ViewBindingKotlinModel<EpoxyModelDividerBinding>(R.layout.epoxy_model_divider) {

    override fun EpoxyModelDividerBinding.bind() {
        root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            setMargins(horizontalMargin, verticalMargin, horizontalMargin, verticalMargin)
        }
    }
}