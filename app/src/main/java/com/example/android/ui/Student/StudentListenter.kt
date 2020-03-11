package com.example.android.ui.Student

import androidx.lifecycle.LiveData
import com.example.android.Db.entities.Course_info
import com.example.android.Db.entities.Dept_info
import com.example.android.Db.entities.Student_info
import com.example.android.Db.entities.User

interface StudentListenter {
    fun onStarted()
    fun onSuccess(studentInfo: Student_info)
    fun onFailure(message:String)
}