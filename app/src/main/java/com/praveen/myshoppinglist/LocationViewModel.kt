package com.praveen.myshoppinglist

import android.support.v4.os.IResultReceiver._Parcel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LocationViewModel: ViewModel() {
    private val _location = mutableStateOf<LocationData?>(null)
    val location : State<LocationData?> = _location

    fun updateLocation(newLocation : LocationData){
        _location.value = newLocation
    }
}