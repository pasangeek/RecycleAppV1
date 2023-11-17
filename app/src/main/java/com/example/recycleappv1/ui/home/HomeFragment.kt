package com.example.recycleappv1.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleappv1.common.BaseFragment
import com.example.recycleappv1.common.Result
import com.example.recycleappv1.common.gone
import com.example.recycleappv1.common.show
import com.example.recycleappv1.data.model.RecycleItemsData
import com.example.recycleappv1.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(){


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

        // Request notification permission if required
        requestNotificationPermission()
        _binding.lifecycleOwner = this
        _binding.homeVM = viewModel

        val layoutMan = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            layoutMan.orientation
        )

        _binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(dividerItemDecoration)
            hasFixedSize()
        }
        // showTodaysWasteDescription()
        // Initialize observers to observe changes in ViewModel data
        initObservers();
        // Set the city text using saved city information from the ViewModel
        _binding.txtCity.text = viewModel.getSavedCity()
        // Fetch today's waste data for the saved city
        viewModel.getTodayWasteData(viewModel.getSavedCity()?:"")


    }


    private fun initObservers() {


        viewModel.responseGetRecyclerItems.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    _binding.progressBar.show()
                }

                is Result.Success<*> -> {
                    _binding.progressBar.gone()
                    // Set the adapter for the RecyclerView with retrieved data
                    _binding.recyclerView.adapter =
                        RecyclerItemAdapter(it.result as List<RecycleItemsData>)
                    Log.d(TAG, "Data loaded successfully.")
                }

                is Result.Failure -> {
                    _binding.progressBar.gone()
                    // Show a Snackbar in case of a failure with an error message
                    Snackbar.make(_binding.root, it.error, Snackbar.LENGTH_LONG).show()

                }

            }

        }


    }


}