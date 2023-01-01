package com.latemen.upventure.model.mapper

import com.latemen.upventure.model.domain.Product
import com.latemen.upventure.model.network.NetworkProduct
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Locale
import javax.inject.Inject

class ProductMapper @Inject constructor() {

    fun buildFrom(networkProduct: NetworkProduct): Product {

        return Product(
            category = capitalize(networkProduct.category),
            description = networkProduct.description,
            id = networkProduct.id,
            image = networkProduct.image,
            price = BigDecimal(networkProduct.price).setScale(2, RoundingMode.HALF_UP),
            title = networkProduct.title
        )

    }

    private fun capitalize(sequence: String): String {
        return sequence.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
    }

}