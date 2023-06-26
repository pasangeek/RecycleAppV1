package com.example.recycleappv1.di

import com.example.recycleappv1.model.repository.RecycleItemsImplementation
import com.example.recycleappv1.model.repository.RecycleItemsRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RecycleItemRepositoryModule {

    @Provides
    @Singleton
    fun  providesRecycleItemRepository(
        database :FirebaseFirestore
    ):RecycleItemsRepository{
        return RecycleItemsImplementation(database)
    }
}