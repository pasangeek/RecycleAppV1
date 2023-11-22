package com.example.recycleappv1.common

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.recycleappv1.ui.onboarding.location.LocationViewModel

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.Locale

@AndroidEntryPoint
open class BaseFragment : Fragment() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var locationRequest = LocationRequest()
    private val viewModel: LocationViewModel by viewModels()
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
                Log.d("BaseFragment", "Location Result - Latitude: ${it.latitude}, Longitude: ${it.longitude}")
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
        // Example: Display a Toast message with the found city name
        Toast.makeText(requireContext(), "City found: $cityName", Toast.LENGTH_SHORT).show()
    }

    private fun getCityName(lat: Double, long: Double) {
        val geoCoder = Geocoder(requireContext(), Locale.getDefault())
        lifecycleScope.launch {
           try {

           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                geoCoder.getFromLocation(lat, long, 3) { addresses ->
                    loadAddress(addresses);
                }
            } else {
                val addresses = geoCoder.getFromLocation(lat, long, 3)
                loadAddress(addresses);
            }
           } catch  (e: IOException) {
               // Handle IOException, which can occur if there is no internet connectivity
               handleNetworkError()
           }
        }


    }

    fun updateCurrentCity() {
        if (!checkLocationPermission()) {
            Log.d("BaseFragment", "Location permission not granted, requesting...")
            requestLocationPermission()
            return
        }

        if (isInternetAvailable()) {
            fusedLocationClient.requestLocationUpdates(
                locationRequest, locationCallback, Looper.myLooper()
            )
        } else {
            handleNetworkError()
        }

    }

    private fun requestLocationPermission() {
        Log.d("BaseFragment", "Requesting location permission...")
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
                Log.d("BaseFragment", "Notification permission not granted, requesting...")
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
        Log.e("BaseFragment", "Failed to retrieve location information")
        if (!isInternetAvailable()) {
            Log.e("BaseFragment", "No internet connection")
            viewModel.errorState.value = ErrorState.NetworkError("No internet connection available")
            // Handle the case when there is no internet connection
            Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT).show()
        } else {
            Log.e("BaseFragment", "Failed to retrieve location information")
            // Handle the case when location information could not be retrieved
            Toast.makeText(requireContext(), "Failed to retrieve location information", Toast.LENGTH_SHORT).show()
        }


    }
    private fun handleNetworkError() {
        // This function can be customized to handle the absence of internet connectivity
        viewModel.errorState.value = ErrorState.NetworkError("No internet connection available")
        Log.e("BaseFragment", "No internet connection")
        Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT).show()
    }


    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}