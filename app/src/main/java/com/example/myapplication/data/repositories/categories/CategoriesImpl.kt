package com.example.myapplication.data.repositories.categories

import com.example.myapplication.apiConfig.ApiService
import com.example.myapplication.data.models.ResponseCategoryMeals
import com.example.myapplication.data.models.ResponseMealsCategory
import com.example.myapplication.data.repositories.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CategoriesImpl(private val apiService: ApiService): ApiHelper.Categories {
    override suspend fun getAllCategories(): Flow<ResponseMealsCategory> {
        return flow {
            val categories = apiService.getListMealCategories()
            emit(categories)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getCategoryMeals(category: String): Flow<ResponseCategoryMeals> {
        return flow {
            val listCategoryMeals = apiService.getListCategoryMeals(category)
            emit(listCategoryMeals)
        }.flowOn(Dispatchers.IO)
    }


}