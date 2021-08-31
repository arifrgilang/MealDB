package com.arifrgilang.mealdb.data.repository

import com.arifrgilang.mealdb.data.service.MealAPI
import com.arifrgilang.mealdb.domain.MealRepository
import com.arifrgilang.mealdb.domain.model.MealsDetailDomainModel
import com.arifrgilang.mealdb.domain.model.MealsDomainModel


/**
 * Created by arifrgilang on 8/31/2021
 */
class MealRepositoryImpl(
    private val mealAPI: MealAPI
) : MealRepository {
    override suspend fun getFilteredMeals(mealType: String): MealsDomainModel =
        mealAPI.getFilteredMeals(mealType)

    override suspend fun getMealDetail(idMeal: String): MealsDetailDomainModel =
        mealAPI.getMealDetail(idMeal)
}