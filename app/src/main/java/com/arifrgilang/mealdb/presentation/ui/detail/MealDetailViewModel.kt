package com.arifrgilang.mealdb.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifrgilang.mealdb.domain.interactor.GetMealDetail
import com.arifrgilang.mealdb.domain.model.MealsDetailDomainModel
import com.arifrgilang.mealdb.presentation.util.Event
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber


/**
 * Created by arifrgilang on 8/31/2021
 */
abstract class MealDetailViewModel : ViewModel() {
    abstract val mealsData: LiveData<MealsDetailDomainModel>

    abstract val isLoading: LiveData<Boolean>
    abstract val isError: LiveData<Event<Unit>>

    abstract fun getMealDetail(idMeal: String)
}

class MealDetailViewModelImpl(
    private val getMealDetail: GetMealDetail
): MealDetailViewModel() {
    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception.toString())
        _isLoading.value = false
    }

    private val _mealsData = MediatorLiveData<MealsDetailDomainModel>()
    override val mealsData: LiveData<MealsDetailDomainModel>
        get() = _mealsData

    private val _isLoading = MediatorLiveData<Boolean>()
    override val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isError = MediatorLiveData<Event<Unit>>()
    override val isError: LiveData<Event<Unit>>
        get() = _isError

    override fun getMealDetail(idMeal: String) {
        viewModelScope.launch(errorHandler) {
            _isLoading.value = true
            val data = getMealDetail.execute(idMeal)
            _mealsData.postValue(data)
            _isLoading.value = false
        }
    }
}