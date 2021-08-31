package com.arifrgilang.mealdb.domain.interactor

import com.arifrgilang.mealdb.domain.MealRepository
import com.arifrgilang.mealdb.domain.model.MealsDetailDomainModel


/**
 * Created by arifrgilang on 8/31/2021
 */
interface GetMealDetail {
    suspend fun execute(idMeal: String): MealsDetailDomainModel
}

class GetMealDetailImpl(
    private val repository: MealRepository
): GetMealDetail {
    override suspend fun execute(idMeal: String): MealsDetailDomainModel =
        repository.getMealDetail(idMeal)
}