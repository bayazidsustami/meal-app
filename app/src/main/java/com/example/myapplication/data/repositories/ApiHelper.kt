package com.example.myapplication.data.repositories

import com.example.myapplication.data.models.ResponseByFirstLetter
import com.example.myapplication.data.models.ResponseFilters
import com.example.myapplication.data.models.ResponseListMeals
import com.example.myapplication.data.models.ResponseMealsCategory
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    interface Categories{
        suspend fun getAllCategories(): Flow<ResponseMealsCategory>
    }

    interface Lists{
        suspend fun getList(params: Map<String, String>): Flow<ResponseListMeals>
    }

    interface Filters{
        suspend fun getFiltersBy(params: Map<String, String>): Flow<ResponseFilters>
    }

    interface Search{
        suspend fun getSearch(params: Map<String, String>): Flow<ResponseByFirstLetter>
        suspend fun getMealsById(id: String): Flow<ResponseByFirstLetter>
    }

    interface Randoms{
        suspend fun getRandoms(): Flow<ResponseByFirstLetter>
    }

}