package com.example.myapplication.data.repositories

import com.example.myapplication.data.models.ResponseCategoryMeals
import com.example.myapplication.data.models.ResponseFilters
import com.example.myapplication.data.models.ResponseMealsCategory
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    interface Categories{
        suspend fun getAllCategories(): Flow<ResponseMealsCategory>
        suspend fun getCategoryMeals(category: String): Flow<ResponseCategoryMeals>
    }

    interface Filters{
        suspend fun getFiltersBy(params: Map<String, String>): Flow<ResponseFilters>
    }

}