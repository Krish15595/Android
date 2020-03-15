package com.example.android.Repositories

import com.example.android.Db.AppDatabase
import com.example.android.Db.entities.Course_info
import com.example.android.Db.entities.Dept_info
import com.example.android.Network.MyApi
import com.example.android.Network.SafeApiRequest

class CourseRepository(
    private val myApi: MyApi,
    private val db: AppDatabase
): SafeApiRequest() {

    suspend fun saveUser(courseInfo: Course_info)= db.getCourseDao().insert(courseInfo)

    fun getCourse()= db.getCourseDao().getCourse()
}