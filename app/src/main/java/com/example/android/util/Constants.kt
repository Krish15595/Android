package com.example.android.util

import android.content.Context
import com.example.android.Helper.RetrofitClient
import com.example.android.Network.MyApi

class Constants {
    companion object {
        const val BASE_URL = "https://api.simplifiedcoding.in/course-apis/mvvm/"

        fun getApi(mContext: Context): MyApi =
            RetrofitClient.getClient(BASE_URL, mContext).create(MyApi::class.java)

    }
}