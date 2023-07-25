package com.example.recycleappv1.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recycleappv1.R
import com.example.recycleappv1.databinding.FragmentViewPagerBinding
import com.example.recycleappv1.ui.onboarding.screens.FirstScreen
import com.example.recycleappv1.ui.onboarding.screens.SecondScreen
import com.example.recycleappv1.ui.onboarding.screens.ThirdScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerFragment : Fragment() {


private lateinit var _binding : FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
     _binding=FragmentViewPagerBinding.inflate(inflater, container, false)
val root :  View =_binding.root

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()

        )
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )


        _binding.viewPager.adapter = adapter
   return  root

    }


}