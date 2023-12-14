package com.dicoding.toekangku1.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.toekangku1.UserRepository
import com.dicoding.toekangku1.data.Login
import com.dicoding.toekangku1.data.SubmitOTP
import com.dicoding.toekangku1.response.SubmitOTPResponse
import com.dicoding.toekangku1.ui.Event
import kotlinx.coroutines.launch

class GetOTPViewModel(private val repository: UserRepository): ViewModel() {
    val otpResponse: LiveData<SubmitOTPResponse> = repository.submitOTPResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val toastText: LiveData<Event<String>> = repository.toastText

    fun postOtp(email: String, secret: String, code: String) {
        viewModelScope.launch {
            repository.postOTP(email, secret, code)
        }
    }

    fun saveSession(token: String) {
        viewModelScope.launch {
            repository.saveSession(token)
        }
    }

    fun submitOTP() {
        viewModelScope.launch {
            repository.login()
        }
    }

}