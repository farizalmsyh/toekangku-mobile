package com.dicoding.toekangku1.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.toekangku1.UserRepository

class HomeViewModel (
    private val repository: UserRepository,
    private val context: Context
) : ViewModel() {

    private val _sessionToken = MutableLiveData<String?>()
    val sessionToken: LiveData<String?>
        get() = _sessionToken

    fun loadSessionToken() {
        val sharedPreferences = context.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
        _sessionToken.value = sharedPreferences.getString("TOKEN", null)
    }
}