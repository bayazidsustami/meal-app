package com.example.myapplication.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.myapplication.apiConfig.ApiService
import com.example.myapplication.data.models.ResponseMealsCategory
import com.example.myapplication.data.repositories.categories.CategoriesImpl
import com.example.myapplication.data.repositories.categories.CategoryRepository
import com.example.myapplication.ui.viewModels.main.MainViewModel
import com.example.myapplication.utils.Resource
import com.example.myapplication.utils.State
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val apiService: ApiService = mockk()

    private var useCase: CategoriesImpl = mockk()
    private var mainRepo: CategoryRepository = mockk()
    private val viewModel = MainViewModel(mainRepo)
    private val observer: Observer<Resource<ResponseMealsCategory>> = spyk()

    @Before
    fun setup(){
        viewModel.states.observeForever(observer)
    }

    @Test
    fun getCategoryTest(){


    }


}