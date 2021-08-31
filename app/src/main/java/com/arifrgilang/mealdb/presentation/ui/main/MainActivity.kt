package com.arifrgilang.mealdb.presentation.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.arifrgilang.mealdb.R
import com.arifrgilang.mealdb.databinding.ActivityMainBinding
import com.arifrgilang.mealdb.domain.model.MealsDomainModel
import com.arifrgilang.mealdb.presentation.ui.detail.MealDetailActivity
import com.arifrgilang.mealdb.presentation.util.CustomRVMargin
import com.arifrgilang.mealdb.presentation.util.Event
import com.arifrgilang.mealdb.presentation.util.baseclass.BaseBindingActivity
import com.arifrgilang.mealdb.presentation.util.baseclass.BaseRecyclerAdapter
import com.arifrgilang.mealdb.presentation.util.toast
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    private val rvAdapter: MainRVAdapter by inject()
    private val viewModel by viewModel<MainViewModel>()

    override fun contentView(): Int = R.layout.activity_main

    override fun setupData(savedInstanceState: Bundle?) {
        viewModel.getFilteredMeal("Seafood")
    }

    override fun setupView() {
        setViewModelObservers()
        initRecyclerView()
    }

    private fun setViewModelObservers() {
        viewModel.isError.observe(this, ::showError)
        viewModel.mealsData.observe(this, ::onDataFetched)
        viewModel.isLoading.observe(this, ::onLoading)
    }

    private fun initRecyclerView() {
        with(binding.rvFilteredMeals) {
            adapter = provideCustomAdapter()
            layoutManager = provideGridLayoutManager()
            addItemDecoration(provideCustomRvMargin())
        }
    }

    private fun onLoading(isLoading: Boolean) {
        binding.pbFilteredMeals.isVisible = isLoading
        binding.rvFilteredMeals.isVisible = !isLoading
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onDataFetched(data: MealsDomainModel) {
        rvAdapter.clearAndNotify()
        rvAdapter.insertAndNotify(data.meals)
        rvAdapter.notifyDataSetChanged()
    }

    private fun showError(unit: Event<Unit>) {
        toast("Error occurred")
    }

    private fun navigateToDetailActivity(idMeal: String) {
        startActivity(
            Intent(this, MealDetailActivity::class.java)
                .apply { putExtra("idMeal", idMeal) }
        )
    }

    private fun provideCustomAdapter() = rvAdapter.apply {
        setOnItemClickListener(
            object: BaseRecyclerAdapter.AdapterOnClick {
                override fun onRecyclerItemClicked(extra: String) {
                    navigateToDetailActivity(extra)
                }
            }
        )
    }

    private fun provideGridLayoutManager() =
        GridLayoutManager(this@MainActivity, 2)

    private fun provideCustomRvMargin() =
        CustomRVMargin(this@MainActivity, 16, CustomRVMargin.GRID_2)
}