package com.example.android.ui.auth

import androidx.lifecycle.LiveData
import com.example.android.Db.entities.User

interface AuthListenter {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message:String)
}