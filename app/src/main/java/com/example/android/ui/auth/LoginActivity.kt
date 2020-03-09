package com.example.android.ui.auth

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.Db.AppDatabase
import com.example.android.Db.entities.User
import com.example.android.R
import com.example.android.Repositories.UserRepository
import com.example.android.databinding.ActivityLoginBinding
import com.example.android.ui.home.HomeActivity
import com.example.android.util.Constants
import com.example.android.util.toastError
import com.example.android.util.toastSuccess
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import java.security.MessageDigest
import java.util.*

class LoginActivity : AppCompatActivity(), AuthListenter {
    private lateinit var auth: FirebaseAuth
    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mService by lazy { Constants.getApi(this@LoginActivity) }
        val db = AppDatabase(this)
        val repository = UserRepository(mService, db)
        val factory = AuthViewModelFactory(repository)

        FacebookSdk.sdkInitialize(getApplicationContext());


        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        viewModel.email = "probelalkhan@gmail.com"
        binding.viewModel = viewModel
        viewModel.authListenter = this
        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(it)
                }
            }
        })
        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create()


        createKeyHash(this@LoginActivity,"com.example.android")
        buttonFacebookLogin.setReadPermissions(listOf("email"))
        buttonFacebookLogin.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
//                Log.d(TAG, "facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d("cancel", "facebook:onCancel")
                // ...
            }

            override fun onError(error: FacebookException) {
                Log.d("fbexception", "facebook:onError", error)
                // ...
            }
        })

    }

    fun createKeyHash(activity: Activity, yourPackage: String) {
        val info = activity.packageManager.getPackageInfo(yourPackage, PackageManager.GET_SIGNATURES)
        for (signature in info.signatures) {
            val md = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            Log.d("KeyHash:", Base64.encodeToString(md.digest(), DEFAULT))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("token", "handleFacebookAccessToken:$data")
        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }


    override fun onStarted() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onSuccess(user: User) {
        progressBar.visibility = View.GONE
        toastSuccess("${user.name} is logged in", false)
        //root_layout.snackbar("${user.name} is logged in")
    }

    override fun onFailure(message: String) {
        progressBar.visibility = View.GONE
        toastError(message, false)
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("token", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("success", "signInWithCredential:success")
                    val user = auth.currentUser
                    // updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("faliure", "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    //updateUI(null)
                }

                // ...
            }
    }
}