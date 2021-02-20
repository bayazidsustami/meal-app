package com.example.myapplication.data.repositories.search

import com.example.myapplication.apiConfig.ApiService
import com.example.myapplication.data.models.ResponseByFirstLetter
import com.example.myapplication.data.repositories.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchImpl(private val apiService: ApiService): ApiHelper.Search {
    override suspend fun getSearch(params: Map<String, String>): Flow<ResponseByFirstLetter> {
        return flow {
            val searches = apiService.getSearch(params)
            emit(searches)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getMealsById(id: String): Flow<ResponseByFirstLetter> {
        return flow {
            val mealsById = apiService.getMealDetailById(id)
            emit(mealsById)
        }.flowOn(Dispatchers.IO)
    }
}