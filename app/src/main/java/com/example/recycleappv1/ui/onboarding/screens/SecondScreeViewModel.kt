package com.example.recycleappv1.ui.onboarding.screens

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class SecondScreeViewModel @Inject constructor(
    firebaseStorage: FirebaseStorage,
) : ViewModel() {
    // Reference to the Firebase Storage "images" directory
    private val imageRef = firebaseStorage.reference.child("images")
    // LiveData to notify whether the image upload was successful or not
    private val isUploadSuccess = MutableLiveData<Boolean>();

    // Function to upload an image to Firebase Storage
    fun uploadImage(imageUri: Uri): MutableLiveData<Boolean> {
        // Generating a unique image file name
        val imageFileName = "${System.currentTimeMillis()}_${UUID.randomUUID()}.jpg"
        // Reference to the specific image file in Firebase Storage
        val imageStorageRef = imageRef.child(imageFileName)
        // Log: Before uploading image
        Log.d("SecondScreenViewModel", "Uploading image with name: $imageFileName")
        // Uploading the image file to Firebase Storage
        imageStorageRef.putFile(imageUri).addOnSuccessListener {uploadTask->
            Log.d("SecondScreenViewModel", "Image uploaded successfully. Image URL: $uploadTask")
            uploadTask.storage.downloadUrl.addOnSuccessListener {
                isUploadSuccess.postValue(true)
            }.addOnFailureListener {
                isUploadSuccess.postValue(false)
            }
        }.addOnFailureListener {
            isUploadSuccess.postValue(false)
        }
        return isUploadSuccess
    }
}