package com.arifrgilang.mealdb.domain.interactor

import com.arifrgilang.mealdb.domain.MealRepository
import com.arifrgilang.mealdb.domain.model.MealsDomainModel


/**
 * Created by arifrgilang on 8/31/2021
 */
interface GetFilteredMeals {
    suspend fun execute(mealCategory: String): MealsDomainModel
}

class GetFilteredMealsImpl(
    private val repository: MealRepository
) : GetFilteredMeals {
    override suspend fun execute(mealCategory: String): MealsDomainModel =
        repository.getFilteredMeals(mealCategory)
}