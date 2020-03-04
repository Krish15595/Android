package com.example.android.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.android.Repositories.UserRepository
import com.example.android.util.Coroutines

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

        Coroutines.main {
            val respose= UserRepository().userLogin(email!!, password!!)
            if(respose.isSuccessful)
            {
                authListenter?.onSuccess(respose.body()?.user!!)
            }
            else
            {
                authListenter?.onFailure(" Error code: ${respose.code()}")
            }
        }
        /* the below commented are used when co-routines are not used*/
//        val loginResponse = UserRepository().userLogin(email!!, password!!)
//        authListenter?.onSuccess(loginResponse)
    }

}