package com.example.recycleappv1.ui.onboarding.splash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.recycleappv1.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private const val SPLASH_DISPLAY_LENGTH =
    2000L // Adjust the duration as needed (in milliseconds).
class SplashFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            delay(SPLASH_DISPLAY_LENGTH)

            if (onBoardingFinished()) {
                navigateToMainContent()
            } else {
                navigateToFirstScreen()
            }

        }
    }
    private fun onBoardingFinished(): Boolean {

        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

    private fun navigateToMainContent() {
        findNavController().navigate(R.id.action_splashFragment_to_nav_home)
    }


    private fun navigateToFirstScreen() {
        findNavController().navigate(R.id.action_splashFragment_to_firstScreen)
    }

}