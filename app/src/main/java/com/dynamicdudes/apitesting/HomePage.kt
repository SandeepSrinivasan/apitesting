package com.dynamicdudes.apitesting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dynamicdudes.apitesting.databinding.ActivityHomePageBinding

class HomePage : AppCompatActivity() {

    private lateinit var binding : ActivityHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}