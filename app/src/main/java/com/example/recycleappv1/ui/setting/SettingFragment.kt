package com.example.recycleappv1.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.recycleappv1.MainActivity
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
          showConfirmationDialog(requireContext())


      }


        return root
    }

    fun deleteDataFromSharedPreferences(context: Context) {
        val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("Finished")
        editor.apply()
    }
    fun deleteRemindervalues(context: Context) {
        val sharedPreferences = context.getSharedPreferences("RecycleApp", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("BurnableReminder")
        editor.remove("nonBurnableReminder")

        editor.apply()
    }
    fun showConfirmationDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Confirm Action")
        builder.setMessage("Are you sure you want to clear your location? This action cannot be undone.")

        builder.setPositiveButton("Clear") { _, _ ->
            // User confirmed, delete data
            deleteDataFromSharedPreferences(context)
            restartApp(context)
        }

        builder.setNegativeButton("Cancel") { _, _ ->
            // User canceled the action
        }

        val dialog = builder.create()
        dialog.show()
    }
    fun restartApp(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
    }

}