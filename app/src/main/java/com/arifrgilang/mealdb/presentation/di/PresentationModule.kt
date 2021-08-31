package com.arifrgilang.mealdb.presentation.di

import com.arifrgilang.mealdb.presentation.ui.detail.MealDetailViewModel
import com.arifrgilang.mealdb.presentation.ui.detail.MealDetailViewModelImpl
import com.arifrgilang.mealdb.presentation.ui.main.MainRVAdapter
import com.arifrgilang.mealdb.presentation.ui.main.MainViewModel
import com.arifrgilang.mealdb.presentation.ui.main.MainViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by arifrgilang on 8/31/2021
 */
val presentationModule = module {
    viewModel<MainViewModel> {
        MainViewModelImpl(get())
    }

    viewModel<MealDetailViewModel> {
        MealDetailViewModelImpl(get())
    }

    single {
        MainRVAdapter(get())
    }
}