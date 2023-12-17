package com.dicoding.toekangku1.ui.login.forgot_password

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.chaos.view.PinView
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.ui.ViewModelFactory

class VerifyForgotPassword : AppCompatActivity() {
    private lateinit var pinView: PinView
    private lateinit var btnKirimOTP: Button
    private val verifyForgotPasswordViewModel: VerifyForgotPasswordViewModel by viewModels { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_forgot_password)

        pinView = findViewById(R.id.pinview_dua)
        btnKirimOTP = findViewById(R.id.btn_otp)

        setupAction()
        observeViewModel()
    }

    private fun setupAction() {
        btnKirimOTP.setOnClickListener {
            val codeString = pinView.text.toString()
            val email = intent.getStringExtra("email") ?: ""
            val secret = intent.getStringExtra("secret") ?: ""

            if (codeString.isNotBlank() && codeString.matches(Regex("\\d+"))) {
                val code = codeString.toInt()
                verifyForgotPasswordViewModel.postResetOtp(code, secret, email)
            } else {
                Toast.makeText(this, "Invalid OTP Code", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeViewModel() {
        verifyForgotPasswordViewModel.submitResetOTPResponse.observe(this) { response ->
            response?.let {
                if (it.success == true) {
                    navigateToNext(response.data?.email.orEmpty(), response.data?.secret.orEmpty(), response.data?.code.orEmpty())
                } else {
                    Toast.makeText(this, "Failed to verify OTP: ${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        verifyForgotPasswordViewModel.toastText.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToNext(email: String, secret: String, code: String) {
        startActivity(Intent(this, ResetPasswordActivity::class.java).apply {
            putExtra("email", email)
            putExtra("secret", secret)
            putExtra("kode", code)
        })
        finish()
    }
}