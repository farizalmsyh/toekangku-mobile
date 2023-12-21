package com.dicoding.toekangku1.response.main

import com.google.gson.annotations.SerializedName

data class GetThreadSeekerResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class Data(

	@field:SerializedName("offset")
	val offset: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("limit")
	val limit: String? = null,

	@field:SerializedName("threads")
	val threads: List<Any?>? = null
)
