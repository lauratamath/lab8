package com.example.lab8.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl("https://api.github.com")
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface GitApi{
    @GET("/users/{username}")
    fun getUser(@Path("username") username: String?): Call<UserDataBase>

    @GET("/users/{username}/repos")
    fun getRepositories(@Path("username") username: String?): Call<List<RepositoryDataBase>>
}

object GitsApi {
    val retrofitService : GitApi by lazy {
        retrofit.create(GitApi::class.java) }
}
