package com.route.newsc43

import android.app.Application
import com.route.newsc43.data.database.MyDatabase
import com.route.newsc43.utils.ConnectivityImpl
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}