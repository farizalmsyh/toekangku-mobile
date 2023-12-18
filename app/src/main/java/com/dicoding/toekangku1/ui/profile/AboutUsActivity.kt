package com.dicoding.toekangku1.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.databinding.ActivityAboutUsBinding
import com.dicoding.toekangku1.databinding.ActivityHelpBinding
import com.dicoding.toekangku1.databinding.ActivityProfileBinding

class AboutUsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
    }

    private fun setupView(){
        supportActionBar?.hide()
    }

    private fun setupAction(){
        binding.btnKembaliAbout.setOnClickListener {
            onBackPressed()
        }
    }
}