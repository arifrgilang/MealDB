package com.arifrgilang.mealdb.domain.di

import com.arifrgilang.mealdb.domain.interactor.GetFilteredMeals
import com.arifrgilang.mealdb.domain.interactor.GetFilteredMealsImpl
import com.arifrgilang.mealdb.domain.interactor.GetMealDetail
import com.arifrgilang.mealdb.domain.interactor.GetMealDetailImpl
import org.koin.dsl.module


/**
 * Created by arifrgilang on 8/31/2021
 */
val domainModule = module {
    single<GetFilteredMeals> {
        GetFilteredMealsImpl(get())
    }

    single<GetMealDetail> {
        GetMealDetailImpl(get())
    }
}