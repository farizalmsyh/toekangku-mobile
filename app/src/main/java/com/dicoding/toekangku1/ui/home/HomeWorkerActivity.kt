package com.dicoding.toekangku1.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.databinding.ActivityHomeWorkerBinding
import com.dicoding.toekangku1.ui.chat.ChatActivity
import com.dicoding.toekangku1.ui.profile.ProfileActivity
import com.dicoding.toekangku1.ui.thread.ThreadSeekerActivity
import com.dicoding.toekangku1.ui.thread.mythreadsseeker.MyDetailThreadsSeeker
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeWorkerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeWorkerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeWorkerBinding.inflate(layoutInflater)
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

    }

    private fun setupAction(){
        binding.rvTerkaitHome.setOnClickListener {
            startActivity(Intent(this, MyDetailThreadsSeeker::class.java))
        }
    }
}