package com.example.myapplication.data.repositories.lists

import com.example.myapplication.data.models.ResponseListMeals
import com.example.myapplication.data.repositories.BaseRepositories
import kotlinx.coroutines.flow.Flow

class ListsRepository private constructor() : BaseRepositories<ListsImpl>(){

    suspend fun getLists(params: Map<String, String>): Flow<ResponseListMeals>?{
        return remoteDataStore?.getList(params)
    }

    companion object{
        val instance by lazy { ListsRepository() }
    }
}