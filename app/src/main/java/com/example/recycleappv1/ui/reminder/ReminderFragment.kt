package com.example.recycleappv1.ui.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.recycleappv1.common.BaseFragment
import com.example.recycleappv1.databinding.FragmentReminderBinding
import com.example.recycleappv1.ui.reminder.notification.Notification
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class ReminderFragment : BaseFragment() {

    private var _binding: FragmentReminderBinding? = null
    private lateinit var pendingIntent: PendingIntent
    // This property is only valid between onCreateView and
    // onDestroyView.

    private lateinit var myIntent: Intent
    private var alarmManager: AlarmManager? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val reminderViewModel =
            ViewModelProvider(this).get(ReminderViewModel::class.java)

        _binding = FragmentReminderBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }
    private fun setAlarm(target: Calendar) {
        alarmManager?.set(AlarmManager.RTC_WAKEUP, target.timeInMillis, pendingIntent)
        if (isAlarmSet()) {
            println("Alarm set success")
        }
    }
    private fun isAlarmSet(): Boolean {
        val intent = Intent(
            context,
            Notification::class.java
        ) // Replace YourReceiver with the appropriate receiver class
        val pendingIntent =
            PendingIntent.getBroadcast(
                context, 0, intent,
                PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
            )
        return pendingIntent != null
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}