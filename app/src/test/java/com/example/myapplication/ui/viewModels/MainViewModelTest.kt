package com.example.myapplication.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.apiConfig.ApiService
import com.example.myapplication.data.repositories.categories.CategoriesImpl
import com.example.myapplication.data.repositories.categories.CategoryRepository
import com.example.myapplication.ui.viewModels.main.MainViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var repository: CategoryRepository

    lateinit var categoryImpl: CategoriesImpl

    lateinit var viewModel: MainViewModel


    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(repository)
    }

    @Test
    fun getCategoryTest(){

    }
}