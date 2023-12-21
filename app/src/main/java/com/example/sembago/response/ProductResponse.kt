package com.example.sembago.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(

	@field:SerializedName("product")
	val product: List<ProductItem?>? = null
)

data class ProductItem(

	@field:SerializedName("productId")
	val productId: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("sellerName")
	val sellerName: String? = null
)
