package com.example.myapplication.data.repositories.lists

import com.example.myapplication.apiConfig.ApiService
import com.example.myapplication.data.models.ResponseListMeals
import com.example.myapplication.data.repositories.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ListsImpl(private val apiService: ApiService): ApiHelper.Lists {
    override suspend fun getList(params: Map<String, String>): Flow<ResponseListMeals> {
        return flow {
            val listed = apiService.getList(params)
            emit(listed)
        }.flowOn(Dispatchers.IO)
    }
}