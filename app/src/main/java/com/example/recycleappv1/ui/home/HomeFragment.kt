package com.example.recycleappv1.ui.home

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleappv1.common.Result
import com.example.recycleappv1.common.gone
import com.example.recycleappv1.common.show
import com.example.recycleappv1.data.model.RecycleItemsData
import com.example.recycleappv1.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {


    private lateinit var _binding: FragmentHomeBinding

    // This property is only valid between onCreateView and
    // onDestroyView.

    val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = _binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.lifecycleOwner = this
        _binding.homeVM = viewModel
_binding.recyclerView.apply {
    layoutManager = LinearLayoutManager(context)
    hasFixedSize()
}
        initObservers();
        viewModel.getTodayRecyclerItems()
    }


    private fun initObservers(){


        viewModel.responseGetRecyclerItems.observe(viewLifecycleOwner){
            when (it) {
                is Result.Loading->{
                    _binding.progressBar.show()
                }
                is Result.Success<*>->{
                    _binding.progressBar.gone()
                    _binding.recyclerView.adapter = RecyclerItemAdapter(it.result as List<RecycleItemsData>)
                }

                is Result.Failure->{
                    _binding.progressBar.gone()
                    Snackbar.make(_binding.root, it.error, Snackbar.LENGTH_LONG).show()
                }

            }

    }



    }
}