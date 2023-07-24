package com.example.recycleappv1.di

import android.content.Context
import com.example.recycleappv1.common.FirebaseStorageConstants

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {
    @Provides
    @Singleton
    fun provideFireStoreInstant(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }



    @Singleton
    @Provides
    fun provideFirebaseStroageInstance(): StorageReference {
        return FirebaseStorage.getInstance().getReference(FirebaseStorageConstants.ROOT_DIRECTORY)
    }
}