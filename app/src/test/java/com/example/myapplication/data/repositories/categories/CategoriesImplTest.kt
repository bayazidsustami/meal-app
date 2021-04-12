package com.example.myapplication.data.repositories.categories

import com.example.myapplication.data.models.ResponseMealsCategory
import com.example.myapplication.data.repositories.ApiHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.stub
import org.mockito.kotlin.verify

class CategoriesImplTest{

    @Mock
    private lateinit var dataSource: ApiHelper.Categories

    @Mock
    private lateinit var categoriesImpl: CategoriesImpl

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        categoriesImpl = Mockito.mock(CategoriesImpl::class.java)
    }

    @Test
    fun `test first call`(){
        runBlocking {

            val flow: Flow<ResponseMealsCategory> = flowOf()

            dataSource.stub {
                onBlocking { getAllCategories() } doAnswer {flow}
            }
            categoriesImpl.getAllCategories()

            verify(categoriesImpl).getAllCategories()
        }
    }

    @Test
    fun `test emit call`(){
        runBlocking {

            val flow: Flow<ResponseMealsCategory> = flow{
                emit(ResponseMealsCategory())
            }

            dataSource.stub {
                onBlocking { getAllCategories() } doAnswer {flow}
            }
            categoriesImpl.getAllCategories()

            verify(categoriesImpl).getAllCategories()
        }
    }
}