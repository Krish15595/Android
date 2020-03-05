package com.example.android.Repositories

import com.example.android.Db.AppDatabase
import com.example.android.Db.entities.User
import com.example.android.Network.MyApi
import com.example.android.Network.SafeApiRequest
import com.example.android.Network.responses.AuthResponse


class UserRepository(
    private val myApi: MyApi,
    private val db: AppDatabase
):SafeApiRequest(){
    /* the below commented are used when co-routines are not used*/
//    fun userLogin(email:String, password:String) : LiveData<String> {

   suspend fun userLogin(email : String, password : String) : AuthResponse {
        return apiRequest {   myApi.userLogin(email,password) }


        /*val loginResponse=MutableLiveData<String>()
        mService.userLogin(email, password).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                loginResponse.value=t.message
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful)
                {
                    loginResponse.value=response.body().toString()
                }
                else
                {
                    loginResponse.value=response.errorBody().toString()
                }
            }
        })
        return loginResponse*/
    }
    suspend fun saveUser(user: User)= db.getUserDao().insert(user)
     fun getUser()= db.getUserDao().getUser()

}