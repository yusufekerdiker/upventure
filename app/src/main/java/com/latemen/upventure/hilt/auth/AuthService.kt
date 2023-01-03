package com.latemen.upventure.hilt.auth

import com.latemen.upventure.model.network.LoginResponse
import com.latemen.upventure.model.network.NetworkUser
import com.latemen.upventure.model.network.post.LoginPostBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {

    @POST("auth/login")
    suspend fun login(
        @Body postBody: LoginPostBody
    ): Response<LoginResponse>

    @GET("users/{user-id}")
    suspend fun fetchUser(
        @Path("user-id") userId: Int
    ): Response<NetworkUser>
}