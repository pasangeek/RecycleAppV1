package com.example.recycleappv1.ui.onboarding.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.recycleappv1.MainActivity
import com.example.recycleappv1.R
import com.example.recycleappv1.databinding.FragmentThirdScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : Fragment() {
private lateinit var _binding : FragmentThirdScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      _binding = FragmentThirdScreenBinding.inflate(inflater, container, false)

        val root: View = _binding.root


_binding.finish.setOnClickListener {
    onBoardingFinished()
    val intent = Intent(requireContext(), MainActivity::class.java)
    startActivity(intent)
    requireActivity().finish()
}
        return root

    //findNavController().navigate(R.id.)
   // findNavController().navigate(R.id.app_bar_main)


    }
private fun LocationSaveToSharedPreference(){

    val sharedPref = requireActivity().getSharedPreferences("Location", Context.MODE_PRIVATE)
    val editor = sharedPref.edit()
    editor.putBoolean("Finished", true)
    editor.apply()
}

    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()



    }

}