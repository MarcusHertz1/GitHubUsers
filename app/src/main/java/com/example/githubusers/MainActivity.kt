package com.example.githubusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.githubusers.databinding.MainActivityLayoutBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: MainActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
