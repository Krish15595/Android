package com.example.android.ui.auth

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Base64.DEFAULT
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
import com.example.android.util.ApiException
import com.example.android.util.Constants
import com.example.android.util.toastError
import com.example.android.util.toastSuccess
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import java.security.MessageDigest
import java.util.*

class LoginActivity : AppCompatActivity(), AuthListenter {
    private lateinit var auth: FirebaseAuth
    private lateinit var callbackManager: CallbackManager
    private lateinit var googleSignInClient:GoogleSignInClient
    private lateinit var viewModel: AuthViewModel
    private var RC_SIGN_IN=2
    private var RC_SIGN_IN1=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mService by lazy { Constants.getApi(this@LoginActivity) }
        val db = AppDatabase(this)
        val repository = UserRepository(mService, db)
        val factory = AuthViewModelFactory(repository)
        initAuthViewModel(factory)
        initGoogleSignInClient()


        FacebookSdk.sdkInitialize(this@LoginActivity);
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        buttonGoogleLogin.setOnClickListener { v -> signIn() }

        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create()

        createKeyHash(this@LoginActivity,"com.example.android")
        buttonFacebookLogin.setReadPermissions(listOf("email"))
        buttonFacebookLogin.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d("fbsuccesss", "facebook:onSuccess:$loginResult")
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


    private fun initAuthViewModel(factory: AuthViewModelFactory) {
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
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

    }

    private fun initGoogleSignInClient() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("failed", "Google sign in failed", e)
                // ...
            }
            catch (e:Exception)
            {
                Log.w("failed", "Google sign in failed", e)
            }
        }
        else
        {
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d("account", "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    val emailVerified = user?.isEmailVerified
                    val name = user?.displayName
                    val email = user?.email
                    Log.d("successGoogle", "signInWithCredential:success $emailVerified\n$name\n$email")
                    val users=User(0,name , email,Date().toString(),Date().toString())
                    viewModel.saveUser(users)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("fail", "signInWithCredential:failure", task.exception)
                    Snackbar.make(root_layout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                    //updateUI(null)
                }

                // ...
            }
    }


    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("token", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    val emailVerified = user?.isEmailVerified
                    val name = user?.displayName
                    val email = user?.email
                    Log.d("successfb", "signInWithCredential:success $emailVerified\n$name\n$email")
                    val users=User(0,name , email,Date().toString(),Date().toString())
                    viewModel.saveUser(users)

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

    fun createKeyHash(activity: Activity, yourPackage: String) {
        val info = activity.packageManager.getPackageInfo(yourPackage, PackageManager.GET_SIGNATURES)
        for (signature in info.signatures) {
            val md = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            Log.d("KeyHash:", Base64.encodeToString(md.digest(), DEFAULT))
        }
    }

/*    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("token", "handleFacebookAccessToken:$data")
        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }*/


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


}