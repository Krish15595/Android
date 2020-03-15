package com.example.android.ui.course_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.Adaptor.CourseListAdapter
import com.example.android.Db.AppDatabase
import com.example.android.Db.entities.Course_info
import com.example.android.R
import com.example.android.Repositories.CourseRepository
import com.example.android.databinding.ActivityCourseBinding
import com.example.android.util.Constants
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.activity_dept.*

class CourseActivity : AppCompatActivity(),CourseListenter {
    private lateinit var viewModels: CourseViewModel
    private lateinit var list:ArrayList<Course_info>
    private lateinit var mAdaptor: CourseListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        val mService by lazy { Constants.getApi(this@CourseActivity) }
        list= arrayListOf()

        val layoutManager = LinearLayoutManager(this@CourseActivity,LinearLayoutManager.VERTICAL,false)
        rc_course.layoutManager = layoutManager
        mAdaptor= CourseListAdapter(list)
        rc_course.adapter=mAdaptor
        val db = AppDatabase(this)
        val repository = CourseRepository(mService, db)
        val factory = CourseViewModelFactory(repository)
        initHomeViewModel(factory)
    }
    private fun initHomeViewModel(factory: CourseViewModelFactory) {
        val binding: ActivityCourseBinding = DataBindingUtil.setContentView(this, R.layout.activity_course)
        viewModels = ViewModelProvider(this, factory).get(CourseViewModel::class.java)
        binding.viewModel = viewModels
        viewModels.getCourse().observe(this, Observer {
            // Log.d("data","${dept[dept.size-1].d_id}")
            list= it as ArrayList<Course_info>
            //viewData(list)
            mAdaptor.notifyDataSetChanged()

        })


    }

    override fun onStarted() {

    }

    override fun onSuccess(courseInfo: Course_info) {

    }

    override fun onFailure(message: String) {

    }
}
