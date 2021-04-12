package com.example.myapplication.ui.viewModels.filters

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.myapplication.TestCoroutineRule
import com.example.myapplication.data.models.ResponseFilters
import com.example.myapplication.data.repositories.filters.FiltersRepository
import com.example.myapplication.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import okio.IOException
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyMap
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.stub
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FilterViewModelTest{
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository : FiltersRepository
    @Mock
    private lateinit var resultObserver: Observer<Resource<ResponseFilters>>
    @Mock
    private lateinit var response: ResponseFilters

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        repository = Mockito.mock(FiltersRepository::class.java)
        response = ResponseFilters()
    }

    @Test
    fun `should given loading when first call`(){
        testCoroutineRule.runBlockingTest {
            //given
            val flow: Flow<ResponseFilters> = flowOf()

            //when
            `when`(repository.getFiltersBy(anyMap())).thenReturn(flow)

            val viewModel = FilterViewModel(repository)
            viewModel.getDataFilter.observeForever(resultObserver)
            viewModel.setDataFilter(anyMap())

            //then
            verify(repository).getFiltersBy(anyMap())
            verify(resultObserver).onChanged(
                Resource.onLoading(null)
            )
            viewModel.getDataFilter.removeObserver(resultObserver)
        }
    }

    @Test
    fun `should given success when first success get data`(){
        testCoroutineRule.runBlockingTest {
            //given
            val flow: Flow<ResponseFilters> = flow{
                emit(ResponseFilters())
            }

            //when
            `when`(repository.getFiltersBy(anyMap())).thenReturn(flow)

            val viewModel = FilterViewModel(repository)
            viewModel.getDataFilter.observeForever(resultObserver)
            viewModel.setDataFilter(anyMap())

            //then
            verify(repository).getFiltersBy(anyMap())
            verify(resultObserver).onChanged(
                Resource.onSuccess(ResponseFilters())
            )
            viewModel.getDataFilter.removeObserver(resultObserver)
        }
    }

    @Test
    fun `should given error when exception`(){
        testCoroutineRule.runBlockingTest {
            //given
            val flow: Flow<ResponseFilters> = flowOf()

            //when
            repository.stub {
                onBlocking { getFiltersBy(anyMap()) } doAnswer {flow.catch { Throwable() }}
            }

            val viewModel = FilterViewModel(repository)
            viewModel.getDataFilter.observeForever(resultObserver)
            viewModel.setDataFilter(anyMap())

            //then
            verify(repository).getFiltersBy(anyMap())
            try {

            }catch (e: IOException){
                verify(flow)
                    .catch { ex -> Resource.onFailed(null, ex.message) }            }
            viewModel.getDataFilter.removeObserver(resultObserver)
        }
    }
}