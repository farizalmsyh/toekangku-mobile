package com.dicoding.toekangku1.ui.register.screen.screenseeker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.databinding.ActivityRegistFirstSeekerBinding
import com.dicoding.toekangku1.ui.ViewModelFactory
import com.dicoding.toekangku1.ui.login.LoginActivity
import com.dicoding.toekangku1.ui.register.RegisterViewModel

class RegistFirstSeekerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistFirstSeekerBinding
    private lateinit var factory: ViewModelFactory
    private val registerViewModel: RegisterViewModel by viewModels { factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistFirstSeekerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        setupViewModel()

    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun setupView() {
        binding = ActivityRegistFirstSeekerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
    }

    private fun setupAction(){
        binding.apply {

            btnSimpanRegistPelanggan.setOnClickListener {
                if (
                    namePelangganEditText.length() == 0 &&
                    nikPelangganEditText.length() == 0 &&
                    jenisKelaminPelangganEditText.length() == 0 &&
                    emailPelangganEditText.length() == 0 &&
                    passwordPelangganEditText.length() == 0 &&
                    passwordConfirmPelangganEditText.length() == 0 &&
                    teleponPelangganEditText.length() == 0 &&
                    tanggalLahirPelangganEditText.length() == 0 &&
                    provinsiPelangganEditText.length() == 0 &&
                    kotaPelangganEditText.length() == 0 &&
                    kecamatanPelangganEditText.length() == 0 &&
                    kelurahanPelangganEditText.length() == 0 &&
                    kodePosPelangganEditText.length() == 0
                ) {
                    namePelangganEditText.error = getString(R.string.required_field)
                    nikPelangganEditText.error =  getString(R.string.required_field)
                    jenisKelaminPelangganEditText.error =  getString(R.string.required_field)
                    emailPelangganEditText.error = getString(R.string.required_field)
                    passwordPelangganEditText.error = getString(R.string.required_field)
                    passwordConfirmPelangganEditText.error = getString(R.string.required_field)
                    teleponPelangganEditText.error= getString(R.string.required_field)
                    tanggalLahirPelangganEditText.error =  getString(R.string.required_field)
                    provinsiPelangganEditText.error =  getString(R.string.required_field)
                    kotaPelangganEditText.error =  getString(R.string.required_field)
                    kecamatanPelangganEditText.error =  getString(R.string.required_field)
                    kelurahanPelangganEditText.error =  getString(R.string.required_field)
                    kodePosPelangganEditText.error =  getString(R.string.required_field)
                } else if (
                    namePelangganEditText.length() != 0 &&
                    nikPelangganEditText.length() != 0 &&
                    jenisKelaminPelangganEditText.length() != 0 &&
                    emailPelangganEditText.length() != 0 &&
                    passwordPelangganEditText.length() != 0 &&
                    passwordConfirmPelangganEditText.length() != 0 &&
                    teleponPelangganEditText.length() != 0 &&
                    tanggalLahirPelangganEditText.length() != 0 &&
                    provinsiPelangganEditText.length() != 0 &&
                    kotaPelangganEditText.length() != 0 &&
                    kecamatanPelangganEditText.length() != 0 &&
                    kelurahanPelangganEditText.length() != 0 &&
                    kodePosPelangganEditText.length() != 0
                ) {
                    showLoading()
                    postText()
                    showToast()
                    moveActivity()
                }
            }
        }
    }

    private fun postText() {
        val userType = intent.getStringExtra("userType")

        if (userType == "Pelanggan") {
            binding.apply {
                registerViewModel.registerPelanggan(
                    userType,
                    namePelangganEditText.text.toString(),
                    emailPelangganEditText.text.toString(),
                    passwordPelangganEditText.text.toString(),
                    passwordConfirmPelangganEditText.text.toString(),
                    nikPelangganEditText.text.toString(),
                    teleponPelangganEditText.text.toString(),
                    jenisKelaminPelangganEditText.text.toString(),
                    tanggalLahirPelangganEditText.text.toString(),
                    provinsiPelangganEditText.text.toString(),
                    kotaPelangganEditText.text.toString(),
                    kecamatanPelangganEditText.text.toString(),
                    kelurahanPelangganEditText.text.toString(),
                    kodePosPelangganEditText.text.toString()
                )
            }
        }
    }

    private fun showLoading() {
        registerViewModel.isLoading.observe(this@RegistFirstSeekerActivity) {
            binding.progressbarRegister2.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun showToast() {
        registerViewModel.toastText.observe(this@RegistFirstSeekerActivity) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(
                    this@RegistFirstSeekerActivity, toastText, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun moveActivity(){
        registerViewModel.registerResponse.observe(this@RegistFirstSeekerActivity){ response ->
            if (response.success == true){
                startActivity(Intent(this@RegistFirstSeekerActivity, LoginActivity::class.java))
                finish()
            }
        }
    }


}