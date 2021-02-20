package com.example.myapplication.apiConfig

import com.example.myapplication.data.models.ResponseByFirstLetter
import com.example.myapplication.data.models.ResponseListMeals
import com.example.myapplication.data.models.ResponseFilters
import com.example.myapplication.data.models.ResponseMealsCategory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("search.php")
    suspend fun getListMealByFirstLetter(
        @Query("f") firstLetter: String
    ): ResponseByFirstLetter

    @GET("lookup.php")
    suspend fun getMealDetailById(
        @Query("i") idMeal: String
    ): ResponseByFirstLetter

    @GET("categories.php")
    suspend fun getListMealCategories(): ResponseMealsCategory

    @GET("list.php")
    suspend fun getList(
        @QueryMap paramsMap: Map<String, String>
    ): ResponseListMeals

    @GET("filter.php")
    suspend fun getFilterBy(
        @QueryMap paramsMap: Map<String, String>
    ): ResponseFilters
}