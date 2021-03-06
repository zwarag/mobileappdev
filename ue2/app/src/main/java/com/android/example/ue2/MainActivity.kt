package com.android.example.ue2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.example.ue2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movie = Movie()
        // setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.fab.setOnClickListener { showToast() }
        binding.apply { binding.movie = movie }
    }

    private fun showToast() {
        Toast.makeText(this, "EAT A TOAST", Toast.LENGTH_LONG).show()
    }
}
