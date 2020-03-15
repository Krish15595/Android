package com.example.android.ui.course_info

import androidx.lifecycle.LiveData
import com.example.android.Db.entities.Course_info
import com.example.android.Db.entities.Dept_info
import com.example.android.Db.entities.User

interface CourseListenter {
    fun onStarted()
    fun onSuccess(courseInfo: Course_info)
    fun onFailure(message:String)
}