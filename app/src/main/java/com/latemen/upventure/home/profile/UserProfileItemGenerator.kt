package com.latemen.upventure.home.profile

import androidx.annotation.DrawableRes
import com.latemen.upventure.R
import com.latemen.upventure.model.domain.User
import javax.inject.Inject

class UserProfileItemGenerator @Inject constructor() {

    data class UserProfileUiItem(
        @DrawableRes val iconRes: Int,
        val headerText: String,
        val infoText: String
    )

    fun buildItems(user: User): List<UserProfileUiItem> {
        return buildList {
            add(
                UserProfileUiItem(
                    iconRes = R.drawable.round_account_box_24,
                    headerText = "Username",
                    infoText = user.username
                )
            )
            add(
                UserProfileUiItem(
                    iconRes = R.drawable.round_smartphone_24,
                    headerText = "Phone number",
                    infoText = user.phoneNumber
                )
            )
            add(
                UserProfileUiItem(
                    iconRes = R.drawable.round_my_location_24,
                    headerText = "Location",
                    infoText = "${user.address.street}, ${user.address.city}, ${user.address.zipcode}"
                )
            )
        }
    }
}