package com.example.se1_hw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.se1_hw.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        Log.d("onCreate", "onCreate(run)")
        setContentView(binding.root)
    }
    override fun onStart() {
        super.onStart()
        Log.d("onStart", "onStart(run)")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d("onRestart", "onRestart(run)")
    }
    override fun onResume() {
        super.onResume()
        Log.d("onResume", "onResume(run)")
    }
    override fun onPause() {
        super.onPause()
        Log.d("onPause", "onPause(run)")
    }
    override fun onStop() {
        super.onStop()
        Log.d("onStop", "onStop(run)")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "onDestroy(run)")
    }
}