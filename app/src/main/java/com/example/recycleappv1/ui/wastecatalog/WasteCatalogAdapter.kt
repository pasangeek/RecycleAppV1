package com.example.recycleappv1.ui.wastecatalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleappv1.data.model.WasteGuideLinesData
import com.example.recycleappv1.databinding.CatalogListItemBinding

class WasteCatalogAdapter(
    var wasteGuideLinesData: ArrayList<WasteGuideLinesData>


):RecyclerView.Adapter<WasteCatalogAdapter.ProfileViewHolder>() {


    fun setFilteredList(contactProfileData: ArrayList<WasteGuideLinesData>) {
        this.wasteGuideLinesData = contactProfileData
        notifyDataSetChanged()
    }

    // Store the position of the currently expanded item
    private var expandedPosition: Int = RecyclerView.NO_POSITION

    inner class ProfileViewHolder(val binding: CatalogListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(
            CatalogListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = wasteGuideLinesData.size

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val wasteInfo = wasteGuideLinesData[position]

        holder.binding.type.text = wasteInfo.type
        holder.binding.description.text = wasteInfo.description
        holder.binding.guidelines.text = wasteInfo.guidelines
        holder.binding.remarks.text = wasteInfo.remarks



        // Check if this item is the currently expanded one
        val isExpanded = position == expandedPosition

        // Show/hide additional details based on the expanded state
        holder.binding.description.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.binding.guidelines.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.binding.remarks.visibility = if (isExpanded) View.VISIBLE else View.GONE
        // Set a click listener on the expanded layout
        holder.binding.constraintLayout.setOnClickListener {
            val prevExpandedPosition = expandedPosition
            expandedPosition = if (isExpanded) RecyclerView.NO_POSITION else position

            // Collapse the previously expanded item
            if (prevExpandedPosition != RecyclerView.NO_POSITION) {
                wasteGuideLinesData[prevExpandedPosition].isExpandable = false
                notifyItemChanged(prevExpandedPosition)

            }

            // Expand/Collapse the clicked item
            wasteInfo.isExpandable = !isExpanded
            notifyItemChanged(position)
        }
    }


}