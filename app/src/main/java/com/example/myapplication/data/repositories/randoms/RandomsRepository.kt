package com.example.myapplication.data.repositories.randoms

import com.example.myapplication.data.models.ResponseByFirstLetter
import com.example.myapplication.data.repositories.BaseRepositories
import kotlinx.coroutines.flow.Flow

class RandomsRepository private constructor(): BaseRepositories<RandomsImpl>(){

    suspend fun getRandoms(): Flow<ResponseByFirstLetter>? = remoteDataStore?.getRandoms()

    companion object{
        val instance by lazy { RandomsRepository() }
    }
}