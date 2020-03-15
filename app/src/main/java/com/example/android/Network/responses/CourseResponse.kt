package com.example.android.Network.responses

import com.example.android.Db.entities.Course_info
import com.example.android.Db.entities.User

data class CourseResponse(
    val isSuccessful : Boolean?,
    val message : String?,
    val user: Course_info
)