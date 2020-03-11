package com.example.android.ui.course_info

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.android.Db.entities.Course_info
import com.example.android.Repositories.CourseRepository
import com.example.android.util.ApiException
import com.example.android.util.Coroutines
import com.example.android.util.NoInternetException
import java.lang.Exception

class CourseViewModel(
    private val courseRepository: CourseRepository
): ViewModel() {
    var et_deptname: String? = null
    var deptListenter: CourseListenter?=null
    fun getCourse() = courseRepository.getCourse()
    fun saveUser(courseInfo: Course_info){
        Coroutines.main {
            courseRepository.saveUser(courseInfo)
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
                val model = Course_info(null, et_deptname)
                courseRepository.saveUser(model)
                deptListenter?.onSuccess(model)
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