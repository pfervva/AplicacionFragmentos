package com.example.aplicacionfragmentos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacionfragmentos.controler.Controller
import com.example.aplicacionfragmentos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var controller: Controller
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun initRecyclerView() {
        binding.myRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}
