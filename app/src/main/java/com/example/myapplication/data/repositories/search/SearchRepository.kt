package com.example.myapplication.data.repositories.search

import com.example.myapplication.data.models.ResponseByFirstLetter
import com.example.myapplication.data.repositories.BaseRepositories
import kotlinx.coroutines.flow.Flow

class SearchRepository private constructor() : BaseRepositories<SearchImpl>() {

    suspend fun getSearch(params: Map<String, String>): Flow<ResponseByFirstLetter>? =
        remoteDataStore?.getSearch(params)

    suspend fun getMealById(id: String): Flow<ResponseByFirstLetter>? =
        remoteDataStore?.getMealsById(id)

    companion object {
        val instance by lazy { SearchRepository() }
    }
}