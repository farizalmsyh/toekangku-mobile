package com.dicoding.toekangku1.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.chaos.view.PinView
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.data.SubmitOTP
import com.dicoding.toekangku1.databinding.ActivityGetOtpactivityBinding
import com.dicoding.toekangku1.databinding.ActivityLoginBinding
import com.dicoding.toekangku1.ui.ViewModelFactory

class GetOTPActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetOtpactivityBinding
    private lateinit var factory: ViewModelFactory
    private lateinit var pinView: PinView
    private lateinit var btnKirimOTP: Button
    private val getOtpViewModel: GetOTPViewModel by viewModels { factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pinView = findViewById(R.id.pinview)
        btnKirimOTP = findViewById(R.id.btn_kirim_otp)

        factory = ViewModelFactory.getInstance(applicationContext)

        setupView()
        setupAction()
        setupViewModel()
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun setupView() {
        binding = ActivityGetOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
    }

    private fun setupAction(){
        btnKirimOTP.setOnClickListener {
            val code: String = pinView.text.toString()

            val email = intent.getStringExtra("email")
            val secret = intent.getStringExtra("secret")

            if (email !=null && secret !=null){
                val login = SubmitOTP(email, secret, code)
                getOtpViewModel.saveSession(SubmitOTP(email, secret, code))
            }
        }
    }
}