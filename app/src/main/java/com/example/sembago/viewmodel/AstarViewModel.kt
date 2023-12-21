package com.example.sembago

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sembago.api.ApiConfig
import com.example.sembago.response.AstarRequest
import com.example.sembago.response.AstarResponse
import com.example.sembago.response.MatchingProductsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AstarViewModel : ViewModel() {

    private val _productList = MutableLiveData<List<MatchingProductsItem>>()
    val productList: LiveData<List<MatchingProductsItem>>
        get() = _productList

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData

    fun postAstarRequest(userLat: Double, userLong: Double, category: String, token: String) {
        val apiService = ApiConfig.getApiService(token)

        val request = AstarRequest(userLat, userLong, category)
        apiService.postAstar(request).enqueue(object : Callback<AstarResponse> {
            override fun onResponse(call: Call<AstarResponse>, response: Response<AstarResponse>) {
                if (response.isSuccessful) {
                    val result = processResponse(response.body())

                    _productList.postValue(result.matchingProducts?.filterNotNull() ?: emptyList())
                    _errorLiveData.postValue("")
                } else {
                    _errorLiveData.postValue("Error: ${response.code()} pastikan anda mengisi ketegori dan lokasi dengan benar")

                    Handler().postDelayed({
                        _errorLiveData.postValue("")
                    }, 3000)
                }
            }

            override fun onFailure(call: Call<AstarResponse>, t: Throwable) {
                _errorLiveData.postValue("Error: ${t.message} ")
            }
        })
    }

    private fun processResponse(response: AstarResponse?): AstarResponse {
        return response ?: AstarResponse()
    }
}

