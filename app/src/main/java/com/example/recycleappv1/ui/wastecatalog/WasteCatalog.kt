package com.example.recycleappv1.ui.wastecatalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleappv1.common.Result
import com.example.recycleappv1.common.gone
import com.example.recycleappv1.common.show
import com.example.recycleappv1.data.model.WasteGuideLinesData
import com.example.recycleappv1.databinding.FragmentWasteCatalogBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

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


}