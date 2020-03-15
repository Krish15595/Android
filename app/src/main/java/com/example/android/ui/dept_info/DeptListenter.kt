package com.example.android.ui.dept_info

import androidx.lifecycle.LiveData
import com.example.android.Db.entities.Dept_info
import com.example.android.Db.entities.User

interface DeptListenter {
    fun onStarted()
    fun onSuccess(deptInfo: Dept_info)
    fun onFailure(message:String)
}