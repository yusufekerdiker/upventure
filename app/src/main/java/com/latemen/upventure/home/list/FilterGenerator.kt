package com.latemen.upventure.home.list

import com.latemen.upventure.model.domain.Filter
import com.latemen.upventure.model.domain.Product
import javax.inject.Inject

class FilterGenerator @Inject constructor() {

    // todo test me!
    fun generateFrom(productsList: List<Product>): Set<Filter> {
        return productsList.groupBy {
            it.category
        }.map { mapEntry ->
            Filter(value = mapEntry.key, displayText = "${mapEntry.key} (${mapEntry.value.size})")
        }.toSet()
    }
}