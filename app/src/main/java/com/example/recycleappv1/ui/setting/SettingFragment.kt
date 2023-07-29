package com.example.recycleappv1.ui.setting

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.recycleappv1.databinding.FragmentSettingBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private lateinit var _binding:FragmentSettingBinding

    // This property is only valid between onCreateView and
    // onDestroyView.


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        val root: View = _binding.root

      _binding.clear.setOnClickListener{

          deleteDataFromSharedPreferences(requireContext())

      }


        return root
    }

    fun deleteDataFromSharedPreferences(context: Context) {
        val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("Finished")
        editor.apply()
    }
}