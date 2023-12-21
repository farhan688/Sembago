package com.example.sembago.api

import com.example.sembago.response.AstarRequest
import com.example.sembago.response.AstarResponse
import com.example.sembago.response.LoginResponse
import com.example.sembago.response.LoginResult
import com.example.sembago.response.ProductResponse
import com.example.sembago.response.RegisterRequest
import com.example.sembago.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
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

    @GET("api/allProduct")
    fun getProduct(

    ): Call<ProductResponse>

    @POST("api/nearProduct")
    fun postAstar(
        @Body astarCredential: AstarRequest
    ): Call<AstarResponse>
}