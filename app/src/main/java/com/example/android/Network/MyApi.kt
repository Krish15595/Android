package com.example.android.Network

import com.example.android.Network.responses.AuthResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {
    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @Field("email") email:String,
        @Field("password") password:String
    ) :Response<AuthResponse>
    /* the below commented are used when co-routines are not used*/
    //) :Call<ResponseBody>
}