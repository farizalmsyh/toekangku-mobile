package com.dicoding.toekangku1.data

import android.content.Context
import com.dicoding.toekangku1.UserRepository
import com.dicoding.toekangku1.data.UserPreference
import com.dicoding.toekangku1.data.dataStore
import com.dicoding.toekangku1.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository{
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(pref, apiService)
    }
}