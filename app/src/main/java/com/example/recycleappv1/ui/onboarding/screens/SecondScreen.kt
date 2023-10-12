package com.example.recycleappv1.ui.onboarding.screens

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.recycleappv1.common.disable
import com.example.recycleappv1.common.enable
import com.example.recycleappv1.common.hide
import com.example.recycleappv1.common.show
import com.example.recycleappv1.common.showAlert
import com.example.recycleappv1.databinding.FragmentSecondScreenBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SecondScreen : Fragment() {
    private var mPhotoUri: Uri? = null
    private lateinit var _binding: FragmentSecondScreenBinding
    private val secondScreeViewModel: SecondScreeViewModel by viewModels()


    private val cameraLaunch =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            onCameraImageCaptured(it)
        }

    private fun onCameraImageCaptured(it: ActivityResult?) {
        if (it?.resultCode == Activity.RESULT_OK && mPhotoUri != null) {
            _binding.progressBar.show()
            _binding.btnCamera.disable()
            secondScreeViewModel.uploadImage(mPhotoUri!!).observe(
                viewLifecycleOwner
            ) { uploaded ->
                _binding.progressBar.hide()
                _binding.btnCamera.enable()
                if (uploaded) {
                    requireContext().showAlert(title = "Success",
                        message = "Uploaded successfully we will add the details to the system soon. Please stay tuned with us",
                        positiveClick = {
                            requireActivity().finish()
                        })
                } else {
                    requireContext().showAlert(title = "Failed",
                        message = "Uploading failed. Please try again",
                        positiveClick = {

                        })
                }
            }
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSecondScreenBinding.inflate(inflater, container, false)
        val root: View = _binding.root


        _binding.btnCamera.setOnClickListener {
            dispatchTakePictureIntent()
        }


        return root
    }

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