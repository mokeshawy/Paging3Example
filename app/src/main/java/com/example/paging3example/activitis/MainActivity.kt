package com.example.paging3example.activitis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.paging3example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}