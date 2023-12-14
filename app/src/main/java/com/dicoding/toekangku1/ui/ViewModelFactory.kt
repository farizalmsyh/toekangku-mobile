package com.dicoding.toekangku1.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.toekangku1.UserRepository
import com.dicoding.toekangku1.data.Injection
import com.dicoding.toekangku1.ui.login.GetOTPViewModel
import com.dicoding.toekangku1.ui.login.LoginViewModel
import com.dicoding.toekangku1.ui.register.RegisterViewModel
import com.dicoding.toekangku1.ui.register.screen.screenworker.RegisterFirstWorkerActivity

class ViewModelFactory (private val repository: UserRepository): ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }

            modelClass.isAssignableFrom(GetOTPViewModel::class.java) -> {
                GetOTPViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}