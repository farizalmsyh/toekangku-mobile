package com.dicoding.toekangku1.response

import com.google.gson.annotations.SerializedName

data class SubmitResetOTPResponse(

	@field:SerializedName("data")
	val data: reset? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class reset(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("secret")
	val secret: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
