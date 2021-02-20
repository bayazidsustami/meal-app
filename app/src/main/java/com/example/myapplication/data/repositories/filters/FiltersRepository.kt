package com.example.myapplication.data.repositories.filters

import com.example.myapplication.data.models.ResponseFilters
import com.example.myapplication.data.repositories.BaseRepositories
import kotlinx.coroutines.flow.Flow

class FiltersRepository private constructor()
    : BaseRepositories<FiltersImpl>(){

    /**
     * filter by ingridients using key=  i
     * filter by category using key = c
     * filter by area using key = a
     */

    suspend fun getFiltersBy(params: Map<String, String>): Flow<ResponseFilters>?{
        return  remoteDataStore?.getFiltersBy(params)
    }

    companion object{
        val instance by lazy { FiltersRepository() }
    }
}