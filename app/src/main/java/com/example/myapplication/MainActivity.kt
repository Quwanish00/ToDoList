package com.example.myapplication

import Fragments.ListItemsFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(binding.root)



        supportFragmentManager.beginTransaction()
            .replace(R.id.container,ListItemsFragment())
            .commit()

    }
}