package com.dicoding.toekangku1.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.databinding.ActivityAboutUsBinding
import com.dicoding.toekangku1.databinding.ActivityHelpBinding

class HelpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHelpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView(){
        supportActionBar?.hide()
    }
}