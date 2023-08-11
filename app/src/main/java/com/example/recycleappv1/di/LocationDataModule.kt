package com.example.recycleappv1.di

import android.content.Context
import com.example.recycleappv1.data.sources.IDataSource
import com.example.recycleappv1.data.sources.ImplRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationDataModule {
    @Singleton
    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context)= ImplRepository ( context)
}