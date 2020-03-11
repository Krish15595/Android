package com.example.android.Repositories

import com.example.android.Db.AppDatabase
import com.example.android.Db.entities.Dept_info
import com.example.android.Network.MyApi
import com.example.android.Network.SafeApiRequest

class DeptRepository(
    private val myApi: MyApi,
    private val db: AppDatabase
): SafeApiRequest() {

    suspend fun saveUser(deptInfo: Dept_info)= db.getDeptDao().insert(deptInfo)

    fun getDept()= db.getDeptDao().getDept()
}