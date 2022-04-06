package com.example.a4_nretrofit.model

import com.google.gson.annotations.SerializedName

data class BodyRegister(
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password:String,
    @SerializedName("name")
    val name:String
)