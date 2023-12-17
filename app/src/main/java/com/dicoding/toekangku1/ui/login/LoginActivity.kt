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
import com.dicoding.toekangku1.ui.login.forgot_password.ForgotPasswordActivity
import com.dicoding.toekangku1.ui.register.PreRegistActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
    }

    private fun setupView() {
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.btnKirimLogin.setOnClickListener {
            performLogin()
        }

        binding.daftardisini.setOnClickListener {
            startActivity(Intent(this, PreRegistActivity::class.java))
        }

        binding.lupaPasswordLogin.setOnClickListener{
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
    }

    private fun performLogin() {
        val email = binding.emailLoginEditText.text.toString()
        val password = binding.passwordLoginEditText.text.toString()

        if (validateLoginForm(email, password)) {
            showLoading(true)
            loginViewModel.postLogin(email, password)
            observeViewModel()
        }
    }

    private fun validateLoginForm(email: String, password: String): Boolean {
        var isValid = true
        if (email.isEmpty()) {
            binding.emailLoginEditText.error = getString(R.string.required_field)
            isValid = false
        }
        if (password.isEmpty()) {
            binding.passwordLoginEditText.error = getString(R.string.required_field)
            isValid = false
        }
        return isValid
    }

    private fun observeViewModel() {
        loginViewModel.loginResponse.observe(this) { response ->
            showLoading(false)
            if (response != null && response.success == true) {
                moveActivity(response.data?.email.orEmpty(), response.data?.secret.orEmpty())
            } else {
                showToast()
            }
        }
    }

    private fun moveActivity(email: String, secret: String) {
        startActivity(Intent(this, GetOTPActivity::class.java).apply {
            putExtra("email", email)
            putExtra("secret", secret)
        })
        finish()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressbarLogin.visibility = if (isLoading) View.VISIBLE else View.GONE
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
