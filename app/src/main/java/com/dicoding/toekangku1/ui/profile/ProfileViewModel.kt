package com.dicoding.toekangku1.ui.profile

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.toekangku1.UserRepository
import com.dicoding.toekangku1.ui.Event

class ProfileViewModel(application: Application, private val repository: UserRepository) : AndroidViewModel(application) {

    val isLoading: LiveData<Boolean> = repository.isLoading
    val toastText: LiveData<Event<String>> = repository.toastText

    fun logout() {
        val sharedPreferences = getApplication<Application>().getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            remove("TOKEN")
            apply()
        }
    }
}