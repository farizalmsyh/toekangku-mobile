package com.dicoding.toekangku1.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.toekangku1.UserRepository
import com.dicoding.toekangku1.response.SubmitOTPResponse
import com.dicoding.toekangku1.response.SubmitResetOTPResponse

class VerifyForgotPasswordViewModel(
    private val repository: UserRepository,
    private val context: Context
): ViewModel() {

    private val _otpResponse = MutableLiveData<SubmitResetOTPResponse>()
    val otpResponse: LiveData<SubmitResetOTPResponse> = _otpResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String> = _toastText

    fun postOtp(email: String, secret: String, code: Int) {
        _isLoading.value = true
        repository.postOTP(email, secret, code)

        repository.submitOTPResponse.observeForever { response ->
            _isLoading.value = false
            response?.let {
                if (it.success == true) {
                    it.data?.token?.let { token ->
                        _toastText.value = "OTP Verified Successfully"
                    } ?: run {
                        _toastText.value = "Token is null"
                    }
                } else {
                    _toastText.value = it.message ?: "Failed to verify OTP"
                }
            } ?: run {
                _toastText.value = "No response from server"
            }
        }
    }

//    private fun saveSession(token: String) {
//        val sharedPreferences = context.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
//        with(sharedPreferences.edit()) {
//            putString("TOKEN", token)
//            apply()
//        }
//    }
}
