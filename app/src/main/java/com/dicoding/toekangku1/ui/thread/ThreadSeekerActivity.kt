package com.dicoding.toekangku1.ui.thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.toekangku1.R

class ThreadSeekerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_seeker)

//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //bottomNavigationView.selectedItemId = R.id.menu_postingan
//        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
//            when (menuItem.itemId){
//                R.id.menu_home -> {
//                    val intent = Intent(applicationContext, HomeActivity::class.java)
//                    startActivity(intent)
//                    true
//                }
//                R.id.menu_postingan -> {
//                    val intent = Intent(applicationContext, ThreadActivity::class.java)
//                    startActivity(intent)
//                    true
//                }
//                R.id.menu_percakapan -> {
//                    true
//                }
//                R.id.menu_profil -> {
//                    val intent = Intent(applicationContext, ProfileActivity::class.java)
//                    startActivity(intent)
//                    true
//                }
//                else -> false
//            }
//        }

    }

    private fun setupAction(){

    }
}