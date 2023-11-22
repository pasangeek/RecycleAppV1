package com.example.recycleappv1.ui.onboarding.location

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.recycleappv1.R
import com.example.recycleappv1.common.BaseFragment
import com.example.recycleappv1.common.ErrorState
import com.example.recycleappv1.common.disable
import com.example.recycleappv1.common.enable
import com.example.recycleappv1.common.hide
import com.example.recycleappv1.common.show
import com.example.recycleappv1.databinding.FragmentThirdScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import jp.co.yumemi.android.code_check.ui.error.ErrorDialog
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationFragment : BaseFragment() {
    private var _binding: FragmentThirdScreenBinding? = null
    private val locationViewModel: LocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentThirdScreenBinding.inflate(inflater, container, false)
        _binding!!.lifecycleOwner = this
        _binding!!.vm = locationViewModel
        val root: View =    _binding!!.root


        _binding!!.finish.setOnClickListener {
            onBoardingFinished()
            findNavController().navigate(R.id.action_locationFragment_to_nav_home)
        }
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding!!.progressBar.show()
        _binding?.finish?.disable()
        updateCurrentCity()

        initializeErrorDialog()
    }

    override fun onFailedLocation() {
        // Hide progress bar and enable finish button if location retrieval fails
        super.onFailedLocation()
        _binding!!.progressBar.hide()
        _binding!!.finish.enable()
    }

    override fun onCityFound(cityName: String) {
        super.onCityFound(cityName)
        lifecycleScope.launch {
            locationViewModel.checkLocationExist(cityName).observe(viewLifecycleOwner) {exist->
                _binding!!.progressBar.hide()
                // Hide progress bar and enable finish button
                _binding!!.finish.enable()
                if(!exist){
                    // Navigate to the second screen if the city doesn't exist
                    findNavController().navigate(R.id.action_locationFragment_to_secondScreen)
                }else{
                    // Finish onboarding if the city exists and save the city dat
                    onBoardingFinished()
                    locationViewModel.saveData(cityName)
                }
            }
        }


    }
    private fun initializeErrorDialog() {
        // Observe errorLiveData for network errors and display an error dialog
        locationViewModel.errorLiveData.observe(viewLifecycleOwner) { errorState ->
            when (errorState) {
                is ErrorState.NetworkError -> {
                    val dialogFragment = ErrorDialog(errorState.message, locationViewModel)
                    dialogFragment.show(childFragmentManager, "NetworkErrorDialog")
                }


            }

            // Handle other error states as needed
        }
    }
    private fun onBoardingFinished() {
        // Mark onboarding as finished in SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear the binding reference to avoid memory leaks
        // Clearing the binding reference
        _binding = null

    }
}