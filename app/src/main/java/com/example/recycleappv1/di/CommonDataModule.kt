package com.example.recycleappv1.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CommonDataModule {

    /**
     * Provides a [SharedPreferences] instance named "RecycleApp" using the application context.
     * This instance allows storing and retrieving application-specific preferences persistently.
     *
     * @param context The [Context] obtained from the application.
     * @return The [SharedPreferences] instance for the "RecycleApp" with private mode.
     */
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("RecycleApp", Context.MODE_PRIVATE)
    }
}