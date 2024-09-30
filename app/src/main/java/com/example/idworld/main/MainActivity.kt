package com.example.idworld.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.idworld.R
import com.example.idworld.databinding.MainActivityBinding
import com.example.idworld.main.model.ViewEvent
import com.example.idworld.main.model.ViewState
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var viewModel: MainViewModel? = null

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(view)
        requestPermission()
        observeState()
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            0
        )
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel?.state?.collect { state ->
                when (state) {
                    is ViewState.Success -> {
                        val location = state.location
                        // Update UI with location data
                        binding.tvLocation.text =
                            getString(
                                R.string.location_text,
                                location?.latitude.toString(),
                                location?.longitude.toString()
                            )
                    }

                    is ViewState.Failure -> {
                        // Some thing went wrong
                    }

                    ViewState.Initial -> {
                        viewModel?.handleEvent(ViewEvent.GetLocation)
                    }
                }
            }
        }
    }
}
