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
class CoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Define the channel names and importance for various types of reminders
        NotificationManager.IMPORTANCE_HIGH

        // Create notification channels for different types of reminders
        createNotificationChannel("Non Burnable Reminder", channelID)
        createNotificationChannel("Burnable Reminder", channelIDBurnable)
        createNotificationChannel("CardBoard And Paper Reminder", channelIDCardBoard)
        createNotificationChannel("Empty Bottles Reminder", channelIDEmptyBottles)
        createNotificationChannel("Glass Reminder", channelIDGlass)
        createNotificationChannel("Pet Bottles Reminder", channelIDPet)
        createNotificationChannel("Plastic Reminder", channelIDPlastic)
    }

    // Function to create a notification channel
    private fun createNotificationChannel(channelName: String, channelId: String) {
        // Check if the Android version supports notification channels
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create a notification channel with the given name and importance
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)

            // Get the notification manager and create the channel
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}