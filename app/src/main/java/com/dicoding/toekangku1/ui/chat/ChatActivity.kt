package com.dicoding.toekangku1.ui.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.databinding.ActivityChatBinding
import com.dicoding.toekangku1.ui.ViewModelFactory
import com.dicoding.toekangku1.ui.home.HomeSeekerActivity
import com.dicoding.toekangku1.ui.profile.ProfileActivity
import com.dicoding.toekangku1.ui.thread.ThreadSeekerActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val loginViewModel: ChatActivityViewModel by viewModels { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.menu_percakapan
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId){
                R.id.menu_home -> {
                    val intent = Intent(applicationContext, HomeSeekerActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_postingan -> {
                    val intent = Intent(applicationContext, ThreadSeekerActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_percakapan -> {
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

        moveActivity()
    }

    fun moveActivity(){
        binding.messagesRecyclerView.setOnClickListener {
            startActivity(Intent(this, ChatDetailActivity::class.java))
        }
    }
}