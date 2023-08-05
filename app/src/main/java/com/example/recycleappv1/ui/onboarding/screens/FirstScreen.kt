package com.example.recycleappv1.ui.onboarding.screens

import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recycleappv1.R
import com.example.recycleappv1.data.model.LocationData
import com.example.recycleappv1.databinding.FragmentFirstScreenBinding


import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class FirstScreen : Fragment() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    val PERMISSION_ID = 1010
    private lateinit var _binding: FragmentFirstScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFirstScreenBinding.inflate(inflater, container, false)
        val root: View = _binding.root
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        // mFusedLocationClient = LocationServices.getFusedLocationProviderClient(Activity())
        //val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager)

        _binding.button.setOnClickListener {
            Log.d("Debug:", checkLocationPermission().toString())
            Log.d("Debug:", isLocationEnabled(requireContext()).toString())
            requestLocationPermission()
            getLastLocation()
        }


        _binding.next.setOnClickListener {
            //viewPager?.currentItem = 1
            navigateToThirdFragment()

        }
        return root

    }

    fun getLastLocation() {
        if (checkLocationPermission()) {
            if (isLocationEnabled(requireContext())) {
                fusedLocationClient.lastLocation.addOnCompleteListener { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        NewLocationData()
                    } else {
                        Log.d("Debug:", "Your Location:" + location.longitude)
                        getCityName(
                            location.latitude,
                            location.longitude
                        )

                    }
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please Turn on Your device Location",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            requestLocationPermission()
        }
    }

    private fun getCityName(lat: Double, long: Double) {
        var cityName: String = ""

        val geoCoder = Geocoder(requireContext(), Locale.getDefault())
        val Adress = geoCoder.getFromLocation(lat, long, 3)

        if (Adress != null) {
            cityName = Adress.get(0).locality
        }
        val locationData: LocationData= LocationData(lat, long, cityName)

        _binding.address.text = "You Current Location is : Long: " + locationData.city
        Log.d("Debug:", "Your City: " + cityName)

    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val lastLocation: Location = locationResult.lastLocation
            Log.d("Debug:", "your last last location: " + lastLocation.longitude.toString())
         
        }
    }

    fun NewLocationData() {
        val locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()
        )
    }

    /*  override fun onStart() {
          super.onStart()
          checkLocationPermission()
          requestLocationPermission()
          isLocationEnabled(requireContext())

      }*/
    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }

    fun isLocationEnabled(context: Context): Boolean {
        //this function will return to us the state of the location service
        //if the gps or the network provider is enabled then it will return true otherwise it will return false
        val locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Debug:", "You have the Permission")
            }
        }
    }


    private fun navigateToThirdFragment() {
        findNavController().navigate(R.id.action_firstScreen_to_thirdScreen2)
    }


}