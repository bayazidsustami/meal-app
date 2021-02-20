package com.example.myapplication.data.repositories.categories

import com.example.myapplication.data.models.ResponseListMeals
import com.example.myapplication.data.models.ResponseMealsCategory
import com.example.myapplication.data.repositories.BaseRepositories
import kotlinx.coroutines.flow.Flow

class CategoryRepository public constructor()
    : BaseRepositories<CategoriesImpl>(){

    suspend fun getAllCategories(): Flow<ResponseMealsCategory>?{
        return remoteDataStore?.getAllCategories()
    }

    suspend fun getListMealCategories(category: String): Flow<ResponseListMeals>?{
        return remoteDataStore?.getList(category)
    }

    companion object{
        val instance by lazy { CategoryRepository() }
    }
}