package com.dicoding.toekangku1.retrofit

import com.dicoding.toekangku1.data.Experience
import com.dicoding.toekangku1.response.LoginResponse
import com.dicoding.toekangku1.response.RegisterResponse
import com.dicoding.toekangku1.response.SubmitOTPResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Multipart
    @POST("/auth/register")
    fun register(
        @Part("tipe_pengguna") tipePengguna: RequestBody,
        @Part("nama") nama: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("password_confirmation") konfirmasiPassword: RequestBody,
        @Part("nik") nik: RequestBody,
        @Part("notelp") nomorTelepon: RequestBody,
        @Part("jenis_kelamin") jenisKelamin: RequestBody,
        @Part("tanggal_lahir") ulangTahun: RequestBody,
        @Part("provinsi") provinsi: RequestBody,
        @Part("kota") kota: RequestBody,
        @Part("kecamatan") kecamatan: RequestBody,
        @Part("kelurahan") kelurahan: RequestBody,
        @Part("kodepos") kodePos: RequestBody,
        @Part("profesi") profesi: RequestBody,
        @Part("tahun_mulai_bekerja") tahun_mulai_bekerja: RequestBody,
        @Part experiences: List<MultipartBody.Part>
    ): Call<RegisterResponse>

    @Multipart
    @POST("/auth/login")
    fun login(
        @Part("email") email: MultipartBody.Part,
        @Part("password") password: MultipartBody.Part
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("/auth/submit-otp")
    fun submitOTP(
        @Field("email") email: String,
        @Field("secret") secret: String,
        @Field("code") code: String
    ): Call<SubmitOTPResponse>

    @FormUrlEncoded
    @POST("/auth/experience/create")
    fun createExperience(
        @Field("name") name: String,
        @Field("tanggal") tanggal: String,
        @Field("deskripsi") deskripsi: String
    )


}