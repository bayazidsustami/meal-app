package com.example.myapplication.ui.viewModels.filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.repositories.filters.FiltersRepository

@Suppress("UNCHECKED_CAST")
class FilterViewModelFactory(private val repository: FiltersRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilterViewModel::class.java))
            return FilterViewModel(repository) as T

        throw IllegalArgumentException()
    }
}