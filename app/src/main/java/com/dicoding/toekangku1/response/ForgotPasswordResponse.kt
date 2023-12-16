package com.dicoding.toekangku1.response

import com.google.gson.annotations.SerializedName

data class ForgotPasswordResponse(

	@field:SerializedName("data")
	val data: forgotPassword? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class forgotPassword(

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("secret")
	val secret: String? = null
)
