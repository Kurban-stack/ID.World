package com.example.idworld.main.model

sealed class ViewEvent {
    object GetLocation: ViewEvent()
}