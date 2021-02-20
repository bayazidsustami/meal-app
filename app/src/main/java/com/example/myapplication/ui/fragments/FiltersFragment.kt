package com.example.myapplication.ui.fragments

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.repositories.filters.FiltersRepository
import com.example.myapplication.databinding.FragmentFiltersBinding
import com.example.myapplication.ui.baseView.BaseFragment
import com.example.myapplication.ui.viewModels.filters.FilterViewModel
import com.example.myapplication.ui.viewModels.filters.FilterViewModelFactory
import com.example.myapplication.utils.State

class FiltersFragment : BaseFragment<FragmentFiltersBinding>(FragmentFiltersBinding::inflate)  {

    private val factory by lazy { FilterViewModelFactory(FiltersRepository.instance) }
    private val viewModel by lazy { ViewModelProvider(this, factory).get(FilterViewModel::class.java) }

    override fun setupView(binds: FragmentFiltersBinding?) {
        binds?.btnTest?.setOnClickListener {
            val key = binds.etParamsKey.text.toString()
            val value = binds.etParamsValue.text.toString()
            val paramsMap = mapOf(
                key to value
            )
            viewModel.setDataFilter(paramsMap)
        }

        viewModel.getDataFilter.observe(viewLifecycleOwner, {data ->
            when(data.status){
                State.SUCCESS -> {
                    Log.d(FilterViewModel::class.java.simpleName, "data  --> ${data.data}")
                    binds?.tvStatus?.text = "Success"
                }
                State.FAILED -> {
                    Log.d(FilterViewModel::class.java.simpleName, "onError")
                    binds?.tvStatus?.text = "Error"
                }
                State.LOADING -> {
                    Log.d(FilterViewModel::class.java.simpleName, "onLoading")
                    binds?.tvStatus?.text = "Loading"
                }
            }
        })
    }

}