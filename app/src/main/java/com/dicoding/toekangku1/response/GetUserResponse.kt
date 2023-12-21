package com.dicoding.toekangku1.response

import com.google.gson.annotations.SerializedName

data class GetUserResponse(

	@field:SerializedName("data")
	val data: userdata? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

data class User(

	@field:SerializedName("location_village")
	val locationVillage: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("picture_url")
	val pictureUrl: Any? = null,

	@field:SerializedName("location_subdistrict")
	val locationSubdistrict: String? = null,

	@field:SerializedName("birth_date")
	val birthDate: String? = null,

	@field:SerializedName("address_village")
	val addressVillage: String? = null,

	@field:SerializedName("start_year")
	val startYear: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("email_verified_at")
	val emailVerifiedAt: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("address_subdistrict")
	val addressSubdistrict: String? = null,

	@field:SerializedName("address_city")
	val addressCity: String? = null,

	@field:SerializedName("address_detail")
	val addressDetail: Any? = null,

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("location_province")
	val locationProvince: String? = null,

	@field:SerializedName("address_province")
	val addressProvince: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("address_zipcode")
	val addressZipcode: String? = null,

	@field:SerializedName("location_city")
	val locationCity: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("profesion")
	val profesion: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class userdata(

	@field:SerializedName("user")
	val user: User? = null
)
