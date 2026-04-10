package com.route.newsc43.ui.screens.maps

import android.location.Location
import android.os.Handler
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState
import kotlinx.coroutines.launch
//Real time database

@Composable
fun GoogleMapsView() {
    val context = LocalContext.current
    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    var userLocation = remember {
        mutableStateOf<Location?>(null)
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 10f)
    }
    val userPosition =remember {
        mutableStateOf<LatLng?>(null)
    }
    val markerState = rememberUpdatedMarkerState()
    val scope = rememberCoroutineScope()

    DisposableEffect(Unit) {

//        locationClient.getCurrentLocation(
//            Priority.PRIORITY_HIGH_ACCURACY,
//            CancellationTokenSource().token,
//        ).addOnSuccessListener { location ->

//        }.addOnFailureListener {
//            Log.e("getCurrentLocation - addOnFailureListener", "${it}")
//        }
        onDispose { }
    }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = true),
            onMapLoaded = {

                val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 2000)
                    .setMinUpdateIntervalMillis(2000)
                    .build()

                val locationCallback = object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult) {
//                        var location = locationResult.lastLocation
//                        Log.e("getCurrentLocation - addOnSuccessListener", "${location}")
//                        userLocation.value = location
//                        val latLng = LatLng(
//                            userLocation.value!!.latitude,
//                            userLocation.value!!.longitude
//                        )
////                newCameraPosition(CameraPosition(latLng, 15f, 0f, 0f)
//
//                        val cameraUpdate = CameraUpdateFactory.newCameraPosition(CameraPosition(latLng, 20f, 0f, 0f))
//                        cameraPositionState.move(cameraUpdate)
//                        scope.launch {
//                            cameraPositionState.animate(cameraUpdate, 500)
//                        }
//
////                        cameraPositionState.position = CameraPosition.fromLatLngZoom(
////                            latLng, 20f
////                        )
//                        markerState.position = latLng
//                        userPosition.value = latLng
                    }
                }

                locationClient.requestLocationUpdates(locationRequest, locationCallback, Handler().looper)
            }
        ){

            AdvancedMarker(
                state = MarkerState(position = userPosition.value?: LatLng(0.0, 0.0)),
                title = "Marker in Sydney",
                on
            )
    }

}