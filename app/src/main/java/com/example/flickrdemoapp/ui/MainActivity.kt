package com.example.flickrdemoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.example.flickrdemoapp.R
import com.example.flickrdemoapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return false
    }

}