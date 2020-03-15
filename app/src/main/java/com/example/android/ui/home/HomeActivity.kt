package com.example.android.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.Db.AppDatabase
import com.example.android.Db.entities.User
import com.example.android.R
import com.example.android.Repositories.UserRepository
import com.example.android.databinding.ActivityHomeBinding
import com.example.android.databinding.ActivityLoginBinding
import com.example.android.ui.auth.AuthListenter
import com.example.android.ui.course_info.CourseActivity
import com.example.android.ui.dept_info.DeptActivity
import com.example.android.util.Constants
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.content_profile.*


class HomeActivity : AppCompatActivity(), AuthListenter {
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModels: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mService by lazy { Constants.getApi(this@HomeActivity) }
        val db = AppDatabase(this)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val repository = UserRepository(mService, db)
        val factory = HomeViewModelFactory(repository)
        initHomeViewModel(factory)
        btnDepartMent.setOnClickListener {
            Intent(this, DeptActivity::class.java).also {
                startActivity(it)
            }
        }

        btnCourse.setOnClickListener {
            Intent(this, CourseActivity::class.java).also {
                startActivity(it)
            }
        }


    }

    private fun initHomeViewModel(factory: HomeViewModelFactory) {
        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModels = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        binding.viewModel = viewModels
        viewModels.authListenter = this
        viewModels.getLoggedInUser().observe(this, Observer { user ->

                Log.d("name","${user.name}")
                name.setText(user.name)
                //email.text=user.email
        })

    }

    override fun onStarted() {

    }

    override fun onSuccess(user: User) {

    }

    override fun onFailure(message: String) {

    }
}
