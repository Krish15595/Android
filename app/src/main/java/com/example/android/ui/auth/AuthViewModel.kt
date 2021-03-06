package com.example.android.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.android.Db.entities.User
import com.example.android.Repositories.UserRepository
import com.example.android.util.ApiException
import com.example.android.util.Coroutines
import com.example.android.util.NoInternetException

class AuthViewModel(
    private val userRepository: UserRepository
):ViewModel() {
    var email :String?=null
    var password:String?=null
    var authListenter:AuthListenter?=null
    fun getLoggedInUser() = userRepository.getUser()
    fun saveUser(user: User){
        Coroutines.main {
            userRepository.saveUser(user)
        }
    }
    fun onLoginButtonClicked(view:View){
        authListenter?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty())
        {
            authListenter?.onFailure("Enter email and password")
            return
        }

        Coroutines.main {
            try {
                val authRespose= userRepository.userLogin(email!!, password!!)
                authRespose.user.let {
                    authListenter?.onSuccess(it)
                    userRepository.saveUser(it)
                    return@main
                }
                authListenter?.onFailure(authRespose?.message.toString())
            }
            catch (e:ApiException)
            {
                authListenter?.onFailure(e.message!!)
            }
            catch (e:NoInternetException)
            {
                authListenter?.onFailure(e.message.toString())
            }

            //uncomment with coroutines
            /*if(respose.isSuccessful)
            {
                authListenter?.onSuccess(respose.body()?.user!!)
            }
            else
            {
                authListenter?.onFailure(" Error code: ${respose.code()}")
            }*/
        }
        /* the below commented are used when co-routines are not used*/
//        val loginResponse = UserRepository().userLogin(email!!, password!!)
//        authListenter?.onSuccess(loginResponse)
    }


}