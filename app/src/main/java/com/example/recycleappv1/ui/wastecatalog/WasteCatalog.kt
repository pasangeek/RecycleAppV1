package com.example.recycleappv1.ui.wastecatalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleappv1.common.Result
import com.example.recycleappv1.common.gone
import com.example.recycleappv1.common.show
import com.example.recycleappv1.data.model.WasteGuideLinesData
import com.example.recycleappv1.databinding.FragmentWasteCatalogBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class WasteCatalog : Fragment() {
private lateinit var _binding:FragmentWasteCatalogBinding
private  lateinit var adapter: WasteCatalogAdapter
private  lateinit var wasteCatalogList: ArrayList<WasteGuideLinesData>
  val viewModel: WasteCatalogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWasteCatalogBinding.inflate(inflater, container, false)
        val root:View =_binding.root

        return  root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.lifecycleOwner = this
        _binding.catalogVM = viewModel
        _binding.catalogsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            hasFixedSize()
        }
       wasteCatalogList = ArrayList()
      adapter = WasteCatalogAdapter(wasteCatalogList)
      _binding.catalogsRecyclerView.adapter = adapter

        // showTodaysWasteDescription()
       initObservers();
       viewModel.getWasteCatalogData()

        // Set up the search functionality using the SearchView
       _binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText ?: "")
                return true
            }
        })

    }






    private fun initObservers(){


        viewModel.responseGetWasteCatalog.observe(viewLifecycleOwner){
            when (it) {
                is Result.Loading->{
                    _binding.progressBar.show()
                }
                is Result.Success<*>->{
                    _binding.progressBar.gone()
                    _binding.catalogsRecyclerView.adapter = WasteCatalogAdapter(
                        it.result as ArrayList<WasteGuideLinesData>
                    )
                }

                is Result.Failure->{
                    _binding.progressBar.gone()
                    Snackbar.make(_binding.root, it.error, Snackbar.LENGTH_LONG).show()
                }

            }

        }

}
    // Filter the list based on the search query
    private fun filter(query: String) {
        val filteredList = java.util.ArrayList<WasteGuideLinesData>()
        for (i in wasteCatalogList) {
            if (i.type?.lowercase(Locale.ROOT)?.contains(query) == true) {
                filteredList.add(i)
            }
        }
// Update the adapter with the filtered list or show a message
        if (filteredList.isEmpty()) {
            // Use the context from the parent activity or fragment
            val context = requireContext() // or activityContext depending on your use case
            Toast.makeText(context, "No Data found", Toast.LENGTH_SHORT).show()
        } else {
            adapter.setFilteredList(filteredList)
        }
    }

}