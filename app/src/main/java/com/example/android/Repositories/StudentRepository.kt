package com.example.android.Repositories

import com.example.android.Db.AppDatabase
import com.example.android.Db.entities.Student_info
import com.example.android.Network.MyApi
import com.example.android.Network.SafeApiRequest

class StudentRepository(
    private val myApi: MyApi,
    private val db: AppDatabase
): SafeApiRequest() {

    suspend fun saveUser(studentInfo: Student_info)= db.getStudentDao().insert(studentInfo)

    fun getStudentDetails()= db.getStudentDao().getStudentDetails()
}