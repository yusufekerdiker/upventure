package com.latemen.upventure.model.ui

import com.latemen.upventure.model.domain.Filter

data class UiFilter(
    val filter: Filter,
    val isSelected: Boolean
)