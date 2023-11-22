package com.example.recycleappv1.ui.onboarding.screens

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.recycleappv1.R
import com.example.recycleappv1.common.disable
import com.example.recycleappv1.common.enable
import com.example.recycleappv1.common.hide
import com.example.recycleappv1.common.show
import com.example.recycleappv1.databinding.FragmentSecondScreenBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SecondScreen : Fragment() {
    private var mPhotoUri: Uri? = null
    private lateinit var _binding: FragmentSecondScreenBinding
    private val secondScreeViewModel: SecondScreeViewModel by viewModels()
    private var shutdownCountdown = 10
    // ActivityResultLauncher to handle camera capture
    private val cameraLaunch =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            onCameraImageCaptured(it)
        }
    // Function to handle the captured camera image
    private fun onCameraImageCaptured(it: ActivityResult?) {
        if (it?.resultCode == Activity.RESULT_OK && mPhotoUri != null) {
            _binding.progressBar.show()
            _binding.btnCamera.disable()
            secondScreeViewModel.uploadImage(mPhotoUri!!).observe(
                viewLifecycleOwner
                // Actions to perform on successful image capture
            ) { uploaded ->
                _binding.progressBar.hide()
                _binding.btnCamera.enable()
                if (uploaded) {
                    // Show success message and start countdown
                    requireContext().showAlert(title = "Success",
                        message = "Uploaded successfully we will add the details to the system soon. Please stay tuned with us",
                        positiveClick = {

                        })
                } else {
                    // Show failure message
                    requireContext().showAlert(title = "Failed",
                        message = "Uploading failed. Please try again",
                        positiveClick = {

                        })
                }
            }
        }

    }
    // Function to start the shutdown countdown
    private fun startShutdownCountdown() {
        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                if (shutdownCountdown > 0) {
                    // Update your UI or perform other actions as needed
                    // Decrement the countdown and schedule the runnable again
                    shutdownCountdown--
                    handler.postDelayed(this, 1000) // Run every 1 second (1000 ms)
                } else {
                    // Shutdown the application when the countdown reaches 0
                    requireActivity().finish()
                }
            }
        }

        // Start the countdown
        handler.post(runnable)
    }
    //With these modifications, after a successful photo upload, the application will display a success message and start a countdown. When the countdown reaches 0, the application will shut down. You can adjust the shutdownCountdown variable to change the duration of the countdown.
    fun Context.showAlert(
        title: String, message: String, positiveClick: () -> Unit
    ) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(R.string.OK) { dialogInterface: DialogInterface, _: Int ->
                // Start shutdown countdown on positive button click
                startShutdownCountdown()
                positiveClick()
            }
        }.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSecondScreenBinding.inflate(inflater, container, false)
        val root: View = _binding.root

// Button click to initiate camera capture
        _binding.btnCamera.setOnClickListener {
            dispatchTakePictureIntent()
        }


        return root
    }
    // Function to initiate camera capture
    private fun dispatchTakePictureIntent() {
        mPhotoUri = requireContext().contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            ContentValues()
        )
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri)
        if (takePictureIntent.resolveActivity(requireContext().packageManager) != null) {
            cameraLaunch.launch(takePictureIntent)
        }
    }


}