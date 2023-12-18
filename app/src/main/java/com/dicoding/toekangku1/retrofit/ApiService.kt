package com.dicoding.toekangku1.retrofit

import com.dicoding.toekangku1.data.Experience
import com.dicoding.toekangku1.response.ForgotPasswordResponse
import com.dicoding.toekangku1.response.LoginResponse
import com.dicoding.toekangku1.response.RegisterResponse
import com.dicoding.toekangku1.response.ResetPasswordResponse
import com.dicoding.toekangku1.response.SubmitOTPResponse
import com.dicoding.toekangku1.response.SubmitResetOTPResponse
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

    @FormUrlEncoded
    @POST("/v1/mobile/auth/register/")
    fun register(
        @Field("tipe_pengguna") tipePengguna: String,
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") konfirmasiPassword: String,
        @Field("nik") nik: String,
        @Field("notelp") nomorTelepon: String,
        @Field("jenis_kelamin") jenisKelamin: String,
        @Field("tanggal_lahir") ulangTahun: String,
        @Field("provinsi") provinsi: String,
        @Field("kota") kota: String,
        @Field("kecamatan") kecamatan: String,
        @Field("kelurahan") kelurahan: String,
        @Field("kodepos") kodePos: String,
        @Field("profesi") profesi: String,
        @Field("tahun_mulai_bekerja") tahun_mulai_bekerja: String,
        @Field("experiences") pengalaman: Array<Experience>
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("/v1/mobile/auth/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("/v1/mobile/auth/submit-otp/")
    fun submitOTP(
        @Field("email") email: String,
        @Field("secret") secret: String,
        @Field("kode") code: Int
    ): Call<SubmitOTPResponse>

    @FormUrlEncoded
    @POST("/v1/mobile/auth/forgot-password")
    fun forgotPassword(
        @Field("email") email: String
    ) : Call<ForgotPasswordResponse>

    @FormUrlEncoded
    @POST("/v1/mobile/auth/submit-reset-code")
    fun submitReset(
        @Field("kode") code: Int,
        @Field("secret") secret: String,
        @Field("email") email: String
    ) : Call<SubmitResetOTPResponse>

    @FormUrlEncoded
    @POST("/v1/mobile/auth/reset-password")
    fun  resetPassword(
        @Field("email") email: String,
        @Field("secret") secret: String,
        @Field("kode") code: Int,
        @Field("password") password: String,
        @Field("password_confirmation") konfirmasi: String
    ) : Call<ResetPasswordResponse>



    @FormUrlEncoded
    @POST("/auth/experience/create/")
    fun createExperience(
        @Field("name") name: String,
        @Field("tanggal") tanggal: String,
        @Field("deskripsi") deskripsi: String
    )


}