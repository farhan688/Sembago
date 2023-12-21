package com.example.sembago.response

import com.google.gson.annotations.SerializedName

data class AstarResponse(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("matchingProducts")
	val matchingProducts: List<MatchingProductsItem?>? = null
)

data class ResultItem(

	@field:SerializedName("goal")
	val goal: String? = null,

	@field:SerializedName("dist_km")
	val distKm: Any? = null
)

data class MatchingProductsItem(

	@field:SerializedName("imageLink")
	val imageLink: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("dist_km")
	val distKm: Any? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
)

data class AstarRequest(
	val user_lat: Double,
	val user_long: Double,
	val category: String
)