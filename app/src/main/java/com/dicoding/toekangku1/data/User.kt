package com.dicoding.toekangku1.data

data class User(
    val tipePengguna: String,
    val nama: String,
    val email: String,
    val password: String,
    val konfirmasiPassword: String,
    val nik: String,
    val nomorTelpon: String,
    val jenisKelamin: String,
    val ulangTahun: String,
    val provinsi: String,
    val kota: String,
    val kecamatan: String,
    val kelurahan: String,
    val kodePos: String,
    val profesi: String,
    val tahunMulaiBekerja: String,
    val pengalaman: Array<Experience>,
    val token: String
)

data class Experience(
    val name: String,
    val date: String,
    val description: String
)

data class Login(
    val email: String,
    val password: String
)

data class SubmitOTP(
    val email: String,
    val secret: String,
    val code: String,
    val isLogin: Boolean = false
)
