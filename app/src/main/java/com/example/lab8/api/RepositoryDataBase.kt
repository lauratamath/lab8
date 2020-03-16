package com.example.lab8.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepositoryDataBase(
    @Json(name = "name")
    val name:String,
    @Json(name = "repos_url")
    val repos_url:String
)