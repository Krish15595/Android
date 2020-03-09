package com.example.android.Helper;


import android.content.Context;

import com.example.android.Network.NetworkConnectionInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit mRetrofit = null;
    private static Retrofit mRetrofitInstance = null;




    private static OkHttpClient getOkHttpClient(Context mContext) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS);
        return httpClient.addInterceptor(new NetworkConnectionInterceptor(mContext)).build();
    }

    private static Gson gson = new GsonBuilder()
            .serializeNulls()
            .setLenient()
            .create();

    public static Retrofit getClient(String base_url,Context mContext) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .client(getOkHttpClient(mContext))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return mRetrofit;
    }


}
