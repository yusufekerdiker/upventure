package com.latemen.upventure.hilt.auth

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latemen.upventure.extensions.capitalize
import com.latemen.upventure.model.domain.Address
import com.latemen.upventure.model.mapper.UserMapper
import com.latemen.upventure.model.network.LoginResponse
import com.latemen.upventure.model.network.NetworkUser
import com.latemen.upventure.redux.ApplicationState
import com.latemen.upventure.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val store: Store<ApplicationState>,
    private val authRepository: AuthRepository,
    private val userMapper: UserMapper
) : ViewModel() {

    private val _intentFlow = MutableStateFlow<Intent?>(null)
    val intentFlow: StateFlow<Intent?> = _intentFlow

    fun ResponseBody?.parseError(): String? {
        return this?.byteStream()?.bufferedReader()?.readLine()?.capitalize()
    }

    fun login(username: String, password: String) = viewModelScope.launch {
        val response: Response<LoginResponse> = authRepository.login(username, password)
        if (response.isSuccessful) {
            val fetchUserResponse: Response<NetworkUser> = authRepository.fetchUser()
            store.update { applicationState ->
                val authState = fetchUserResponse.body()?.let { body ->
                    ApplicationState.AuthState.Authenticated(user = userMapper.buildFrom(body))
                } ?: ApplicationState.AuthState.Unauthenticated(
                    errorString = response.errorBody()?.parseError()
                )

                return@update applicationState.copy(authState = authState)
            }
        } else {
            store.update { applicationState ->
                applicationState.copy(
                    authState = ApplicationState.AuthState.Unauthenticated(
                        errorString = response.errorBody()?.parseError()
                    )
                )
            }
        }
    }

    fun logout() = viewModelScope.launch {
        store.update { applicationState ->
            applicationState.copy(authState = ApplicationState.AuthState.Unauthenticated())
        }
    }

    fun sendCallIntent() = viewModelScope.launch {
        val phoneNumber: String = store.read {
            (it.authState as ApplicationState.AuthState.Authenticated).user.phoneNumber
        }
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        _intentFlow.emit(intent)
    }

    fun sendLocationIntent() = viewModelScope.launch {
        val address: Address = store.read {
            (it.authState as ApplicationState.AuthState.Authenticated).user.address
        }
//        val intentUri = Uri.parse("geo:${address.lat},${address.long}")
        val intentUri = Uri.parse("geo:40.7128,-74.0060?q=${Uri.encode("NYC")}") // NYC lat long
        val mapIntent = Intent(Intent.ACTION_VIEW, intentUri)
        //mapIntent.setPackage("com.google.android.apps.maps")
        _intentFlow.emit(mapIntent)
    }

}