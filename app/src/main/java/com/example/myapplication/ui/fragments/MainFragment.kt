package com.example.myapplication.ui.fragments

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.repositories.categories.CategoryRepository
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.ui.baseView.BaseFragment
import com.example.myapplication.ui.viewModels.main.MainViewModel
import com.example.myapplication.ui.viewModels.main.MainViewModelFactory
import com.example.myapplication.utils.State

class MainFragment: BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val factory by lazy { MainViewModelFactory(CategoryRepository.instance) }
    private val viewModels by lazy { ViewModelProvider(this, factory).get(MainViewModel::class.java) }

    override fun setupView(binds: FragmentMainBinding?) {

        binds?.btnTest?.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_filtersFragment)
        }

        binds?.btnTestcategory?.setOnClickListener {
            viewModels.loadAllCategories()
        }

        stateCategory()

    }

    private fun stateCategory(){
        viewModels.states.observe(this, { data ->
            when(data.status){
                State.SUCCESS -> {
                    Log.d(MainViewModel::class.java.simpleName, "data = ${data.data}")
                }
                State.LOADING -> {
                    Log.d(MainViewModel::class.java.simpleName, "onLoading")
                }
                State.FAILED -> {
                    Log.d(MainViewModel::class.java.simpleName, "onError")
                }
            }
        })
    }

    private fun stateListCategory(){
        viewModels.list.observe(this, { data ->
            when(data.status){
                State.SUCCESS -> {
                    Log.d(MainViewModel::class.java.simpleName, " category -->data = ${data.data}")
                }
                State.LOADING -> {
                    Log.d(MainViewModel::class.java.simpleName, "category --> onLoading")
                }
                State.FAILED -> {
                    Log.d(MainViewModel::class.java.simpleName, "category --> onError")
                }
            }
        })
    }
}