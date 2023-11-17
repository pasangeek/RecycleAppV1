package com.example.recycleappv1.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.firestore.ktx.memoryCacheSettings
import com.google.firebase.firestore.ktx.persistentCacheSettings
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {
    // Provides an instance of FirebaseFirestore with custom settings.
    @Provides
    @Singleton
    fun provideFireStoreInstant(): FirebaseFirestore {
        val db = FirebaseFirestore.getInstance()
        val settings = firestoreSettings {
            // Use memory cache
            setLocalCacheSettings(memoryCacheSettings {
            })
            // Use persistent disk cache (default)
            setLocalCacheSettings(persistentCacheSettings {

            })
            // Enable Firestore persistence for offline access

        }

        db.firestoreSettings = settings
        return db
    }

    // Provides an instance of FirebaseStorage
    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage{
        return FirebaseStorage.getInstance()
    }




}


