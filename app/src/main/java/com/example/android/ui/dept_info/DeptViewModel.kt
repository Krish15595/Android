package com.example.android.ui.dept_info

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.android.Db.entities.Dept_info
import com.example.android.Repositories.DeptRepository
import com.example.android.ui.auth.AuthListenter
import com.example.android.util.ApiException
import com.example.android.util.Coroutines
import com.example.android.util.NoInternetException
import java.lang.Exception

class DeptViewModel(
    private val deptRepository: DeptRepository
): ViewModel() {
    var et_deptname: String? = null
    var deptListenter: DeptListenter?=null
    fun getDept() = deptRepository.getDept()
    fun saveUser(deptInfo: Dept_info){
        Coroutines.main {
            deptRepository.saveUser(deptInfo)
        }
    }
    fun onAddDeptButtonClicked(view: View) {
        deptListenter?.onStarted()
        if (et_deptname.isNullOrEmpty()) {
            deptListenter?.onFailure("Enter Name to proceed")
            return
        }

        Coroutines.main {
            try {
                val deptModel = Dept_info(null, et_deptname)
                deptRepository.saveUser(deptModel)
                deptListenter?.onSuccess(deptModel)
            }
            catch (e: ApiException) {
                deptListenter?.onFailure(e.message!!)
            }
            catch (e: NoInternetException) {
                deptListenter?.onFailure(e.message.toString())
            }
            catch (e:Exception)
            {
                deptListenter?.onFailure(e.message.toString())
            }
        }
    }
}