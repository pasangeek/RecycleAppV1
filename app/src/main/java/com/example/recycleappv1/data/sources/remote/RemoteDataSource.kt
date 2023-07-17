package com.example.recycleappv1.data.sources.remote

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class RemoteDataSource @Inject constructor (
    private val fireStore: FirebaseFirestore
) {
}