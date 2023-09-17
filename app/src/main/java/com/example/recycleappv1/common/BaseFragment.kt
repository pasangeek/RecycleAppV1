package com.example.recycleappv1.common

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.os.Looper
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
open class BaseFragment : Fragment() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var locationRequest = LocationRequest()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            fusedLocationClient.removeLocationUpdates(
                this
            )

            locationResult.locations.forEach {
                println("sssss ${it.latitude} ${it.longitude}")
                getCityName(it.latitude, it.longitude)
            }
        }
    }

    private val pushNotificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->

        }

    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkNotificationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            return true
        }
    }

    private fun loadAddress(addresses: MutableList<Address>?) {
        if (addresses.isNullOrEmpty()) {
            onFailedLocation()
            return
        }
        val cityName: String = addresses[0].locality
        onCityFound(cityName)
    }

    open fun onCityFound(cityName: String) {

    }

    private fun getCityName(lat: Double, long: Double) {
        val geoCoder = Geocoder(requireContext(), Locale.getDefault())
        lifecycleScope.launch {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                geoCoder.getFromLocation(lat, long, 3) { addresses ->
                    loadAddress(addresses);
                }
            } else {
                val addresses = geoCoder.getFromLocation(lat, long, 3)
                loadAddress(addresses);
            }

        }


    }

    fun updateCurrentCity() {
        if (!checkLocationPermission()) {
            requestLocationPermission()
            return
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()
        )

    }

    private fun requestLocationPermission() {
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
    }

    fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!checkNotificationPermission()) {
                pushNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }


    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                updateCurrentCity()
            }

            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                updateCurrentCity()
            }

            else -> {
                onFailedLocation()
            }
        }
    }

    open fun onFailedLocation() {

    }
}