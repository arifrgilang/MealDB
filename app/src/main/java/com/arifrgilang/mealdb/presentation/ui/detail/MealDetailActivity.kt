package com.arifrgilang.mealdb.presentation.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.view.isVisible
import com.arifrgilang.mealdb.R
import com.arifrgilang.mealdb.databinding.ActivityMealDetailBinding
import com.arifrgilang.mealdb.domain.model.MealsDetailDomainModel
import com.arifrgilang.mealdb.presentation.util.Event
import com.arifrgilang.mealdb.presentation.util.baseclass.BaseBindingActivity
import com.arifrgilang.mealdb.presentation.util.toast
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class MealDetailActivity : BaseBindingActivity<ActivityMealDetailBinding>() {
    private val viewModel by viewModel<MealDetailViewModel>()

    override fun contentView(): Int = R.layout.activity_meal_detail

    override fun setupData(savedInstanceState: Bundle?) {
        val idMeal = intent?.getStringExtra("idMeal")
        viewModel.getMealDetail(idMeal!!)
    }

    override fun setupView() {
        viewModel.isError.observe(this, ::showError)
        viewModel.isLoading.observe(this, ::onLoading)
        viewModel.mealsData.observe(this, ::onDataFetched)
    }

    private fun onDataFetched(mealsData: MealsDetailDomainModel) {
        val mealData = mealsData.meals?.get(0)
        binding.meal = mealData
        mealData?.strTags?.let { categories ->
            if(categories.isNotEmpty()) {
                setCategory(categories.split(","))
            }
        }
        setupYouTubeListener(mealData?.strYoutube!!)
    }

    private fun setupYouTubeListener(uri: String) {
        binding.tvMealYoutube.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(uri)
            )
            startActivity(intent)
        }
    }

    private fun onLoading(isLoading: Boolean) {
        binding.pbMealDetail.isVisible = isLoading
        binding.clMealDetail.isVisible = !isLoading
    }

    private fun showError(unit: Event<Unit>) {
        toast("Error occurred")
    }

    private fun setCategory(list: List<String>) {
        for(x in list.indices){
            addChip(list[x])
        }
    }

    private fun addChip(category: String) {
        binding.cgTags.addView(
            Chip(this)
                .apply { text = category })
    }

}