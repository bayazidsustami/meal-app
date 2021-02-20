package com.example.myapplication.data.models

data class ResponseMealsCategory(
	val categories: List<CategoriesItem?>? = null
)

data class CategoriesItem(
	val strCategory: String? = null,
	val strCategoryDescription: String? = null,
	val idCategory: String? = null,
	val strCategoryThumb: String? = null
)

