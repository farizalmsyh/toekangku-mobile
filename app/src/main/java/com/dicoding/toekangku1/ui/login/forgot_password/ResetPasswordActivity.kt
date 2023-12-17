package com.dicoding.toekangku1.ui.login.forgot_password

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.databinding.ActivityForgotPasswordBinding
import com.dicoding.toekangku1.databinding.ActivityLoginBinding
import com.dicoding.toekangku1.databinding.ActivityResetPasswordBinding
import com.dicoding.toekangku1.ui.ViewModelFactory
import com.dicoding.toekangku1.ui.login.LoginActivity
import com.dicoding.toekangku1.ui.login.LoginViewModel
import com.google.android.material.textfield.TextInputEditText

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResetPasswordBinding
    private val resetPasswordViewModel: ResetPasswordViewModel by viewModels { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        observeViewModel()
    }

    fun setupView(){
        supportActionBar?.hide()
    }

    fun setupAction(){
        binding.btnKirimLupaPassword.setOnClickListener {
            val email = intent.getStringExtra("email") ?: ""
            val secret = intent.getStringExtra("secret") ?: ""
            val code = intent.getStringExtra("kode") ?: ""
            val password = binding.resetPasswordEditText.text.toString()
            val confirmNewPassword = binding.konfirmasiResetPasswordEditText.text.toString()

            if (password.isNotBlank() && confirmNewPassword.isNotBlank() && password == confirmNewPassword){
                if (code.isNotBlank() && code.matches(Regex("\\d+"))) {
                    val code = code.toInt()
                    // Call ViewModel function to post data
                    resetPasswordViewModel.resetPassword(email, secret, code, password, confirmNewPassword)
                } else {
                    Toast.makeText(this, "Invalid code format", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Password fields are blank or do not match", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeViewModel(){
        resetPasswordViewModel.resetPasswordResult.observe(this){ response ->
            response?.let {
                if (it.success == true) {
                    navigateToLogin()
                }
            }
        }

        resetPasswordViewModel.toastText.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}