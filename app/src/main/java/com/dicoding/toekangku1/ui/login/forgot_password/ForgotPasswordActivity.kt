package com.dicoding.toekangku1.ui.login.forgot_password

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.databinding.ActivityForgotPasswordBinding
import com.dicoding.toekangku1.ui.ViewModelFactory

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding
    private val forgotPasswordViewModel: ForgotPasswordViewModel by viewModels { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()

    }

    private fun setupView(){
        supportActionBar?.hide()
    }

    private fun setupAction(){
        binding.btnKirimLupaPassword.setOnClickListener {
            performOTPAuth()
        }
    }

    private fun performOTPAuth(){
        val email = binding.lupaPasswordEditText.text.toString()

        if (validateForgotPasswordForm(email)){
            showLoading(true)
            forgotPasswordViewModel.postForgotPassword(email)
            observeViewModel()
        }
    }

    private fun validateForgotPasswordForm(email: String): Boolean{
        var isValid = true
        if (email.isEmpty()){
            binding.lupaPasswordEditText.error = getString(R.string.required_field)
            isValid = false
        }
        return isValid
    }

    private fun observeViewModel(){
        forgotPasswordViewModel.forgotPasswordResponse.observe(this){response ->
            showLoading(false)
            if (response != null && response.success == true){
                moveActivity(response.data?.email.orEmpty(), response.data?.secret.orEmpty())
            } else{
                showToast()
            }
        }
    }

    private fun moveActivity(email: String, secret: String){
        startActivity(Intent(this, VerifyForgotPassword::class.java).apply {
            putExtra("email", email)
            putExtra("secret", secret)
        })
        finish()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressbarForgot.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast() {
        forgotPasswordViewModel.toastText.observe(this@ForgotPasswordActivity) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(
                    this@ForgotPasswordActivity, toastText, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}