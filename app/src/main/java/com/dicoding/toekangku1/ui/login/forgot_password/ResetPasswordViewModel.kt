package com.dicoding.toekangku1.ui.login.forgot_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.toekangku1.UserRepository
import com.dicoding.toekangku1.response.ForgotPasswordResponse
import com.dicoding.toekangku1.response.ResetPasswordResponse
import com.dicoding.toekangku1.ui.Event
import kotlinx.coroutines.launch

class ResetPasswordViewModel(private val repository: UserRepository): ViewModel() {
    private val _resetPasswordResult = MutableLiveData<ResetPasswordResponse>()
    val resetPasswordResult: LiveData<ResetPasswordResponse> = _resetPasswordResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String> = _toastText

    fun resetPassword(email: String, secret: String, code: Int, newPassword: String, confirmNewPassword: String) {
        _isLoading.value = true
        repository.resetPassword(email, secret, code, newPassword, confirmNewPassword)

        repository.resetPasswordResponse.observeForever{ response ->
            _resetPasswordResult.value = response
            _isLoading.value = false

            response?.let {
                response?.let {
                    if (it.success == true) {
                        _toastText.value = "Berhasil atur ulang password, silahkan Login kembali!"
                    } else {
                        _toastText.value = it.message ?: "Gagal."
                    }
                } ?: run {
                    _toastText.value = "No response from server."
                }
            }
        }


    }
}