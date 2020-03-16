package com.example.lab8.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDataBase(
    @Json(name = "login")
    val login:String,
    @Json(name = "avatar_url")
    val avatar_url: String
)