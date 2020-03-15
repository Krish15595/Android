package com.example.android.Network.responses

import com.example.android.Db.entities.Dept_info
import com.example.android.Db.entities.Student_info

data class StudentResponse(
    val isSuccessful : Boolean?,
    val message : String?,
    val user: Student_info
)