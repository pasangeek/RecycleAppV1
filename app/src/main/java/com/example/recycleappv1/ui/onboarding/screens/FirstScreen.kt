package com.example.recycleappv1.ui.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.recycleappv1.R
import com.example.recycleappv1.databinding.FragmentFirstScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstScreen : Fragment() {

private  lateinit var  _binding : FragmentFirstScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFirstScreenBinding.inflate(inflater, container, false)
        val root : View = _binding.root

_binding.next.setOnClickListener{

    navigateToThirdFragment()

}
        return root

    }

    private fun navigateToThirdFragment() {
        findNavController().navigate(R.id.action_firstScreen_to_thirdScreen2)
    }


}