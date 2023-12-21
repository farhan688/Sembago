package com.example.sembago.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.sembago.R
import com.example.sembago.adapter.ProductAdapter
import com.example.sembago.api.ApiConfig
import com.example.sembago.databinding.FragmentHomeBinding
import com.example.sembago.response.ProductItem
import com.example.sembago.response.ProductResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view) // Menggunakan FragmentHomeBinding dari view yang dibuat

        adapter = ProductAdapter(this@HomeFragment, arrayListOf())
        binding.rcHome.adapter = adapter
        binding.rcHome.setHasFixedSize(true)

        val sharedPreferences = requireActivity().getSharedPreferences("LoginSession", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", "") ?: ""

        fetchProductData(token)
    }


    private fun fetchProductData(token : String) {
        val apiService = ApiConfig.getApiService(token)

        apiService.getProduct().enqueue(object : Callback<ProductResponse>{
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.product
                    data?.let { setDataToAdapter(it) }
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.d("Error", "" + t.stackTraceToString())

            }
        })
    }

    fun setDataToAdapter(data: List<ProductItem?>){
        adapter.setData(data as ArrayList<ProductItem>)

    }

}

