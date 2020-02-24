package com.example.android.ui.auth

import androidx.lifecycle.LiveData

interface AuthListenter {
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>)
    fun onFailure(message:String)
}