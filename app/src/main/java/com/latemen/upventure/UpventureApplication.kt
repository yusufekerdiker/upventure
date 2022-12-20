package com.latemen.upventure

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class UpventureApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}