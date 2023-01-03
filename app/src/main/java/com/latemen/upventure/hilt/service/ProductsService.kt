package com.latemen.upventure.hilt.service

import com.latemen.upventure.model.domain.Product
import com.latemen.upventure.model.domain.User
import com.latemen.upventure.model.network.NetworkProduct
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductsService {
    @GET("products")
    suspend fun getAllProducts(): Response<List<NetworkProduct>>

/*    @POST("products")
    suspend fun postNewProduct(
        @Body post: POST
    ): Response<Product>*/
}