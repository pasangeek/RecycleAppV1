package com.example.recycleappv1.ui.wastecatalog

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recycleappv1.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WasteCatalog : Fragment() {

    companion object {
        fun newInstance() = WasteCatalog()
    }

    private lateinit var viewModel: WasteCatalogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_waste_catalog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WasteCatalogViewModel::class.java)
        // TODO: Use the ViewModel
    }

}