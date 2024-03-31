package com.example.githubusers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.githubusers.databinding.MainActivityLayoutBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun addFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(binding.fragmentContainerView.id, fragment, fragment.tag)
        fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.commit()
    }
}
