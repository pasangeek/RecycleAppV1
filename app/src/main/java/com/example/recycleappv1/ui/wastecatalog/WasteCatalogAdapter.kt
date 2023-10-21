package com.example.recycleappv1.ui.wastecatalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleappv1.data.model.WasteGuideLinesData
import com.example.recycleappv1.databinding.CatalogListItemBinding

class WasteCatalogAdapter(
    private val wasteGuideLinesData: ArrayList<WasteGuideLinesData>
) : RecyclerView.Adapter<WasteCatalogAdapter.CatalogDetailViewHolder>() {
    var onItemClickedListener: OnItemClickedListener? = null

    interface OnItemClickedListener {
        fun onItemClicked(item: WasteGuideLinesData)
    }


    inner class CatalogDetailViewHolder(val binding: CatalogListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WasteGuideLinesData){
       binding.type.text = item.type
           // Glide.with(itemView.context).load(item.typeOfWasteImage).into(binding.imageView4)
            itemView.setOnClickListener {
                onItemClickedListener?.onItemClicked(item)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogDetailViewHolder {
        return CatalogDetailViewHolder(
            CatalogListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = wasteGuideLinesData.size

    override fun onBindViewHolder(holder: CatalogDetailViewHolder, position: Int) {
        val wasteInfo: WasteGuideLinesData = wasteGuideLinesData[position]
        val item = wasteGuideLinesData[position]
        holder.bind(item)

    }
}


