package com.example.myapplication.ui.viewModels.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.repositories.categories.CategoryRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val mainRepository: CategoryRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(mainRepository) as T

        throw IllegalArgumentException()
    }
}