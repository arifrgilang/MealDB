package com.arifrgilang.mealdb.presentation.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by arifrgilang on 8/31/2021
 */
class CustomRVMargin(
    private val context: Context,
    private val spaceHeight: Int,
    private val type: String
) : RecyclerView.ItemDecoration(){

    companion object{
        const val LINEAR_HORIZONTAL = "linear_horizontal"
        const val LINEAR_VERTICAL = "linear_vertical"
        const val LINEAR_VERTICAL_REVERSED = "linear_vertical_reversed"
        const val GRID_2 = "grid_2"
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val customMargin = dpToPx(context, spaceHeight)
        when(type){
            LINEAR_HORIZONTAL -> {
                with(outRect){
                    if(parent.getChildAdapterPosition(view) == 0){
                        left = customMargin
                    }
                    top = customMargin
                    right = customMargin
                    bottom = customMargin
                }
            }
            LINEAR_VERTICAL -> {
                with(outRect){
                    if(parent.getChildAdapterPosition(view) == 0){
                        top = customMargin
                    }
                    left = customMargin
                    right = customMargin
                    bottom = customMargin
                }
            }
            LINEAR_VERTICAL_REVERSED -> {
                with(outRect){
                    if(parent.getChildAdapterPosition(view) == (parent.adapter?.itemCount?.minus(1))){
                        top = customMargin
                    }
                    left = customMargin
                    right = customMargin
                    bottom = customMargin
                }
            }
            GRID_2 -> {
                with(outRect){
                    if(parent.getChildAdapterPosition(view) in 0..1){
                        top = customMargin
                    }
                    if(parent.getChildAdapterPosition(view) % 2 == 0){
                        left = customMargin
                        right = customMargin / 2
                    } else {
                        left = customMargin / 2
                        right = customMargin
                    }
                    bottom = customMargin
                }
            }
        }
    }

    private fun dpToPx(context: Context, dp: Int): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }

}