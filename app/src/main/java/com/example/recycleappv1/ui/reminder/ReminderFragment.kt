package com.example.recycleappv1.ui.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import com.example.recycleappv1.common.BaseFragment
import com.example.recycleappv1.common.Result
import com.example.recycleappv1.common.convertToCalendarWithTime
import com.example.recycleappv1.common.gone
import com.example.recycleappv1.common.show
import com.example.recycleappv1.data.model.RecycleItemsData
import com.example.recycleappv1.databinding.FragmentReminderBinding
import com.example.recycleappv1.ui.reminder.notification.BurnableNotification
import com.example.recycleappv1.ui.reminder.notification.CardBoardnotification
import com.example.recycleappv1.ui.reminder.notification.EmptyBottlesNotification
import com.example.recycleappv1.ui.reminder.notification.GlassNotification
import com.example.recycleappv1.ui.reminder.notification.NonBurnableNotification
import com.example.recycleappv1.ui.reminder.notification.PetBottlesNotification
import com.example.recycleappv1.ui.reminder.notification.PlasticNotification
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class ReminderFragment : BaseFragment() {

    // Binding object for the FragmentReminder layout
    private lateinit var binding: FragmentReminderBinding
    private lateinit var pendingIntentNonBurnable: PendingIntent
    private lateinit var pendingIntentCardBoard: PendingIntent
    private lateinit var pendingIntentEmptyBottles: PendingIntent
    private lateinit var pendingIntentCans: PendingIntent
    private lateinit var pendingIntentPetBottles: PendingIntent
    private lateinit var pendingIntentPlastic: PendingIntent
    private lateinit var pendingIntentBurnable: PendingIntent
    // Intent objects for different types of reminders
    private lateinit var myIntent: Intent
    private lateinit var myIntentCardBoard: Intent
    private lateinit var myIntentEmptyBottles: Intent
    private lateinit var myIntentCans: Intent
    private lateinit var myIntentPetBottles: Intent
    private lateinit var myIntentPlastic: Intent
    private lateinit var myIntentBurnable: Intent
    // AlarmManager instance
    private var alarmManager: AlarmManager? = null
    // ViewModel associated with the ReminderFragment
    private val viewModel: ReminderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize the AlarmManager
        alarmManager = getSystemService(requireContext(), AlarmManager::class.java)
        // Initialize Intents for each reminder type
        myIntent = Intent(requireContext(), NonBurnableNotification::class.java)
        myIntentBurnable= Intent(requireContext(), BurnableNotification::class.java)
        myIntentCardBoard= Intent(requireContext(), CardBoardnotification::class.java)
        myIntentEmptyBottles= Intent(requireContext(), EmptyBottlesNotification::class.java)
        myIntentCans= Intent(requireContext(), GlassNotification::class.java)
        myIntentPetBottles= Intent(requireContext(), PetBottlesNotification::class.java)
        myIntentPlastic= Intent(requireContext(), PlasticNotification::class.java)
        // Create PendingIntent for each reminder type
        pendingIntentNonBurnable =
            PendingIntent.getBroadcast(requireContext(), 0, myIntent, PendingIntent.FLAG_IMMUTABLE)
        pendingIntentBurnable =
            PendingIntent.getBroadcast(requireContext(), 2, myIntentBurnable, PendingIntent.FLAG_IMMUTABLE)
        pendingIntentCardBoard =
            PendingIntent.getBroadcast(requireContext(), 3, myIntentCardBoard, PendingIntent.FLAG_IMMUTABLE)
        pendingIntentEmptyBottles =
            PendingIntent.getBroadcast(requireContext(), 4, myIntentEmptyBottles, PendingIntent.FLAG_IMMUTABLE)
        pendingIntentCans =
            PendingIntent.getBroadcast(requireContext(), 5, myIntentCans, PendingIntent.FLAG_IMMUTABLE)
        pendingIntentPetBottles =
            PendingIntent.getBroadcast(requireContext(), 6, myIntentPetBottles, PendingIntent.FLAG_IMMUTABLE)
        pendingIntentPlastic =
            PendingIntent.getBroadcast(requireContext(), 7, myIntentPlastic, PendingIntent.FLAG_IMMUTABLE)

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
// Set the switch to reflect the saved reminder status
        binding.switchNonBurnable.isChecked = viewModel.getNonBurnableReminderStatus()

        // Listen for changes in the switch state
        binding.switchNonBurnable.setOnCheckedChangeListener { _, isChecked ->
            Log.d("ReminderFragment", "Switch state changed: $isChecked")
            // Remove existing observers to prevent multiple observers
            viewModel.responseGetRecyclerItems.removeObservers(viewLifecycleOwner)
            // Check if the switch is toggled on
            if (isChecked) {
                // Observe changes in recycling items data
                viewModel.responseGetRecyclerItems.observe(viewLifecycleOwner) { it ->
                    when (it) {
                        is Result.Loading -> {
                            // Show progress bar when loading data
                            binding.progressBar.show()
                        }

                        is Result.Success<*> -> {
                            binding.progressBar.gone()
                            // Retrieve recycling items data and set alarms
                            val items = it.result as List<RecycleItemsData>
                            items.forEach { item ->

                                val calendar = item.date?.convertToCalendarWithTime()
                                calendar?.let {
                                    setAlarm(it)
                                }

                            }
                            Log.d("ReminderFragment", "Recycling data loaded successfully")
                        }

                        is Result.Failure -> {
                            // Handle failure cases when retrieving data
                            binding.progressBar.gone()
                            Log.e("ReminderFragment", "Failed to load recycling data")
                        }

                    }

                }  // Fetch recycling data based on waste type
                viewModel.getRecyclerDataByWasteType()
            } else { // Cancel the alarm if the switch is toggled off
                alarmManager?.cancel(pendingIntentNonBurnable)
                Log.d("ReminderFragment", "Reminder turned off. Alarm canceled.")
            }
            // Save the current switch state
            viewModel.saveNonBurnableReminderStatus(isChecked)
            Log.d("ReminderFragment", "Switch state saved: $isChecked")
        }


        binding.switchBurnable.isChecked = viewModel.getBurnableReminderStatus()
        binding.switchBurnable.setOnCheckedChangeListener { _, isChecked ->
            viewModel.responseGetRecyclerItems.removeObservers(viewLifecycleOwner)
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
                                    setAlarmBurnable(it)
                                }

                            }

                        }

                        is Result.Failure -> {
                            binding.progressBar.gone()
                        }

                    }

                }
                viewModel.getRecyclerDataByWasteTypeBurnable()
            } else {
                alarmManager?.cancel(pendingIntentBurnable)
            }

            viewModel.saveBurnableReminderStatus(isChecked)
        }


        binding.switchCardBoard.isChecked = viewModel.getCardBoardReminderStatus()
        binding.switchCardBoard.setOnCheckedChangeListener { _, isChecked ->
            viewModel.responseGetRecyclerItems.removeObservers(viewLifecycleOwner)
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
                                    setAlarmCardBoard(it)
                                }

                            }

                        }

                        is Result.Failure -> {
                            binding.progressBar.gone()
                        }

                    }

                }
                viewModel.getRecyclerDataByWasteTypeCardBoard()
            } else {
                alarmManager?.cancel(pendingIntentCardBoard)
            }

            viewModel.saveCardBoardReminderStatus(isChecked)
        }

        binding.switchCans.isChecked = viewModel.getCansReminderStatus()
        binding.switchCans.setOnCheckedChangeListener { _, isChecked ->
            viewModel.responseGetRecyclerItems.removeObservers(viewLifecycleOwner)
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
                                    setAlarmCans(it)
                                }

                            }

                        }

                        is Result.Failure -> {
                            binding.progressBar.gone()
                        }

                    }

                }
                viewModel.getRecyclerDataByWasteTypeCans()
            } else {
                alarmManager?.cancel(pendingIntentCans)
            }

            viewModel.saveCansReminderStatus(isChecked)
        }
        binding.switchGlass.isChecked = viewModel.getEmptyBottlesReminderStatus()
        binding.switchGlass.setOnCheckedChangeListener { _, isChecked ->
            viewModel.responseGetRecyclerItems.removeObservers(viewLifecycleOwner)
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
                                    setAlarmEmptyBottles(it)
                                }

                            }

                        }

                        is Result.Failure -> {
                            binding.progressBar.gone()
                        }

                    }

                }
                viewModel.getRecyclerDataByWasteTypeEmptyBottles()
            } else {
                alarmManager?.cancel(pendingIntentEmptyBottles)
            }

            viewModel.saveEmptyBottlesReminderStatus(isChecked)
        }
        binding.switchPetBottles.isChecked = viewModel.getPetReminderStatus()
        binding.switchPetBottles.setOnCheckedChangeListener { _, isChecked ->
            viewModel.responseGetRecyclerItems.removeObservers(viewLifecycleOwner)
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
                                    setAlarmPet(it)
                                }

                            }

                        }

                        is Result.Failure -> {
                            binding.progressBar.gone()
                        }

                    }

                }
                viewModel.getRecyclerDataByWasteTypePet()
            } else {
                alarmManager?.cancel(pendingIntentPetBottles)
            }

            viewModel.savePetReminderStatus(isChecked)
        }
        binding.switchPlastic.isChecked = viewModel.getPlasticReminderStatus()
        binding.switchPlastic.setOnCheckedChangeListener { _, isChecked ->
            viewModel.responseGetRecyclerItems.removeObservers(viewLifecycleOwner)
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
                                    setAlarmPlastic(it)
                                }

                            }

                        }

                        is Result.Failure -> {
                            binding.progressBar.gone()
                        }

                    }

                }
                viewModel.getRecyclerDataByWasteTypePlastic()
            } else {
                alarmManager?.cancel(pendingIntentPlastic)
            }

            viewModel.savePlasticReminderStatus(isChecked)
        }
    }

    private fun setAlarm(target: Calendar) {
        if(target.timeInMillis > System.currentTimeMillis()){
            alarmManager?.set(AlarmManager.RTC_WAKEUP, target.timeInMillis, pendingIntentNonBurnable)
            if (isAlarmSet()) {
                Toast.makeText(requireContext(), "Alarm set successfully", Toast.LENGTH_LONG).show()
                println("Alarm set success")
            }
        }

    }

    private fun isAlarmSet(): Boolean {
        val intent = Intent(
            context,
            NonBurnableNotification::class.java
        ) // Replace YourReceiver with the appropriate receiver class
        val pendingIntent =
            PendingIntent.getBroadcast(
                context, 0, intent,
                PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
            )
        return pendingIntent != null
    }
    private fun setAlarmBurnable(target: Calendar) {
        if(target.timeInMillis > System.currentTimeMillis()){
            alarmManager?.set(AlarmManager.RTC_WAKEUP, target.timeInMillis, pendingIntentBurnable)
            if (isAlarmSetBurnable()) {
                Toast.makeText(requireContext(), "Alarm set successfully", Toast.LENGTH_LONG).show()
                println("Alarm set success")
            }
        }

    }

    private fun isAlarmSetBurnable(): Boolean {
        val intent = Intent(
            context,
            BurnableNotification::class.java
        ) // Replace YourReceiver with the appropriate receiver class
        val pendingIntent =
            PendingIntent.getBroadcast(
                context, 2, intent,
                PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
            )
        return pendingIntent != null
    }


    private fun setAlarmCardBoard(target: Calendar) {
        if(target.timeInMillis > System.currentTimeMillis()){
            alarmManager?.set(AlarmManager.RTC_WAKEUP, target.timeInMillis, pendingIntentCardBoard)
            if (isAlarmSetCardBoard()) {
                Toast.makeText(requireContext(), "Alarm set successfully", Toast.LENGTH_LONG).show()
                println("Alarm set success")
            }
        }}
    private fun isAlarmSetCardBoard(): Boolean {
        val intent = Intent(
            context,
            CardBoardnotification::class.java
        ) // Replace YourReceiver with the appropriate receiver class
        val pendingIntent =
            PendingIntent.getBroadcast(
                context, 3, intent,
                PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
            )
        return pendingIntent != null
    }


    private fun setAlarmEmptyBottles(target: Calendar) {
        if(target.timeInMillis > System.currentTimeMillis()){
            alarmManager?.set(AlarmManager.RTC_WAKEUP, target.timeInMillis, pendingIntentEmptyBottles)
            if (isAlarmEmptyBottles()) {
                Toast.makeText(requireContext(), "Alarm set successfully", Toast.LENGTH_LONG).show()
                println("Alarm set success")
            }
        }}
    private fun isAlarmEmptyBottles(): Boolean {
        val intent = Intent(
            context,
            EmptyBottlesNotification::class.java
        ) // Replace YourReceiver with the appropriate receiver class
        val pendingIntent =
            PendingIntent.getBroadcast(
                context, 4, intent,
                PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
            )
        return pendingIntent != null
    }
    private fun setAlarmCans(target: Calendar) {
        if(target.timeInMillis > System.currentTimeMillis()){
            alarmManager?.set(AlarmManager.RTC_WAKEUP, target.timeInMillis, pendingIntentCans)
            if (isAlarmSetCans()) {
                Toast.makeText(requireContext(), "Alarm set successfully", Toast.LENGTH_LONG).show()
                println("Alarm set success")
            }
        }}
    private fun isAlarmSetCans(): Boolean {
        val intent = Intent(
            context,
            GlassNotification::class.java
        ) // Replace YourReceiver with the appropriate receiver class
        val pendingIntent =
            PendingIntent.getBroadcast(
                context, 5, intent,
                PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
            )
        return pendingIntent != null
    }
    private fun setAlarmPet(target: Calendar) {
        if(target.timeInMillis > System.currentTimeMillis()){
            alarmManager?.set(AlarmManager.RTC_WAKEUP, target.timeInMillis, pendingIntentPetBottles)
            if (isAlarmSetPet()) {
                Toast.makeText(requireContext(), "Alarm set successfully", Toast.LENGTH_LONG).show()
                println("Alarm set success")
            }
        }}
    private fun isAlarmSetPet(): Boolean {
        val intent = Intent(
            context,
            PetBottlesNotification::class.java
        ) // Replace YourReceiver with the appropriate receiver class
        val pendingIntent =
            PendingIntent.getBroadcast(
                context, 6, intent,
                PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
            )
        return pendingIntent != null
    }
    private fun setAlarmPlastic(target: Calendar) {
        if(target.timeInMillis > System.currentTimeMillis()){
            alarmManager?.set(AlarmManager.RTC_WAKEUP, target.timeInMillis, pendingIntentPlastic)
            if (isAlarmSetPlastic()) {
                Toast.makeText(requireContext(), "Alarm set successfully", Toast.LENGTH_LONG).show()
                println("Alarm set success")
            }
        }}
    private fun isAlarmSetPlastic(): Boolean {
        val intent = Intent(
            context,
            PlasticNotification::class.java
        ) // Replace YourReceiver with the appropriate receiver class
        val pendingIntent =
            PendingIntent.getBroadcast(
                context, 7, intent,
                PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
            )
        return pendingIntent != null
    }

}