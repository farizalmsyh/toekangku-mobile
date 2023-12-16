package com.dicoding.toekangku1.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.chaos.view.PinView
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.data.SubmitOTP
import com.dicoding.toekangku1.databinding.ActivityGetOtpactivityBinding
import com.dicoding.toekangku1.databinding.ActivityLoginBinding
import com.dicoding.toekangku1.ui.ViewModelFactory
import com.dicoding.toekangku1.ui.home.HomeActivity

class GetOTPActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetOtpactivityBinding
    private lateinit var factory: ViewModelFactory
    private lateinit var pinView: PinView
    private lateinit var btnKirimOTP: Button
    private val getOtpViewModel: GetOTPViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
    }

//    private fun setupViewModel() {
//        factory = ViewModelFactory.getInstance(this)
//    }

    private fun setupView() {
//        binding = ActivityGetOtpactivityBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        supportActionBar?.hide()
//        binding.btnKirimOtp.setOnClickListener {
//            performOtpVerification()
//        }
    }

    private fun setupAction() {
        binding.btnKirimOtp.setOnClickListener {
            performOtpVerification()
        }
    }

    private fun performOtpVerification() {
        val code = binding.pinview.text.toString()
        val email = intent.getStringExtra("email") ?: return
        val secret = intent.getStringExtra("secret") ?: return

        if (code.isNotBlank()) {
            getOtpViewModel.postOtp(email, secret, code)
            observeViewModel()
        } else {
            showToast()
        }
    }

    private fun observeViewModel() {
        getOtpViewModel.otpResponse.observe(this) { response ->
            if (response.success == true) {
                response.data?.token?.let { token ->
                    getOtpViewModel.saveSession(token)
                    moveActivity()
                }
            } else {
                showToast()
            }
        }
    }

    private fun moveActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

//    private fun showToast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }

    //    private fun setupAction(){
//        btnKirimOTP.setOnClickListener {
//            val code: String = pinView.text.toString()
//            val email = intent.getStringExtra("email")
//            val secret = intent.getStringExtra("secret")
//
//            if (email.isNullOrBlank() || secret.isNullOrBlank()) {
//
//            } else {
//                getOtpViewModel.postOtp(email!!, secret!!, code)
//                getOtpViewModel.otpResponse.observe(this@GetOTPActivity) { response ->
//                    getOtpViewModel.saveSession(
//                        token = String()
//                    )
//                }
//                showToast()
//                getOtpViewModel.submitOTP()
//                moveActivity()
//            }
//        }
//    }
//
    private fun showToast() {
        getOtpViewModel.toastText.observe(this@GetOTPActivity) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(
                    this@GetOTPActivity, toastText, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
//
//    private fun moveActivity() {
//        getOtpViewModel.otpResponse.observe(this@GetOTPActivity) { response ->
//            if (response.success == true) {
//                startActivity(Intent(this@GetOTPActivity, HomeActivity::class.java))
//                finish()
//            }
//        }
//    }
}