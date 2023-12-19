package com.example.sembago.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("loginResult")
	val loginResult: LoginResult? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class LoginResult(

	@field:SerializedName("email")
	val name: String? = null,

	@field:SerializedName("password")
	val userId: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
