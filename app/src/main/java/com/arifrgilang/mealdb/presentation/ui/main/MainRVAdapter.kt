package com.arifrgilang.mealdb.presentation.ui.main

import android.content.Context
import android.view.ViewGroup
import com.arifrgilang.mealdb.R
import com.arifrgilang.mealdb.databinding.ItemMealBinding
import com.arifrgilang.mealdb.domain.model.MealDomainModel
import com.arifrgilang.mealdb.presentation.util.baseclass.BaseRecyclerAdapter
import timber.log.Timber


/**
 * Created by arifrgilang on 8/31/2021
 */
class MainRVAdapter(
    context: Context
): BaseRecyclerAdapter<MealDomainModel, ItemMealBinding, MainRVAdapter.ViewHolder>(context) {
    inner class ViewHolder(view: ItemMealBinding) : BaseViewHolder(view) {
        override fun onBind(model: MealDomainModel) {
            view.meal = model
            view.cvItemMeal.setOnClickListener {
                getCallback()?.onRecyclerItemClicked(model.idMeal!!)
            }
        }
    }

    override fun getResLayout(type: Int): Int = R.layout.item_meal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(initViewBinding(viewType, parent))
}