package com.example.recycleappv1.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycleappv1.common.convertToReadable
import com.example.recycleappv1.common.dateToDayOfWeek
import com.example.recycleappv1.data.model.RecycleItemsData
import com.example.recycleappv1.databinding.RecycleItemBinding


class RecyclerItemAdapter(private val data: List<RecycleItemsData>) :
    RecyclerView.Adapter<RecyclerItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecycleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(private val binding: RecycleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecycleItemsData) {
            // Set the day of the week
            binding.txtDay.text = item.date?.dateToDayOfWeek()
            Log.d(TAG, "Waste type: ${item.wasteType}, Description: ${item.description}")
            binding.txtWasteType.text = item.wasteType
            // Set waste type and description
            binding.txtWasteDescription.text = item.description
            // Set date in readable format
        binding.txtDate.text = item.date?.convertToReadable()
            Glide.with(itemView.context).load(item.iconUrl).into(binding.imgIcon)
        }
    }

    companion object {
        private const val TAG = "RecyclerItemAdapter"
    }
}