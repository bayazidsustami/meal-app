package com.example.myapplication.data.repositories.randoms

import com.example.myapplication.apiConfig.ApiService
import com.example.myapplication.data.models.ResponseByFirstLetter
import com.example.myapplication.data.repositories.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RandomsImpl(private val apiService: ApiService): ApiHelper.Randoms {
    override suspend fun getRandoms(): Flow<ResponseByFirstLetter> =
        flow {
            val randoms = apiService.getRandomMeals()
            emit(randoms)
        }.flowOn(Dispatchers.IO)
}