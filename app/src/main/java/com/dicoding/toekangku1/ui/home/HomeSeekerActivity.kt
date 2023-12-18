package com.dicoding.toekangku1.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.databinding.ActivityHomeSeekerBinding
import com.dicoding.toekangku1.ui.ViewModelFactory
import com.dicoding.toekangku1.ui.chat.ChatActivity
import com.dicoding.toekangku1.ui.home.detail.DetailSeekerActivity
import com.dicoding.toekangku1.ui.home.detail.DetailWorkerActivity
import com.dicoding.toekangku1.ui.home.listworker.ListWorkerActivity
import com.dicoding.toekangku1.ui.login.LoginActivity
import com.dicoding.toekangku1.ui.profile.ProfileActivity
import com.dicoding.toekangku1.ui.thread.ThreadSeekerActivity
import com.dicoding.toekangku1.ui.thread.detail.DetailPostActivity
import com.dicoding.toekangku1.ui.thread.detail.DetailPostWorker
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeSeekerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeSeekerBinding
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
                    val intent = Intent(applicationContext, ChatActivity::class.java)
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
        homeViewModel.loadSessionToken()
    }

    private fun setupView(){
        supportActionBar?.hide()
    }

    private fun observeSessionToken(){
        homeViewModel.sessionToken.observe(this) { token ->
            if (token.isNullOrEmpty()) {
                // Token is null or empty
                // Redirect to Login Screen
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Token exists, you can proceed with user-specific data loading
            }
        }
    }

    private fun setupAction(){
        binding.selengkapnyaLabel.setOnClickListener{
            startActivity(Intent(this, ListWorkerActivity::class.java))
        }

        binding.recommendedWorkersRecyclerView.setOnClickListener{
            startActivity(Intent(this, DetailWorkerActivity::class.java))
        }

        binding.relatedPostsRecyclerView.setOnClickListener{
            startActivity(Intent(this, DetailPostActivity::class.java))
        }
    }
}