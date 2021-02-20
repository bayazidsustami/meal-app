package com.example.myapplication.data.repositories.filters

import com.example.myapplication.apiConfig.ApiService
import com.example.myapplication.data.models.ResponseFilters
import com.example.myapplication.data.repositories.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FiltersImpl(private val apiService: ApiService): ApiHelper.Filters {

    override suspend fun getFiltersBy(params: Map<String, String>): Flow<ResponseFilters> {
        return flow {
            emit(apiService.getFilterBy(params))
        }.flowOn(Dispatchers.IO)
    }
}