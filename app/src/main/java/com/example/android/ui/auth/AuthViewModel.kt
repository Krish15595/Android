package com.example.android.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel

class AuthViewModel:ViewModel() {
    var email :String?=null
    var password:String?=null
    var authListenter:AuthListenter?=null

    fun onLoginButtonClicked(view:View){
        authListenter?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty())
        {
            authListenter?.onFailure("Enter email and password")
            return
        }
        authListenter?.onSuccess()
    }

}