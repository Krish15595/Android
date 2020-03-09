package com.example.android

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.facebook.FacebookSdk

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        FacebookSdk.setApplicationId("2512440482363214")
    }

}