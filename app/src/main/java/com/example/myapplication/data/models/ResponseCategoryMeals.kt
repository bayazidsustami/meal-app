package com.example.myapplication.data.models

import com.squareup.moshi.Json

data class ResponseCategoryMeals(

	@Json(name="meals")
	val meals: List<MealsCategoryItem?>? = null
)

data class MealsCategoryItem(

	@Json(name="strCategory")
	val strCategory: String? = null
)
