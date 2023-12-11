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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_regist)

        val radioGroup: RadioGroup = findViewById(R.id.radio_group)
        val btnMasuk: Button = findViewById(R.id.btn_masuk_pre_regist)

        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)

        btnMasuk.isEnabled = false

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_ninjas -> {
                    // Simpan usertype di SharedPreferences
                    sharedPreferences.edit().putString("tipePengguna", "Pekerja").apply()
                    // Tampilkan pesan sesuai pemilihan RadioButton
                    Toast.makeText(this, "Anda memilih Pekerja", Toast.LENGTH_SHORT).show()
                    // Aktifkan tombol masuk setelah pemilihan dilakukan
                    btnMasuk.isEnabled = true
                }
                R.id.radio_pirates -> {
                    // Simpan usertype di SharedPreferences
                    sharedPreferences.edit().putString("tipePengguna", "Pelanggan").apply()
                    // Tampilkan pesan sesuai pemilihan RadioButton
                    Toast.makeText(this, "Anda memilih Pelanggan", Toast.LENGTH_SHORT).show()
                    // Aktifkan tombol masuk setelah pemilihan dilakukan
                    btnMasuk.isEnabled = true
                }
            }
        }

        btnMasuk.setOnClickListener {
            // Ambil usertype dari SharedPreferences
            val userType = sharedPreferences.getString("tipePengguna", "")

            // Lanjutkan ke aktivitas registrasi yang sesuai berdasarkan usertype
            when (userType) {
                "Pekerja" -> {
                    val intent = Intent(this, RegisterFirstWorkerActivity::class.java)
                    startActivity(intent)
                }
                "Pelanggan" -> {
                    val intent = Intent(this, RegistFirstSeekerActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    Toast.makeText(this, "Pilih jenis pengguna terlebih dahulu", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}