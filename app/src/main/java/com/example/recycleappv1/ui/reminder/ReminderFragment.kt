package com.example.recycleappv1.ui.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.recycleappv1.common.BaseFragment
import com.example.recycleappv1.common.Result
import com.example.recycleappv1.common.convertToCalendarWithTime
import com.example.recycleappv1.common.gone
import com.example.recycleappv1.common.show
import com.example.recycleappv1.data.model.RecycleItemsData
import com.example.recycleappv1.databinding.FragmentReminderBinding
import com.example.recycleappv1.ui.reminder.notification.Notification
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class ReminderFragment : BaseFragment() {

    private lateinit var binding: FragmentReminderBinding
    private lateinit var pendingIntent: PendingIntent
    // This property is only valid between onCreateView and
    // onDestroyView.

    private lateinit var myIntent: Intent
    private var alarmManager: AlarmManager? = null
    private val viewModel: ReminderViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alarmManager = ContextCompat.getSystemService(requireContext(), AlarmManager::class.java)
        myIntent = Intent(requireContext(), Notification::class.java)
        pendingIntent =
            PendingIntent.getBroadcast(requireContext(), 0, myIntent, PendingIntent.FLAG_IMMUTABLE)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentReminderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.switch2.isChecked = viewModel.getNonBurnableReminderStatus()
        binding.switch2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.responseGetRecyclerItems.observe(viewLifecycleOwner) { it ->
                    when (it) {
                        is Result.Loading -> {
                            binding.progressBar.show()
                        }

                        is Result.Success<*> -> {
                            binding.progressBar.gone()
                            val items = it.result as List<RecycleItemsData>
                            items.forEach { item ->

                                val calendar = item.date?.convertToCalendarWithTime()
                                calendar?.let {
                                    setAlarm(it)
                                }

                            }

                        }

                        is Result.Failure -> {
                            binding.progressBar.gone()
                        }

                    }
                }
                viewModel.getRecyclerDataByWasteType()
            } else {
                alarmManager?.cancel(pendingIntent)
            }

            viewModel.saveNonBurnableReminderStatus(isChecked)
        }
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



}