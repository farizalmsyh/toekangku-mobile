package com.dicoding.toekangku1.ui.thread.mythreadsworker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.toekangku1.R
import com.dicoding.toekangku1.databinding.ActivityAboutUsBinding
import com.dicoding.toekangku1.databinding.ActivityNewPostWorkerBinding

class NewPostWorker : AppCompatActivity() {

    private lateinit var binding: ActivityNewPostWorkerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPostWorkerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}