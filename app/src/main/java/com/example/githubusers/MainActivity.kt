package com.example.githubusers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubusers.databinding.MainActivityLayoutBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
