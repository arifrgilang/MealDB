package com.arifrgilang.mealdb.domain

import com.arifrgilang.mealdb.domain.model.MealsDetailDomainModel
import com.arifrgilang.mealdb.domain.model.MealsDomainModel


/**
 * Created by arifrgilang on 8/31/2021
 */
interface MealRepository {
    suspend fun getFilteredMeals(
        mealType: String
    ): MealsDomainModel

    suspend fun getMealDetail(
        idMeal: String
    ): MealsDetailDomainModel
}