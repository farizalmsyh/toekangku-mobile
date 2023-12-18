package com.dicoding.toekangku1.ui.home.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.databinding.ActivityDetailWorkerBinding
import com.dicoding.toekangku1.ui.home.offeringJob.WorkerJobOfferActivity

class DetailWorkerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailWorkerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailWorkerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupAction(){
        binding.btnAjukan.setOnClickListener {
            startActivity(Intent(this, WorkerJobOfferActivity::class.java))
        }
    }
}