package com.example.myapplication.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.myapplication.TestCoroutineRule
import com.example.myapplication.data.models.ResponseMealsCategory
import com.example.myapplication.data.repositories.categories.CategoryRepository
import com.example.myapplication.ui.viewModels.main.MainViewModel
import com.example.myapplication.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import okio.IOException
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.stub
import org.mockito.kotlin.verify
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository: CategoryRepository

    @Mock
    private lateinit var resultObserver: Observer<Resource<ResponseMealsCategory>>

    @Mock
    private lateinit var responseMealsCategory: ResponseMealsCategory

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        repository = Mockito.mock(CategoryRepository::class.java)
        responseMealsCategory = ResponseMealsCategory()
    }

    @Test
    fun `should given loading when first call`(){
        testCoroutineRule.runBlockingTest {
            //given
            val flow: Flow<ResponseMealsCategory> = flowOf()

            //when
            `when`(repository.getAllCategories()).thenReturn(flow)

            val viewModel = MainViewModel(repository)
            viewModel.states.observeForever(resultObserver)
            viewModel.loadAllCategories()

            //then
            verify(repository).getAllCategories()
            verify(resultObserver).onChanged(
                Resource.onLoading(null)
            )
            viewModel.states.removeObserver(resultObserver)
        }
    }

    @Test
    fun `should given success when success get data`(){
        testCoroutineRule.runBlockingTest {
            //given
            val flow: Flow<ResponseMealsCategory> = flow {
                emit(ResponseMealsCategory())
            }

            //when
            `when`(repository.getAllCategories()).thenReturn(flow)

            val viewModel = MainViewModel(repository)
            viewModel.states.observeForever(resultObserver)
            viewModel.loadAllCategories()

            //then
            verify(repository).getAllCategories()
            verify(resultObserver).onChanged(
                Resource.onSuccess(responseMealsCategory)
            )
            viewModel.states.removeObserver(resultObserver)
        }
    }

    @Test
    fun `should given error when exception`(){
        testCoroutineRule.runBlockingTest {
            //given
            val flow: Flow<ResponseMealsCategory> = flow {
                emit(ResponseMealsCategory())
            }

            //when
            repository.stub {
                onBlocking {getAllCategories()} doAnswer {throw IOException()}
            }

            val viewModel = MainViewModel(repository)
            viewModel.states.observeForever(resultObserver)
            viewModel.loadAllCategories()

            //then
            verify(repository).getAllCategories()
            try {

            }catch (e: IOException){
                verify(flow).catch { ex->
                    Resource.onFailed(data = null, ex.message)
                }
            }
            viewModel.states.removeObserver(resultObserver)
        }
    }
}