package com.latemen.upventure.hilt.repository

import com.latemen.upventure.hilt.service.ProductsService
import com.latemen.upventure.model.domain.Product
import com.latemen.upventure.model.domain.User
import com.latemen.upventure.model.mapper.ProductMapper
import retrofit2.Response
import retrofit2.http.POST
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val productsService: ProductsService,
    private val productMapper: ProductMapper
) {

    suspend fun fetchAllProducts(): List<Product> {
        // todo better error handling
        return productsService.getAllProducts().body()?.let { networkProducts ->
            networkProducts.map { productMapper.buildFrom(it) }
        } ?: emptyList()
    }

/*    suspend fun pushPost(post: POST): Response<Product> {
        return productsService.postNewProduct(post)
    }*/
}