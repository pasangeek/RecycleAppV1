package com.example.recycleappv1.ui.wastecatalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleappv1.data.model.WasteGuideLinesData
import com.example.recycleappv1.databinding.CatalogListItemBinding

class WasteCatalogAdapter(
    var wasteGuideLinesData: ArrayList<WasteGuideLinesData>
) : RecyclerView.Adapter<WasteCatalogAdapter.ProfileViewHolder>() {

    private var filteredWasteGuideLinesData: ArrayList<WasteGuideLinesData> = ArrayList()

    init {
        filteredWasteGuideLinesData.addAll(wasteGuideLinesData)
    }

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



    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val wasteInfo = filteredWasteGuideLinesData[position]

        holder.binding.type.text = wasteInfo.type
        holder.binding.description.text = wasteInfo.description
        holder.binding.guidelines.text = wasteInfo.guidelines

        holder.binding.description.visibility = View.VISIBLE
        holder.binding.guidelines.visibility = View.VISIBLE


    }
    override fun getItemCount(): Int {

        return filteredWasteGuideLinesData.size
    }
    fun setFilteredList(searchQuery: ArrayList<WasteGuideLinesData>) {
        filteredWasteGuideLinesData.clear()

        if (searchQuery.isEmpty()) {
            filteredWasteGuideLinesData.addAll(wasteGuideLinesData)
        } else {
            val lowerCaseQuery = searchQuery[0].type?.toLowerCase() ?: ""
            filteredWasteGuideLinesData.addAll(
                wasteGuideLinesData.filter { it.type?.toLowerCase()!!.contains(lowerCaseQuery) }
            )
        }

        notifyDataSetChanged()
    }
}