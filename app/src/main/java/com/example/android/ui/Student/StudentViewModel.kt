package com.example.android.ui.Student

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.android.Db.entities.Course_info
import com.example.android.Db.entities.Student_info
import com.example.android.Repositories.StudentRepository
import com.example.android.util.ApiException
import com.example.android.util.Coroutines
import com.example.android.util.NoInternetException
import java.lang.Exception

class StudentViewModel(
    private val studentRepository: StudentRepository
): ViewModel() {
    //var et_deptname: String? = null
    var studentListenter: StudentListenter?=null
    fun getStudent() = studentRepository.getStudentDetails()
    fun saveUser(deptInfo: Student_info){
        Coroutines.main {
            studentRepository.saveUser(deptInfo)
        }
    }
    fun onAddDeptButtonClicked(view: View) {
        studentListenter?.onStarted()
     /*   if (et_deptname.isNullOrEmpty()) {
            studentListenter?.onFailure("Enter Name to proceed")
            return
        }*/

        Coroutines.main {
            try {
 /*               val model = Student_info(null, et_deptname)
                studentRepository.saveUser(model)
                studentListenter?.onSuccess(model)*/
            }
            catch (e: ApiException) {
                studentListenter?.onFailure(e.message!!)
            }
            catch (e: NoInternetException) {
                studentListenter?.onFailure(e.message.toString())
            }
            catch (e:Exception)
            {
                studentListenter?.onFailure(e.message.toString())
            }
        }
    }
}