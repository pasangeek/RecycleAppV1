package com.example.recycleappv1.ui.onboarding.location

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.recycleappv1.R
import com.example.recycleappv1.common.BaseFragment
import com.example.recycleappv1.common.hide
import com.example.recycleappv1.common.show
import com.example.recycleappv1.databinding.FragmentThirdScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : BaseFragment() {
    private lateinit var _binding: FragmentThirdScreenBinding
    private val locationViewModel: LocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentThirdScreenBinding.inflate(inflater, container, false)
        _binding.lifecycleOwner = this
        _binding.vm = locationViewModel
        val root: View = _binding.root


        _binding.finish.setOnClickListener {
            onBoardingFinished()
            findNavController().navigate(R.id.action_locationFragment_to_nav_home)
        }
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.progressBar.show()
        updateCurrentCity()
    }

    override fun onFailedLocation() {
        super.onFailedLocation()
        _binding.progressBar.hide()
    }

    override fun onCityFound(cityName: String) {
        super.onCityFound(cityName)
        locationViewModel.saveData(cityName)
        _binding.progressBar.hide()
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }


}