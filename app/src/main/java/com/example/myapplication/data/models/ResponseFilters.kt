package com.example.myapplication.data.models

import com.squareup.moshi.Json

data class ResponseFilters(

	@Json(name="meals")
	val meals: List<MealsItemFilters?>? = null
)

data class MealsItemFilters(

	@Json(name="strMealThumb")
	val strMealThumb: String? = null,

	@Json(name="idMeal")
	val idMeal: String? = null,

	@Json(name="strMeal")
	val strMeal: String? = null
)
