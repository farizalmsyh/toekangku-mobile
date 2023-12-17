package com.dicoding.toekangku1

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.dicoding.toekangku1.data.Experience
import com.dicoding.toekangku1.data.Login
import com.dicoding.toekangku1.data.SubmitOTP
import com.dicoding.toekangku1.data.User
import com.dicoding.toekangku1.data.UserPreference
import com.dicoding.toekangku1.response.ForgotPasswordResponse
import com.dicoding.toekangku1.response.LoginResponse
import com.dicoding.toekangku1.response.RegisterResponse
import com.dicoding.toekangku1.response.SubmitOTPResponse
import com.dicoding.toekangku1.retrofit.ApiService
import com.dicoding.toekangku1.ui.Event
import okhttp3.MultipartBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {

    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private val _submitOTPResponse = MutableLiveData<SubmitOTPResponse>()
    val submitOTPResponse: LiveData<SubmitOTPResponse> = _submitOTPResponse

    private val _forgotPassword = MutableLiveData<ForgotPasswordResponse>()
    val forgotPassword: LiveData<ForgotPasswordResponse> = _forgotPassword

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
        pengalaman: Array<Experience>
    ) {
        _isLoading.value = true
        // Panggil API dengan menggunakan bagian-bagian yang telah dibuat
        val client = apiService.register(
            tipePengguna,
            name,
            email,
            password,
            konfirmasiPassword,
            nik,
            nomorTelepon,
            jenisKelamin,
            ulangTahun,
            provinsi,
            kota,
            kecamatan,
            kelurahan,
            kodePos,
            profesi,
            tahun_mulai_bekerja,
            pengalaman
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
        // Panggil API dengan menggunakan bagian-bagian yang telah dibuat
        val client = apiService.register(
            tipePengguna,
            name,
            email,
            password,
            konfirmasiPassword,
            nik,
            nomorTelepon,
            jenisKelamin,
            ulangTahun,
            provinsi,
            kota,
            kecamatan,
            kelurahan,
            kodePos,
            (""), // Memberikan nilai kosong untuk profesi
            (""), // Memberikan nilai kosong untuk tahun_mulai_bekerja
            emptyArray()  // Memberikan nilai list kosong untuk experiences
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

    fun postLogin(email: String, password: String){
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

    fun postOTP(email: String, secret: String, code: Int) {
        _isLoading.value = true
        val client = apiService.submitOTP(email, secret, code)

        client.enqueue(object : Callback<SubmitOTPResponse>{
            override fun onResponse(
                call: Call<SubmitOTPResponse>,
                response: Response<SubmitOTPResponse>
            ) {
                try {
                    _isLoading.value=false
                    if (response.isSuccessful && response.body() != null){
                        _submitOTPResponse.value = response.body()
                        _toastText.value = Event(response.body()?.message.toString())

                    } else {
                        val jsonObject = response.errorBody().toString()?.let { JSONObject(it) }
                        val error = jsonObject?.getBoolean("error")
                        val message = jsonObject?.getString("message")
                        _submitOTPResponse.value = SubmitOTPResponse(null, error, message)
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

            override fun onFailure(call: Call<SubmitOTPResponse>, t: Throwable) {
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

    fun postForgotPassword(email: String){
        _isLoading.value = true
        val client = apiService.forgotPassword(email)

        client.enqueue(object : Callback<ForgotPasswordResponse>{
            override fun onResponse(
                call: Call<ForgotPasswordResponse>,
                response: Response<ForgotPasswordResponse>
            ) {
                try {
                    _isLoading.value=false
                    if (response.isSuccessful && response.body() != null){
                        _forgotPassword.value = response.body()
                        _toastText.value = Event(response.body()?.message.toString())

                    } else {
                        val jsonObject = response.errorBody().toString()?.let { JSONObject(it) }
                        val error = jsonObject?.getBoolean("error")
                        val message = jsonObject?.getString("message")
                        _forgotPassword.value = ForgotPasswordResponse(null, error, message)
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

            override fun onFailure(call: Call<ForgotPasswordResponse>, t: Throwable) {
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

//    suspend fun saveSession(user: User) {
//        userPreference.saveSession(user)
//    }
//
//    fun getSession(): LiveData<User> {
//        return userPreference.getSession().asLiveData()
//    }

//    suspend fun saveSessionLogin(submit: SubmitOTP){
//        userPreference.saveSessionLogin(submit)
//    }
//
//    fun getSessionLogin(login: Login): LiveData<Login> {
//        return userPreference.getSessionLogin().asLiveData()
//    }

    suspend fun saveSession(token: String){
        userPreference.saveToken(token)
    }

    fun getSession(token: String): LiveData<String?> {
        return userPreference.getToken().asLiveData()

    }

    suspend fun login() {
        userPreference.login()
    }

    suspend fun logout() {
        userPreference.logout()
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