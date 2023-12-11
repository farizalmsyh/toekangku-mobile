package com.dicoding.toekangku1.response

import com.google.gson.annotations.SerializedName

data class SubmitOTPResponse(

	@field:SerializedName("data")
	val data: loginResult? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class loginResult(

	@field:SerializedName("token")
	val token: String? = null
)
