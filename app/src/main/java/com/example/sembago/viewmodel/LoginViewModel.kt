package com.example.sembago.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sembago.api.ApiConfig
import com.example.sembago.response.LoginResponse
import com.example.sembago.response.LoginResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _token = MutableLiveData<String>()
    val token: LiveData<String> = _token

    fun loginUser(email: String, password: String) {
        val logCredential = LoginResult(email, password)
        _isLoading.value = true
        val call = ApiConfig.getApiService("").login(logCredential)
        call.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error!!) {
                        val token = responseBody.loginResult?.token
                        Log.d(TAG, "Token: $token")
                        _isSuccess.value = true
                        _token.value = token.toString()
                    }
                } else {
                    Log.e(TAG, "onResponse: ${response.body()?.message ?: "Login failed"}")
                    _isSuccess.value = false
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun isEmailValid(email: String): Boolean {
        val emailPattern1 = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
        return email.matches(emailPattern1)
    }

    companion object {
        private const val TAG = "SignupActivity"
    }
}