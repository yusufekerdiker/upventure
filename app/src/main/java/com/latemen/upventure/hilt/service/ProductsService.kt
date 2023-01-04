package com.latemen.upventure.hilt.service

import com.latemen.upventure.model.domain.Product
import com.latemen.upventure.model.domain.User
import com.latemen.upventure.model.network.NetworkProduct
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductsService {
    @GET("products")
    suspend fun getAllProducts(): Response<List<NetworkProduct>>

/*    @GET("products/")
    suspend fun getCharactersPage(
        @Query("name") characterName: String,
        @Query("page") pageIndex: Int
    ): Response<GetCharactersPageResponse>*/

/*    @POST("products")
    suspend fun pushProduct(@Body product: Product): Response<Product>*/
}