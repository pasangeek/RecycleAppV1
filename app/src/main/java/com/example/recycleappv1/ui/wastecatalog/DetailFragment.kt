package com.example.recycleappv1.ui.wastecatalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recycleappv1.data.model.WasteGuideLinesData
import com.example.recycleappv1.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var _binding: FragmentDetailBinding
    private var item: WasteGuideLinesData? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = _binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val recycleWasteTemp = it.getParcelable<WasteGuideLinesData>("item")
            if (recycleWasteTemp != null) {
                item = recycleWasteTemp
                fetchData()
            }
        }

    }

    private fun fetchData() {
        item?.let { item ->
            _binding.wasteType.text = item.type
            _binding.remarks.text = item.remarks
            _binding.guidelines.text = item.guidelines


        }

    }
}