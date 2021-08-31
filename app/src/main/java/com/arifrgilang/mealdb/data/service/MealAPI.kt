package com.arifrgilang.mealdb.data.service

import com.arifrgilang.mealdb.domain.model.MealsDetailDomainModel
import com.arifrgilang.mealdb.domain.model.MealsDomainModel
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by arifrgilang on 8/31/2021
 */
interface MealAPI {
    @GET("filter.php")
    suspend fun getFilteredMeals(
        @Query("c") mealType: String
    ): MealsDomainModel

    @GET("lookup.php")
    suspend fun getMealDetail(
        @Query("i") idMeal: String
    ): MealsDetailDomainModel
}