package com.dicoding.toekangku1.ui.register.screen.screenworker

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.data.Experience
import com.dicoding.toekangku1.databinding.ActivityRegistFirstWorkerBinding
import com.dicoding.toekangku1.ui.ViewModelFactory
import com.dicoding.toekangku1.ui.login.LoginActivity
import com.dicoding.toekangku1.ui.register.RegisterViewModel
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class RegisterFirstWorkerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistFirstWorkerBinding
    private lateinit var factory: ViewModelFactory
    private val registerViewModel: RegisterViewModel by viewModels { factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistFirstWorkerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        setupViewModel()
    }

    private fun setupViewModel(){
        factory = ViewModelFactory.getInstance(this)
    }

    private fun setupView(){
        binding = ActivityRegistFirstWorkerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
    }

    private fun setupAction(){
        binding.apply {

            // Tambahkan aksi untuk menampilkan date picker untuk pengalaman pertama
            pengalamanPertamaDateEditText.setOnClickListener {
                showDatePickerDialog(pengalamanPertamaDateEditText)
            }

            // Tambahkan aksi untuk menampilkan date picker untuk pengalaman kedua
            pengalamanKeduaDateEditText.setOnClickListener {
                showDatePickerDialog(pengalamanKeduaDateEditText)
            }

            // Tambahkan aksi untuk menampilkan date picker untuk pengalaman ketiga
            pengalamanKetigaDateEditText.setOnClickListener {
                showDatePickerDialog(pengalamanKetigaDateEditText)
            }

            btnSimpanRegistPekerja.setOnClickListener {
                if (
                    nameEditText.length() == 0 &&
                    NIKEditText.length() == 0 &&
                    jenisKelaminEditText.length() == 0 &&
                    emailEditText.length() == 0 &&
                    passwordPekerjaEditText.length() == 0 &&
                    passwordConfirmEditText.length() == 0 &&
                    teleponEditText.length() == 0 &&
                    tanggalLahirEditText.length() == 0 &&
                    provinsiEditText.length() == 0 &&
                    kotaEditText.length() == 0 &&
                    kecamatanEditText.length() == 0 &&
                    kelurahanEditText.length() == 0 &&
                    kodePosEditText.length() == 0 &&
                    profesiEditText.length() == 0 &&
                    tahunEditText.length() == 0
                ) {
                    nameEditText.error = getString(R.string.required_field)
                    NIKEditText.error =  getString(R.string.required_field)
                    jenisKelaminEditText.error =  getString(R.string.required_field)
                    emailEditText.error = getString(R.string.required_field)
                    passwordPekerjaEditText.error = getString(R.string.required_field)
                    passwordConfirmEditText.error = getString(R.string.required_field)
                    teleponEditText.error= getString(R.string.required_field)
                    tanggalLahirEditText.error =  getString(R.string.required_field)
                    provinsiEditText.error =  getString(R.string.required_field)
                    kotaEditText.error =  getString(R.string.required_field)
                    kecamatanEditText.error =  getString(R.string.required_field)
                    kelurahanEditText.error =  getString(R.string.required_field)
                    kodePosEditText.error =  getString(R.string.required_field)
                    profesiEditText.error = getString(R.string.required_field)
                    tahunEditText.error = getString(R.string.required_field)
                } else if (
                    nameEditText.length() != 0 &&
                    NIKEditText.length() != 0 &&
                    jenisKelaminEditText.length() != 0 &&
                    emailEditText.length() != 0 &&
                    passwordPekerjaEditText.length() != 0 &&
                    passwordConfirmEditText.length() != 0 &&
                    teleponEditText.length() != 0 &&
                    tanggalLahirEditText.length() != 0 &&
                    provinsiEditText.length() != 0 &&
                    kotaEditText.length() != 0 &&
                    kecamatanEditText.length() != 0 &&
                    kelurahanEditText.length() != 0 &&
                    kodePosEditText.length() != 0 &&
                    profesiEditText.length() != 0 &&
                    tahunEditText.length() != 0
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
        binding.apply {
            val pengalamanList = arrayOfNulls<Experience>(3)

            val pengalamanPertama = Experience(
                name = pengalamanPertamaEditText.text.toString(),
                date = pengalamanPertamaDateEditText.text.toString(),
                description = pengalamanPertamaDesc.text.toString()
            )

            val pengalamanKedua = Experience(
                name = pengalamanKeduaEditText.text.toString(),
                date = pengalamanKeduaDateEditText.text.toString(),
                description = pengalamanKeduaDesc.text.toString()
            )

            val pengalamanKetiga = Experience(
                name = pengalamanKetigaEditText.text.toString(),
                date = pengalamanKetigaDateEditText.text.toString(),
                description = pengalamanKetigaDesc.text.toString()
            )

            pengalamanList[0] = pengalamanPertama
            pengalamanList[1] = pengalamanKedua
            pengalamanList[2] = pengalamanKetiga

            // Mengambil jenis pengguna dari Intent
            val userType = intent.getStringExtra("userType")

            // Pastikan jenis pengguna dan pengalaman tidak null sebelum mengirim ke API
            if (!userType.isNullOrBlank()) {
                registerViewModel.register(
                    userType,
                    nameEditText.text.toString(),
                    emailEditText.text.toString(),
                    passwordPekerjaEditText.text.toString(),
                    passwordConfirmEditText.text.toString(),
                    NIKEditText.text.toString(),
                    teleponEditText.text.toString(),
                    jenisKelaminEditText.text.toString(),
                    tanggalLahirEditText.text.toString(),
                    provinsiEditText.text.toString(),
                    kotaEditText.text.toString(),
                    kecamatanEditText.text.toString(),
                    kelurahanEditText.text.toString(),
                    kodePosEditText.text.toString(),
                    profesiEditText.text.toString(),
                    tahunEditText.text.toString(),
                    pengalamanList.requireNoNulls()
                )
            }
        }
    }

    private fun showDatePickerDialog(editText: TextInputEditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                editText.setText(selectedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }


    private fun showLoading() {
        registerViewModel.isLoading.observe(this@RegisterFirstWorkerActivity) {
            binding.progressbarRegister.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun showToast() {
        registerViewModel.toastText.observe(this@RegisterFirstWorkerActivity) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(
                    this@RegisterFirstWorkerActivity, toastText, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun moveActivity(){
        registerViewModel.registerResponse.observe(this@RegisterFirstWorkerActivity){ response ->
            if (response.success == true){
                startActivity(Intent(this@RegisterFirstWorkerActivity, LoginActivity::class.java))
                finish()
            }
        }
    }
}