package com.example.recycleappv1.ui.onboarding.screens

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class SecondScreeViewModel @Inject constructor(
    firebaseStorage: FirebaseStorage,
) : ViewModel() {
    private val imageRef = firebaseStorage.reference.child("images")
    private val isUploadSuccess = MutableLiveData<Boolean>();
    fun uploadImage(imageUri: Uri): MutableLiveData<Boolean> {
        val imageFileName = "${System.currentTimeMillis()}_${UUID.randomUUID()}.jpg"
        val imageStorageRef = imageRef.child(imageFileName)
        imageStorageRef.putFile(imageUri).addOnSuccessListener {uploadTask->
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