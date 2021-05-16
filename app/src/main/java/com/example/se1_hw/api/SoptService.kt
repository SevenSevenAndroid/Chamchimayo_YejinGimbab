package com.example.se1_hw.api

import com.example.se1_hw.data.request.RequestLoginData
import com.example.se1_hw.data.request.RequestSignUpData
import com.example.se1_hw.data.response.ResponseLoginData
import com.example.se1_hw.data.response.ResponseSignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("/login/signin")
    fun postLogin(
        @Body body: RequestLoginData
    ) : Call<ResponseLoginData>
    @POST("/login/signup")
    fun postSignUp(
            @Body body: RequestSignUpData
    ) : Call<ResponseSignUpData>
}