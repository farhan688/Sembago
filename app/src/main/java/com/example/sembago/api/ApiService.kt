package com.example.sembago.api

import com.example.sembago.response.LoginResponse
import com.example.sembago.response.LoginResult
import com.example.sembago.response.RegisterRequest
import com.example.sembago.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("api/login")
    fun login(
        @Body logCredential : LoginResult
    ): Call<LoginResponse>

    @POST("api/register")
    fun register(
        @Body regCredential : RegisterRequest
    ): Call<RegisterResponse>
}