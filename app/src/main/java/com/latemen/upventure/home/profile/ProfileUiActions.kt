package com.latemen.upventure.home.profile

import android.util.Log
import com.latemen.upventure.R
import com.latemen.upventure.hilt.auth.AuthViewModel

class ProfileUiActions(private val viewModel: AuthViewModel) {

    fun onSignIn(username: String, password: String) {
        viewModel.login(username, password)
    }

    fun onProfileItemSelected(id: Int) {
        when (id) {
            R.drawable.round_smartphone_24 -> viewModel.sendCallIntent()
            R.drawable.round_my_location_24 -> viewModel.sendLocationIntent()
            R.drawable.round_logout_24 -> viewModel.logout()
            else -> {
                Log.i("SELECTION", "Unknown ID -> $id")
            }
        }
    }
}