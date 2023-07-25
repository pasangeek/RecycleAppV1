package com.example.recycleappv1.ui.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recycleappv1.R
import com.example.recycleappv1.databinding.FragmentSecondScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondScreen : Fragment() {
private  lateinit var _binding : FragmentSecondScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondScreenBinding.inflate(inflater, container, false)
        val root: View = _binding.root


        return  root
    }


}