package com.example.recycleappv1

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.example.recycleappv1.ui.reminder.notification.channelID
import com.example.recycleappv1.ui.reminder.notification.channelIDBurnable
import com.example.recycleappv1.ui.reminder.notification.channelIDCardBoard
import com.example.recycleappv1.ui.reminder.notification.channelIDEmptyBottles
import com.example.recycleappv1.ui.reminder.notification.channelIDGlass
import com.example.recycleappv1.ui.reminder.notification.channelIDPet
import com.example.recycleappv1.ui.reminder.notification.channelIDPlastic
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoreApplication :Application(){

    override fun onCreate() {
        super.onCreate()
        val channelName: CharSequence = " Non Burnable Reminder"
        val importance = NotificationManager.IMPORTANCE_HIGH

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelID, channelName, importance)
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
        val channelBurnable: CharSequence = " Burnable Reminder"


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelIDBurnable, channelBurnable, importance)
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
        val channelCardBoard: CharSequence = " CardBoard And Paper Reminder"


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelIDCardBoard, channelCardBoard, importance)
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
        val channelEmptyBottles: CharSequence = " Empty Bottles Reminder"


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelIDEmptyBottles, channelEmptyBottles, importance)
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
        val channelGlass: CharSequence = " Glass Reminder"


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelIDGlass, channelGlass, importance)
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
        val channelPetBottles: CharSequence = " Pet Bottles Reminder"


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelIDPet, channelPetBottles, importance)
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
        val channelPlastic: CharSequence = " Plastic Reminder"


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelIDPlastic, channelPlastic, importance)
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}