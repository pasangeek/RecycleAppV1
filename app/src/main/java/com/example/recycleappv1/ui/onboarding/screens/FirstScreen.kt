package com.example.recycleappv1.ui.onboarding.screens


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.recycleappv1.R
import com.example.recycleappv1.databinding.FragmentFirstScreenBinding
import com.example.recycleappv1.ui.onboarding.location.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstScreen : Fragment() {

    private lateinit var _binding: FragmentFirstScreenBinding
    private val locationViewModel: LocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFirstScreenBinding.inflate(inflater, container, false)
        val root: View = _binding.root
        _binding.lifecycleOwner = this
        _binding.vm = locationViewModel

        _binding.next.setOnClickListener {
            navigateToThirdFragment()

        }
        return root

    }


    private fun navigateToThirdFragment() {
        findNavController().navigate(R.id.action_firstScreen_to_thirdScreen2)
    }



}