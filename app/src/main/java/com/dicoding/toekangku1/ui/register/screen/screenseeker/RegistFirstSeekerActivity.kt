package com.dicoding.toekangku1.ui.register.screen.screenseeker

// Pastikan Anda mengimpor semua kelas yang dibutuhkan
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.databinding.ActivityRegistFirstSeekerBinding
import com.dicoding.toekangku1.ui.ViewModelFactory
import com.dicoding.toekangku1.ui.login.LoginActivity
import com.dicoding.toekangku1.ui.register.RegisterViewModel

class RegistFirstSeekerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistFirstSeekerBinding
    private val registerViewModel: RegisterViewModel by viewModels { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menggunakan metode inflate dari kelas binding
        binding = ActivityRegistFirstSeekerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
    }

    private fun setupView() {
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.btnSimpanRegistPelanggan.setOnClickListener {
            postText()
        }
    }

    private fun postText() {
        val userType = intent.getStringExtra("userType") ?: "Pelanggan"

        with(binding) {
            if (validasiInput()) {
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

                observeViewModel()
            }
        }
    }

    private fun validasiInput(): Boolean {
        with(binding) {
            var isValid = true

            if (namePelangganEditText.text.toString().isEmpty()) {
                namePelangganEditText.error = getString(R.string.required_field)
                isValid = false
            }

            if (nikPelangganEditText.text.toString().isEmpty()) {
                nikPelangganEditText.error = getString(R.string.required_field)
                isValid = false
            }

            if (jenisKelaminPelangganEditText.text.toString().isEmpty()) {
                jenisKelaminPelangganEditText.error = getString(R.string.required_field)
                isValid = false
            }

            if (emailPelangganEditText.text.toString().isEmpty()) {
                emailPelangganEditText.error = getString(R.string.required_field)
                isValid = false
            }

            if (passwordPelangganEditText.text.toString().isEmpty()) {
                passwordPelangganEditText.error = getString(R.string.required_field)
                isValid = false
            }

            if (passwordConfirmPelangganEditText.text.toString().isEmpty()) {
                passwordConfirmPelangganEditText.error = getString(R.string.required_field)
                isValid = false
            }

            if (teleponPelangganEditText.text.toString().isEmpty()) {
                teleponPelangganEditText.error = getString(R.string.required_field)
                isValid = false
            }

            if (tanggalLahirPelangganEditText.text.toString().isEmpty()) {
                tanggalLahirPelangganEditText.error = getString(R.string.required_field)
                isValid = false
            }

            if (provinsiPelangganEditText.text.toString().isEmpty()) {
                provinsiPelangganEditText.error = getString(R.string.required_field)
                isValid = false
            }

            if (kotaPelangganEditText.text.toString().isEmpty()) {
                kotaPelangganEditText.error = getString(R.string.required_field)
                isValid = false
            }

            if (kecamatanPelangganEditText.text.toString().isEmpty()) {
                kecamatanPelangganEditText.error = getString(R.string.required_field)
                isValid = false
            }

            if (kelurahanPelangganEditText.text.toString().isEmpty()) {
                kelurahanPelangganEditText.error = getString(R.string.required_field)
                isValid = false
            }

            if (kodePosPelangganEditText.text.toString().isEmpty()) {
                kodePosPelangganEditText.error = getString(R.string.required_field)
                isValid = false
            }

            return isValid
        }
    }


    private fun observeViewModel() {
        registerViewModel.isLoading.observe(this) {
            binding.progressbarRegister2.visibility = if (it) View.VISIBLE else View.GONE
        }

        registerViewModel.toastText.observe(this) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
            }
        }

        registerViewModel.registerResponse.observe(this) { response ->
            if (response.success == true){
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}