package com.dicoding.toekangku1.ui.login.forgot_password

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.toekangku1.UserRepository
import com.dicoding.toekangku1.response.SubmitResetOTPResponse

class VerifyForgotPasswordViewModel(
    private val repository: UserRepository,
    private val context: Context
): ViewModel() {

    private val _submitResetOTPResponse = MutableLiveData<SubmitResetOTPResponse>()
    val submitResetOTPResponse: LiveData<SubmitResetOTPResponse> = _submitResetOTPResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String> = _toastText

    fun postResetOtp(code: Int, secret: String, email: String) {
        _isLoading.value = true
        repository.postResetOTP(code, secret, email)

        repository.submitResetOTPResponse.observeForever { response ->
            _submitResetOTPResponse.value = response
            _isLoading.value = false
            response?.let {
                if (it.success == true) {
                    _toastText.value = "Reset OTP sent successfully."
                } else {
                    _toastText.value = it.message ?: "Failed to send reset OTP."
                }
            } ?: run {
                _toastText.value = "No response from server."
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
