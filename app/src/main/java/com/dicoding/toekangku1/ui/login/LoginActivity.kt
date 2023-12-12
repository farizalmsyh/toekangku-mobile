package com.dicoding.toekangku1.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.data.User
import com.dicoding.toekangku1.data.login
import com.dicoding.toekangku1.databinding.ActivityLoginBinding
import com.dicoding.toekangku1.databinding.ActivityRegistFirstSeekerBinding
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

    private fun setupAction(){
        binding.btnKirimLogin.setOnClickListener {
            val email = binding.emailLoginEditText
            val password = binding.passwordLoginEditText
            if (email.length() == 0 && password.length() == 0) {
                email.error = getString(R.string.required_field)
                password.setError(getString(R.string.required_field), null)
            } else if (email.length() != 0 && password.length() != 0) {
                showLoading()
                loginViewModel.postLogin(email, password)
                loginViewModel.loginResponse.observe(this@LoginActivity) { response ->
                    loginViewModel.saveSession(
                        login = login(
                            response.data?.email.toString(),
                            AUTH_KEY + response.Data?.secret.toString(),
                            true,
                            true,
                            true
                        )
                    )
                }
                showToast()
                loginViewModel.login()
                moveActivity()
            }
        }
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

    companion object{
        private const val AUTH_KEY = "Bearer + "
    }
}