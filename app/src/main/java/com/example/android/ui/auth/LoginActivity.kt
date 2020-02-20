package com.example.android.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android.R
import com.example.android.databinding.ActivityLoginBinding
import com.example.android.util.toast
import com.example.android.util.toastError
import com.example.android.util.toastInfo
import com.example.android.util.toastSuccess

class LoginActivity : AppCompatActivity(),AuthListenter {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityLoginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel=ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewModel=viewModel
        viewModel.authListenter=this
    }
    override fun onStarted() {
        toastInfo("Login Started",false)
    }

    override fun onSuccess() {
        toastSuccess("Login success",false)
    }

    override fun onFailure(message: String) {
        toastError(message,false)
    }
}
