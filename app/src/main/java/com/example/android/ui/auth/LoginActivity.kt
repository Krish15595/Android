package com.example.android.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.Db.entities.User
import com.example.android.Network.responses.AuthResponse
import com.example.android.R
import com.example.android.databinding.ActivityLoginBinding
import com.example.android.util.toastError
import com.example.android.util.toastInfo
import com.example.android.util.toastSuccess
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Response

class LoginActivity : AppCompatActivity(),AuthListenter {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityLoginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel=ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewModel=viewModel
        viewModel.authListenter=this
    }
    override fun onStarted() {
        progressBar.visibility=View.VISIBLE
    }

    override fun onSuccess(user: User) {
        progressBar.visibility=View.GONE
        toastSuccess("${user.name} is logged in",false)
    }

    override fun onFailure(message: String) {
        progressBar.visibility=View.GONE
        toastError(message,false)
    }
}
