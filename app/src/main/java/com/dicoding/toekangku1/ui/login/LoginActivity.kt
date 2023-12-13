package com.dicoding.toekangku1.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.databinding.ActivityLoginBinding
import com.dicoding.toekangku1.ui.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var factory: ViewModelFactory
    private val loginViewModel: LoginViewModel by viewModels { factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        factory = ViewModelFactory.getInstance(applicationContext)

        setupView()
        setupAction()
        setupViewModel()
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun setupView() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.btnKirimLogin.setOnClickListener {
            val email = binding.emailLoginEditText.text.toString()
            val password = binding.passwordLoginEditText.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                if (email.isEmpty()) {
                    binding.emailLoginEditText.error = getString(R.string.required_field)
                }
                if (password.isEmpty()) {
                    binding.passwordLoginEditText.error = getString(R.string.required_field)
                }
            } else {
                showLoading()
                loginViewModel.postLogin(email, password)
            }
        }

        loginViewModel.loginResponse.observe(this@LoginActivity) { response ->
            if (response != null) {
                if (response.success == true) {
                    moveActivity(response.data?.email.orEmpty(), response.data?.secret.orEmpty())
                }
            } else {
                showToast()
            }
        }
    }

    fun moveActivity(email: String, secret: String) {
        startActivity(Intent(this@LoginActivity, GetOTPActivity::class.java).apply {
            putExtra("email", email)
            putExtra("secret", secret)
        })
        finish()
    }


    private fun showLoading() {
        loginViewModel.isLoading.observe(this@LoginActivity) {
            binding.progressbarLogin.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun showToast() {
        loginViewModel.toastText.observe(this@LoginActivity) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(
                    this@LoginActivity, toastText, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}