package com.dicoding.toekangku1.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.toekangku1.UserRepository
import com.dicoding.toekangku1.data.User
import com.dicoding.toekangku1.data.login
import com.dicoding.toekangku1.response.LoginResponse
import com.dicoding.toekangku1.ui.Event
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    val loginResponse: LiveData<LoginResponse> = repository.loginResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val toastText: LiveData<Event<String>> = repository.toastText

    fun postLogin(email: MultipartBody.Part, password: MultipartBody.Part) {
        viewModelScope.launch {
            repository.postLogin(email, password)
        }
    }

    fun saveSession(login: login) {
        viewModelScope.launch {
            repository.saveSession(login)
        }
    }

    fun login() {
        viewModelScope.launch {
            repository.login()
        }
    }
}