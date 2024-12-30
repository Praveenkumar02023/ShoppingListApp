package com.praveen.myshoppinglist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun LocationSelectionScreen(
    location: LocationData,
    onLocationSelected: (LocationData) -> Unit
){
    val UserLocation = remember {
        mutableStateOf(
            LatLng(location.latitude,location.longitude))
    }

    val cameraPositionState = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(UserLocation.value,10f)
    }


    Column(modifier = Modifier.fillMaxSize()) {

        GoogleMap(
            modifier =  Modifier.weight(1f).padding(top = 16.dp),
            cameraPositionState = cameraPositionState,
            onMapClick = {
                UserLocation.value = it
            }
        ){
            Marker(state = MarkerState(UserLocation.value))
        }

        var newLocation : LocationData

        Button(onClick = {
            newLocation = LocationData(UserLocation.value.latitude,UserLocation.value.longitude)
            onLocationSelected(newLocation)
        }){
            Text(text = "Select Location")
        }

    }
}