package com.example.recycleappv1.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleappv1.data.model.RecycleItemsData
import com.example.recycleappv1.databinding.RecycleItemBinding


class RecyclerItemAdapter (private val data: List<RecycleItemsData>) :
    RecyclerView.Adapter<RecyclerItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder( RecycleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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
            binding.txtWasteType.text = item.wasteType
           binding.txtWasteDescription.text=item.description
        }
    }
}