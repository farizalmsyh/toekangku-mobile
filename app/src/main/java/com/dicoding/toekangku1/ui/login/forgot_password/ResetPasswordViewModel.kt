package com.dicoding.toekangku1.ui.login.forgot_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.toekangku1.UserRepository
import com.dicoding.toekangku1.response.ForgotPasswordResponse
import com.dicoding.toekangku1.ui.Event

class ResetPasswordViewModel(private val repository: UserRepository): ViewModel() {

    val forgotPasswordResponse: LiveData<ForgotPasswordResponse> = repository.forgotPassword
    val toastText: LiveData<Event<String>> = repository.toastText

}