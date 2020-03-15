package com.example.android.Network.responses

import com.example.android.Db.entities.Dept_info

data class DeptResponse(
    val isSuccessful : Boolean?,
    val message : String?,
    val user: Dept_info
)