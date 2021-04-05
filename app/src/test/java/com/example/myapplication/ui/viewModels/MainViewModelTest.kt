package com.example.myapplication.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.data.models.ResponseMealsCategory
import com.example.myapplication.data.repositories.categories.CategoryRepository
import com.example.myapplication.ui.viewModels.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

//TODO do unit test
class MainViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    var repository: CategoryRepository? = null

    var viewModel: MainViewModel? = null


    @ExperimentalCoroutinesApi
    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(repository!!)
        Dispatchers.setMain(Dispatchers.IO)
    }

    @ExperimentalCoroutinesApi
    @After
    fun finish(){
        Dispatchers.resetMain()
    }

    @Test
    fun `should loading when first call`(){
        runBlocking {
            val categoryList = mutableListOf<ResponseMealsCategory>()

            val getAllCategory = repository?.getAllCategories()

            getAllCategory?.toList(categoryList)

            assert(categoryList.first() == viewModel?.states?.value?.data)
        }
    }

}