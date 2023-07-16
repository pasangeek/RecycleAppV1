package com.example.recycleappv1.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FirebaseModule {
@Provides
@Singleton
fun provideFireStoreInstant():FirebaseFirestore{
    return  FirebaseFirestore.getInstance()
}
}