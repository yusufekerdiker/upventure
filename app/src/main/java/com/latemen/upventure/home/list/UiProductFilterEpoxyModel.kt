package com.latemen.upventure.home.list

import androidx.core.content.ContextCompat
import com.latemen.upventure.R
import com.latemen.upventure.databinding.EpoxyModelProductFilterBinding
import com.latemen.upventure.epoxy.ViewBindingKotlinModel
import com.latemen.upventure.model.domain.Filter
import com.latemen.upventure.model.ui.UiFilter

data class UiProductFilterEpoxyModel(
    val uiFilter: UiFilter,
    val onFilterSelected: (Filter) -> Unit
) : ViewBindingKotlinModel<EpoxyModelProductFilterBinding>(R.layout.epoxy_model_product_filter) {

    override fun EpoxyModelProductFilterBinding.bind() {
        root.setOnClickListener { onFilterSelected(uiFilter.filter) }
        filterNameTextView.text = uiFilter.filter.displayText

        val cardBackgroundColorResId = if (uiFilter.isSelected) {
            com.google.android.material.R.color.material_dynamic_primary50
        } else {
            com.google.android.material.R.color.material_dynamic_primary70
        }
        root.setCardBackgroundColor(ContextCompat.getColor(root.context, cardBackgroundColorResId))
    }
}