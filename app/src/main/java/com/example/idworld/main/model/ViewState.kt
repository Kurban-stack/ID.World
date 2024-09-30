package com.example.idworld.main.model

import com.example.idworld.main.location.Location

sealed class ViewState {
    object Initial: ViewState()
    data class Success(val location: Location? = null): ViewState()
    object Failure: ViewState()
}