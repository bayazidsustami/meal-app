package com.example.myapplication

import android.app.Application
import com.example.myapplication.apiConfig.ApiBuilder
import com.example.myapplication.apiConfig.ApiService
import com.example.myapplication.data.repositories.categories.CategoriesImpl
import com.example.myapplication.data.repositories.categories.CategoryRepository
import com.example.myapplication.data.repositories.filters.FiltersImpl
import com.example.myapplication.data.repositories.filters.FiltersRepository

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        val apiService = ApiBuilder.createService(ApiService::class.java)
        CategoryRepository.instance.apply {
            init(
                CategoriesImpl(apiService)
            )
        }
        FiltersRepository.instance.apply {
            init(
                FiltersImpl(apiService)
            )
        }
    }
}