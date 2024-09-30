package com.example.idworld.main.location

data class Location(val latitude: Double, val longitude: Double)

fun android.location.Location.toLocationData(): Location {
    return Location(latitude, longitude)
}
