package com.dicoding.toekangku1.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.toekangku1.UserRepository
import com.dicoding.toekangku1.data.Injection
import com.dicoding.toekangku1.ui.home.HomeViewModel
import com.dicoding.toekangku1.ui.login.GetOTPViewModel
import com.dicoding.toekangku1.ui.login.LoginViewModel
import com.dicoding.toekangku1.ui.login.forgot_password.VerifyForgotPasswordViewModel
import com.dicoding.toekangku1.ui.login.forgot_password.ForgotPasswordViewModel
import com.dicoding.toekangku1.ui.login.forgot_password.ResetPasswordViewModel
import com.dicoding.toekangku1.ui.register.RegisterViewModel

class ViewModelFactory (private val repository: UserRepository, private val context: Context): ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository, context) as T
            }

            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }

            modelClass.isAssignableFrom(GetOTPViewModel::class.java) -> {
                GetOTPViewModel(repository, context) as T
            }

            modelClass.isAssignableFrom(ForgotPasswordViewModel::class.java) -> {
                ForgotPasswordViewModel(repository) as T
            }

            modelClass.isAssignableFrom(VerifyForgotPasswordViewModel::class.java) -> {
                VerifyForgotPasswordViewModel(repository, context) as T
            }

            modelClass.isAssignableFrom(ResetPasswordViewModel::class.java) -> {
                ResetPasswordViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(Injection.provideRepository(context), context).also {
                    INSTANCE = it
                }
            }
        }
    }
}