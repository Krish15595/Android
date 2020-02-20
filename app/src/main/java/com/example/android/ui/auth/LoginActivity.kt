package com.example.android.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.android.R
import com.example.android.databinding.ActivityLoginBinding
import com.example.android.util.toast

class LoginActivity : AppCompatActivity(),AuthListenter {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityLoginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel=ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewModel=viewModel
        viewModel.authListenter=this
    }
    override fun onStarted() {
        toast("Login Started")
    }

    override fun onSuccess() {
        toast("Login success")
    }

    override fun onFailure(message: String) {
        toast(message)
    }
}
