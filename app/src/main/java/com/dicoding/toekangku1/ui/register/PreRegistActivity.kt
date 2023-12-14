package com.dicoding.toekangku1.ui.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.ui.register.screen.screenseeker.RegistFirstSeekerActivity
import com.dicoding.toekangku1.ui.register.screen.screenworker.RegisterFirstWorkerActivity

class PreRegistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var selectedUserType: String? = null

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_regist)

        val radioGroup: RadioGroup = findViewById(R.id.radio_group)
        val btnMasuk: Button = findViewById(R.id.btn_masuk_pre_regist)

        btnMasuk.isEnabled = false

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_ninjas -> {
                    selectedUserType = "Pekerja"
                    Toast.makeText(this, "Anda memilih Pekerja", Toast.LENGTH_SHORT).show()
                    btnMasuk.isEnabled = true
                }
                R.id.radio_pirates -> {
                    selectedUserType = "Pelanggan"
                    Toast.makeText(this, "Anda memilih Pelanggan", Toast.LENGTH_SHORT).show()
                    btnMasuk.isEnabled = true
                }
            }
        }

        btnMasuk.setOnClickListener {
            // Memastikan bahwa jenis pengguna telah dipilih sebelum melanjutkan
            if (selectedUserType != null) {
                // Lanjutkan ke aktivitas registrasi yang sesuai berdasarkan jenis pengguna
                when (selectedUserType) {
                    "Pekerja" -> {
                        val intent = Intent(this, RegisterFirstWorkerActivity::class.java)
                        // Mengirim jenis pengguna ke halaman registrasi selanjutnya
                        intent.putExtra("userType", selectedUserType)
                        startActivity(intent)
                    }
                    "Pelanggan" -> {
                        val intent = Intent(this, RegistFirstSeekerActivity::class.java)
                        // Mengirim jenis pengguna ke halaman registrasi selanjutnya
                        intent.putExtra("userType", selectedUserType)
                        startActivity(intent)
                    }
                }
            } else {
                Toast.makeText(this, "Pilih jenis pengguna terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }
}