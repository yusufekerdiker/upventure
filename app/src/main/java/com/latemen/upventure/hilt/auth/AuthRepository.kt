package com.latemen.upventure.hilt.auth

import com.latemen.upventure.model.network.LoginResponse
import com.latemen.upventure.model.network.NetworkUser
import com.latemen.upventure.model.network.post.LoginPostBody
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authService: AuthService) {
    suspend fun login(username: String, password: String): Response<LoginResponse> {
        return authService.login(LoginPostBody(username, password))
    }
    suspend fun fetchUser(): Response<NetworkUser> {
        return authService.fetchUser(userId = 4)
    }
}