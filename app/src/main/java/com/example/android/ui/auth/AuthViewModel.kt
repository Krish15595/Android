package com.example.android.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.android.Repositories.UserRepository

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

        val loginResponse = UserRepository().userLogin(email!!, password!!)
        authListenter?.onSuccess(loginResponse)
    }

}