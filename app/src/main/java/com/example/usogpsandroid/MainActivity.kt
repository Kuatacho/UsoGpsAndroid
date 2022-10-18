package com.example.usogpsandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.usogpsandroid.databinding.ActivityMainBinding

/*Activar binding e implementar google/services en gradle/module */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    //evaluar y gestionar si el gps en el celular esta ACTIVO
}























