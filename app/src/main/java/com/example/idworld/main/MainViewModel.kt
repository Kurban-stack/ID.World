package com.example.idworld.main

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.idworld.main.location.toLocationData
import com.example.idworld.main.model.ViewEvent
import com.example.idworld.main.model.ViewState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class MainViewModel(private val application: Application) : AndroidViewModel(application) {
    private val _state = MutableStateFlow<ViewState>(ViewState.Initial)
    val state: StateFlow<ViewState> get() = _state

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    fun handleEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.GetLocation -> fetchLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun fetchLocation() {
        viewModelScope.launch {
            if (!hasLocationPermission(application)) {
                _state.value = ViewState.Failure
                return@launch
            }
            try {
                val location = getLastLocation()
                _state.value = location?.let {
                    ViewState.Success(it.toLocationData())
                } ?: ViewState.Failure
            } catch (e: Exception) {
                _state.value = ViewState.Failure
            }
        }
    }

    @SuppressLint("MissingPermission")
    private suspend fun getLastLocation(): android.location.Location? {
        return suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation
                .addOnSuccessListener { continuation.resume(it) }
                .addOnFailureListener { continuation.resumeWithException(it) }
        }
    }

    private fun hasLocationPermission(context: Context): Boolean {
        return listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ).any { perm ->
            ActivityCompat.checkSelfPermission(context, perm) == PackageManager.PERMISSION_GRANTED
        }
    }
}