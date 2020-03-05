package com.example.android.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.Db.AppDatabase
import com.example.android.Db.entities.User
import com.example.android.R
import com.example.android.Repositories.UserRepository
import com.example.android.databinding.ActivityLoginBinding
import com.example.android.ui.home.HomeActivity
import com.example.android.util.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),AuthListenter {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mService by lazy { Constants.getApi() }
        val db=AppDatabase(this)
        val repository=UserRepository(mService, db)
        val factory=AuthViewModelFactory(repository)

        val binding:ActivityLoginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel=ViewModelProvider(this,factory).get(AuthViewModel::class.java)
        viewModel.email="probelalkhan@gmail.com"
        binding.viewModel=viewModel
        viewModel.authListenter=this
        viewModel.getLoggedInUser()?.observe( this, Observer {user ->
            if(user!=null)
            {
                Intent(this,HomeActivity::class.java).also {
                    it.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(it)
                }
            }
        })
    }
    override fun onStarted() {
        progressBar.visibility=View.VISIBLE
    }

    override fun onSuccess(user: User) {
        progressBar.visibility=View.GONE
        toastSuccess("${user.name} is logged in",false)
        //root_layout.snackbar("${user.name} is logged in")
    }

    override fun onFailure(message: String) {
        progressBar.visibility=View.GONE
        toastError(message,false)
    }
}
