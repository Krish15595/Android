package com.example.android.ui.home

import androidx.lifecycle.ViewModel
import com.example.android.Repositories.UserRepository
import com.example.android.ui.auth.AuthListenter

class HomeViewModel( private val userRepository: UserRepository
): ViewModel() {
    var authListenter: AuthListenter?=null
    fun getLoggedInUser() = userRepository.getUser()

}