package com.example.myapplication.data.repositories.filters

import com.example.myapplication.apiConfig.ApiService
import com.example.myapplication.data.models.ResponseFilters
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

class FiltersRepositoryTest{
    @Mock
    var dataSource: FiltersImpl? = null

    @Mock
    var repository: FiltersRepository? = null

    @Mock
    var filters: Flow<ResponseFilters>? = null

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        repository = Mockito.mock(FiltersRepository::class.java)
        dataSource = Mockito.mock(FiltersImpl::class.java)
    }

    @Test
    fun `success get data filters`(){
        runBlocking {

            val params = mapOf(
                "c" to "Seafood"
            )

            `when`(repository?.getFiltersBy(params)).thenReturn(filters)
            dataSource?.getFiltersBy(params)

            verify(repository, never())?.getFiltersBy(params)

            assertEquals(filters, repository?.getFiltersBy(params))
        }
    }

    @Test
    fun `return null when loading`(){
        runBlocking {

            val params = mapOf(
                "c" to "Seafood"
            )

            `when`(repository?.getFiltersBy(params)).thenReturn(null)
            dataSource?.getFiltersBy(params)

            verify(repository, never())?.getFiltersBy(params)

            assertEquals(null, repository?.getFiltersBy(params))
        }
    }

    @Test
    fun `return exception when errors`(){
        runBlocking {

            val params = mapOf(
                "c" to "Seafood"
            )

            `when`(repository?.getFiltersBy(params)).thenAnswer { throw Exception() }
            dataSource?.getFiltersBy(params)

            try {
                verify(repository, never())?.getFiltersBy(params)
            }catch (e: Exception){
                assertEquals(e, repository?.getFiltersBy(params))
            }
        }
    }
}