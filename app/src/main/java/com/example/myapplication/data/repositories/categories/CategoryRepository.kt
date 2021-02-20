package com.example.myapplication.data.repositories.categories

import com.example.myapplication.data.models.ResponseMealsCategory
import com.example.myapplication.data.repositories.BaseRepositories
import kotlinx.coroutines.flow.Flow

class CategoryRepository private constructor()
    : BaseRepositories<CategoriesImpl>(){

    suspend fun getAllCategories(): Flow<ResponseMealsCategory>?{
        return remoteDataStore?.getAllCategories()
    }

    companion object{
        val instance by lazy { CategoryRepository() }
    }
}