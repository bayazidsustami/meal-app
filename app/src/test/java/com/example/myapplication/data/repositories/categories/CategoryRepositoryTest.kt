package com.example.myapplication.data.repositories.categories

import com.example.myapplication.apiConfig.ApiService
import com.example.myapplication.data.models.ResponseMealsCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import kotlin.Exception

class CategoryRepositoryTest{
    @Mock
    var dataSource: CategoriesImpl? = null

    @Mock
    private val apiService: ApiService? = null

    var repository: CategoryRepository? = null

    var categories: Flow<ResponseMealsCategory>? = null

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
        repository = CategoryRepository.instance.apply {
            init(CategoriesImpl(apiService!!))
        }
    }

    @Test
    fun `success get all categories`(){
        runBlocking {
            `when`(dataSource?.getAllCategories()).thenReturn(categories)
            repository?.getAllCategories()
            verify(dataSource, never())?.getAllCategories()
        }
    }

    @Test
    fun `return null when failed`(){
        runBlocking {
            `when`(dataSource?.getAllCategories()).thenReturn(null)
            repository?.getAllCategories()

            verify(dataSource, never())?.getAllCategories()
        }
    }

    @Test
    fun `should throw exception`(){
        runBlocking {
            `when`(dataSource?.getAllCategories()).thenAnswer{throw Exception()}

            try {
                repository?.getAllCategories()
            }catch (e: Exception){
            }
        }
    }


}