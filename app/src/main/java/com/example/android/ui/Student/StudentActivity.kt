package com.example.android.ui.Student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.Db.AppDatabase
import com.example.android.Db.entities.Course_info
import com.example.android.R
import com.example.android.Repositories.StudentRepository
import com.example.android.databinding.ActivityStudentBinding
import com.example.android.util.Constants
import kotlinx.android.synthetic.main.activity_dept.*

class StudentActivity : AppCompatActivity() {
    private lateinit var viewModels: StudentViewModel
    private lateinit var list:ArrayList<Course_info>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        val mService by lazy { Constants.getApi(this@StudentActivity) }
        list= arrayListOf()

        val layoutManager = LinearLayoutManager(this@StudentActivity)
        rc_dept.layoutManager = layoutManager
        /* mAdaptor= DepListAdaptor(deptList )
         rc_dept.adapter=mAdaptor*/
        val db = AppDatabase(this)
        val repository = StudentRepository(mService, db)
        val factory = StudentViewModelFactory(repository)
     //   initHomeViewModel(factory)
    }
  /*  private fun initHomeViewModel(factory: StudentViewModelFactory) {
        val binding: ActivityStudentBinding = DataBindingUtil.setContentView(this, R.layout.activity_student)
        viewModels = ViewModelProvider(this, factory).get(StudentViewModel::class.java)
        binding.viewModel = viewModels
        viewModels.getStudent().observe(this, Observer {
            // Log.d("data","${dept[dept.size-1].d_id}")
            list= it as ArrayList<Course_info>
            //viewData(list)

        })


    }*/

}
