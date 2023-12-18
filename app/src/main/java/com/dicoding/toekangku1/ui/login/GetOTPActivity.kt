package com.dicoding.toekangku1.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.chaos.view.PinView
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.ui.ViewModelFactory
import com.dicoding.toekangku1.ui.home.HomeSeekerActivity

class GetOTPActivity : AppCompatActivity() {

    private lateinit var pinView: PinView
    private lateinit var btnKirimOTP: Button
    private val getOtpViewModel: GetOTPViewModel by viewModels { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_otpactivity)

        pinView = findViewById(R.id.pinview)
        btnKirimOTP = findViewById(R.id.btn_kirim_otp)

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
                getOtpViewModel.postOtp(email, secret, code)
            } else {
                Toast.makeText(this, "Invalid OTP Code", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeViewModel() {
        getOtpViewModel.otpResponse.observe(this) { response ->
            response?.let {
                if (it.success == true) {
                    navigateToHome()
                } else {
                    Toast.makeText(this, "Failed to verify OTP: ${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        getOtpViewModel.toastText.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeSeekerActivity::class.java))
        finish()
    }
}
