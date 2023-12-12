package com.dicoding.toekangku1

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.dicoding.toekangku1.data.Experience
import com.dicoding.toekangku1.data.User
import com.dicoding.toekangku1.data.UserPreference
import com.dicoding.toekangku1.data.login
import com.dicoding.toekangku1.response.LoginResponse
import com.dicoding.toekangku1.response.RegisterResponse
import com.dicoding.toekangku1.retrofit.ApiService
import com.dicoding.toekangku1.ui.Event
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {

    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toastText = MutableLiveData<Event<String>>()
    val toastText: LiveData<Event<String>> = _toastText

    fun register(
        tipePengguna: String,
        name: String,
        email: String,
        password: String,
        konfirmasiPassword: String,
        nik: String,
        nomorTelepon: String,
        jenisKelamin: String,
        ulangTahun: String,
        provinsi: String,
        kota: String,
        kecamatan: String,
        kelurahan: String,
        kodePos: String,
        profesi: String,
        tahun_mulai_bekerja: String,
        pengalaman: ArrayList<Experience>
    ) {
        _isLoading.value = true

        // Mengonversi data pengalaman ke MultipartBody.Part
        val experiencesPart = mutableListOf<MultipartBody.Part>()
        for (experience in pengalaman) {
            val experienceRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), experience.toString())
            val experiencePart = MultipartBody.Part.createFormData("pengalaman", experience.toString())
            experiencesPart.add(experiencePart)
        }

        // Membuat bagian-bagian dari data pengguna
        val tipePenggunaPart = createPartFromString(tipePengguna)
        val namePart = createPartFromString(name)
        val emailPart = createPartFromString(email)
        val passwordPart = createPartFromString(password)
        val konfirmasiPasswordPart = createPartFromString(konfirmasiPassword)
        val nikPart = createPartFromString(nik)
        val nomorTeleponPart = createPartFromString(nomorTelepon)
        val jenisKelaminPart = createPartFromString(jenisKelamin)
        val ulangTahunPart = createPartFromString(ulangTahun)
        val provinsiPart = createPartFromString(provinsi)
        val kotaPart = createPartFromString(kota)
        val kecamatanPart = createPartFromString(kecamatan)
        val kelurahanPart = createPartFromString(kelurahan)
        val kodePosPart = createPartFromString(kodePos)
        val profesiPart = createPartFromString(profesi)
        val tahunMulaiBekerjaPart = createPartFromString(tahun_mulai_bekerja)

        // Panggil API dengan menggunakan bagian-bagian yang telah dibuat
        val client = apiService.register(
            tipePenggunaPart,
            namePart,
            emailPart,
            passwordPart,
            konfirmasiPasswordPart,
            nikPart,
            nomorTeleponPart,
            jenisKelaminPart,
            ulangTahunPart,
            provinsiPart,
            kotaPart,
            kecamatanPart,
            kelurahanPart,
            kodePosPart,
            profesiPart,
            tahunMulaiBekerjaPart,
            experiencesPart
        )

        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body() != null) {

                    _registerResponse.value = response.body()
                    _toastText.value = Event(response.body()?.message.toString())
                } else {
                    _toastText.value = Event(response.message().toString())
                    Log.e(
                        TAG,
                        "onFailure: ${response.message()}, ${response.body()?.message.toString()}"
                    )
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _toastText.value = Event(t.message.toString())
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun registerPelanggan(
        tipePengguna: String,
        name: String,
        email: String,
        password: String,
        konfirmasiPassword: String,
        nik: String,
        nomorTelepon: String,
        jenisKelamin: String,
        ulangTahun: String,
        provinsi: String,
        kota: String,
        kecamatan: String,
        kelurahan: String,
        kodePos: String
    ) {
        // Buat bagian-bagian dari data pengguna
        val tipePenggunaPart = createPartFromString(tipePengguna)
        val namePart = createPartFromString(name)
        val emailPart = createPartFromString(email)
        val passwordPart = createPartFromString(password)
        val konfirmasiPasswordPart = createPartFromString(konfirmasiPassword)
        val nikPart = createPartFromString(nik)
        val nomorTeleponPart = createPartFromString(nomorTelepon)
        val jenisKelaminPart = createPartFromString(jenisKelamin)
        val ulangTahunPart = createPartFromString(ulangTahun)
        val provinsiPart = createPartFromString(provinsi)
        val kotaPart = createPartFromString(kota)
        val kecamatanPart = createPartFromString(kecamatan)
        val kelurahanPart = createPartFromString(kelurahan)
        val kodePosPart = createPartFromString(kodePos)

        // Panggil API dengan menggunakan bagian-bagian yang telah dibuat
        val client = apiService.register(
            tipePenggunaPart,
            namePart,
            emailPart,
            passwordPart,
            konfirmasiPasswordPart,
            nikPart,
            nomorTeleponPart,
            jenisKelaminPart,
            ulangTahunPart,
            provinsiPart,
            kotaPart,
            kecamatanPart,
            kelurahanPart,
            kodePosPart,
            createPartFromString(""), // Memberikan nilai kosong untuk profesi
            createPartFromString(""), // Memberikan nilai kosong untuk tahun_mulai_bekerja
            emptyList()  // Memberikan nilai list kosong untuk experiences
        )
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body() != null) {

                    _registerResponse.value = response.body()
                    _toastText.value = Event(response.body()?.message.toString())
                } else {
                    _toastText.value = Event(response.message().toString())
                    Log.e(
                        TAG,
                        "onFailure: ${response.message()}, ${response.body()?.message.toString()}"
                    )
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _toastText.value = Event(t.message.toString())
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

    }

    fun postLogin(email: MultipartBody.Part, password: MultipartBody.Part){
        _isLoading.value = true
        val client = apiService.login(email, password)

        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>
            ) {
                try {
                    _isLoading.value=false
                    if (response.isSuccessful && response.body() != null){
                        _loginResponse.value = response.body()
                        _toastText.value = Event(response.body()?.message.toString())
                        
                    } else {
                        val jsonObject = response.errorBody().toString()?.let { JSONObject(it) }
                        val error = jsonObject?.getBoolean("error")
                        val message = jsonObject?.getString("message")
                        _loginResponse.value = LoginResponse(null, error, message)
                        _toastText.value = Event(
                            "${response.message()} ${response.code()}, $message"

                        )
                        Log.e(
                            "postLogin",
                            "onResponse: ${response.message()}, ${response.code()} $message"
                        )
                    }
                } catch (e: JSONException){
                    _toastText.value = Event(e.message.toString())
                    Log.e("JSONException", "onResponse: ${e.message.toString()}")
                } catch (e: Exception){
                    _toastText.value = Event(e.message.toString())
                    Log.e("Exception", "onResponse: ${e.message.toString()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value=false
                when(t){
                    is UnknownHostException -> {
                        _toastText.value = Event("No Internet Connection")
                        Log.e("UnknownHostException", "onFailure: ${t.message.toString()}")
                    }

                    else -> {
                        _toastText.value = Event(t.message.toString())
                        Log.e("postLogin", "onFailure: ${t.message.toString()}")
                    }
                }
            }

        })
    }

    suspend fun saveSession(login: login) {
        userPreference.saveSession(login)
    }

    fun getSession(): LiveData<User> {
        return userPreference.getSession().asLiveData()
    }

    suspend fun login() {
        userPreference.login()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    private fun createPartFromString(string: String): RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(), string)
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference, apiService)
            }.also { instance = it }
    }

}