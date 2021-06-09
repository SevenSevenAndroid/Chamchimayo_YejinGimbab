package com.example.se1_hw.data.request

import com.google.gson.annotations.SerializedName

data class RequestLoginData (
    @SerializedName("email")
    val id: String,
    val password: String
)