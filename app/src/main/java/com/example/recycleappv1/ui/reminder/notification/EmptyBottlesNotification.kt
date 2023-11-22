package com.example.recycleappv1.ui.reminder.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.recycleappv1.MainActivity
import com.example.recycleappv1.R

const val channelIDEmptyBottles = "channel4"
class EmptyBottlesNotification : BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent) {
        val activity = Intent(context, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(
                context,
                4,
                activity,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

        val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(context, channelIDEmptyBottles)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Recycle App reminder")
                .setContentText("Today is a Empty Bottles collecting day")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true).build()
        } else {
            NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Recycle App reminder")
                .setContentText("Today is a Empty Bottles collecting day")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true).build()
        }
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(System.currentTimeMillis().toInt(), notification)
    }
}