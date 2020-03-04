package com.example.android.Network.responses

import com.example.android.Db.entities.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message : String?,
    val user: User
)