package com.arifrgilang.mealdb.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifrgilang.mealdb.domain.interactor.GetFilteredMeals
import com.arifrgilang.mealdb.domain.model.MealsDomainModel
import com.arifrgilang.mealdb.presentation.util.Event
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber


/**
 * Created by arifrgilang on 8/31/2021
 */
abstract class MainViewModel : ViewModel() {
    abstract val mealsData: LiveData<MealsDomainModel>

    abstract val isLoading: LiveData<Boolean>
    abstract val isError: LiveData<Event<Unit>>

    abstract fun getFilteredMeal(mealType: String)
}

class MainViewModelImpl(
    private val getFilteredMeals: GetFilteredMeals
) : MainViewModel() {
    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception.toString())
        _isLoading.value = false
    }

    private val _mealsData = MediatorLiveData<MealsDomainModel>()
    override val mealsData: LiveData<MealsDomainModel>
        get() = _mealsData

    private val _isLoading = MediatorLiveData<Boolean>()
    override val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isError = MediatorLiveData<Event<Unit>>()
    override val isError: LiveData<Event<Unit>>
        get() = _isError

    override fun getFilteredMeal(mealType: String) {
        viewModelScope.launch(errorHandler) {
            _isLoading.value = true
            val data = getFilteredMeals.execute(mealType)
            _mealsData.postValue(data)
            _isLoading.value = false
        }
    }

}