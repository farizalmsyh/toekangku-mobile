package com.dicoding.toekangku1.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.databinding.ActivityHomeSeekerBinding
import com.dicoding.toekangku1.response.GetUserResponse
import com.dicoding.toekangku1.response.User
import com.dicoding.toekangku1.ui.ViewModelFactory
import com.dicoding.toekangku1.ui.login.LoginActivity
import com.dicoding.toekangku1.ui.profile.ProfileActivity
import com.dicoding.toekangku1.ui.thread.ThreadSeekerActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeSeekerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeSeekerBinding
    private var token = ""
    private var type = ""
    private val homeViewModel: HomeViewModel by viewModels { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeSeekerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.menu_home
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId){
                R.id.menu_home -> {
                    true
                }
                R.id.menu_postingan -> {
                    val intent = Intent(applicationContext, ThreadSeekerActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_percakapan -> {
//                    val intent = Intent(applicationContext, ChatActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_profil -> {
                    val intent = Intent(applicationContext, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        setupView()
        observeSessionToken()
        homeViewModel.getSession(token, type)
        setupAction()
    }

    private fun setupView(){
        supportActionBar?.hide()
    }

    private fun observeSessionToken() {
        val user =
        homeViewModel.sessionData.observe(this, Observer { (token, userType) ->
            if (userType != "Pekerja") {
                // Jika userType adalah "Pelanggan", arahkan ke HomePelangganActivity
                val intent = Intent(this, HomeWorkerActivity::class.java)
                startActivity(intent)
                finish() // Menutup HomeSeekerActivity agar tidak kembali ke sini saat menekan tombol back
            } else {
                // Jika userType adalah "Seeker", setup tampilan untuk Seeker
                setupViewForSeeker()
            }
        })
    }

    private fun setupViewForSeeker(user: User){
        binding.lokasiPengguna.text = user.locationCity ?: "Lokasi tidak diketahui"
        binding.namaPengguna.text = user.name ?: "Nama tidak diketahui"

        // Pastikan Anda menghandle URL gambar dengan tepat jika tipe datanya adalah 'Any'
        // Misalnya, jika itu adalah String, Anda bisa gunakan Glide untuk memuat gambar:
        if (user.pictureUrl is String) {
            Glide.with(this)
                .load(user.pictureUrl)
                .circleCrop() // Jika Anda ingin gambar menjadi lingkaran
                .into(binding.profileSeeker) // Sesuaikan dengan ID ImageView yang sebenarnya
        }
    }

    private fun setupAction(){
        binding.selengkapnyaLabel.setOnClickListener{
//            startActivity(Intent(this, ListWorkerActivity::class.java))
        }

        binding.recommendedWorkersRecyclerView.setOnClickListener{
//            startActivity(Intent(this, DetailWorkerActivity::class.java))
        }

        binding.relatedPostsRecyclerView.setOnClickListener{
//            startActivity(Intent(this, DetailPostActivity::class.java))
        }
    }
}