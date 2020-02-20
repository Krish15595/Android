package com.example.android.ui.auth

interface AuthListenter {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message:String)
}