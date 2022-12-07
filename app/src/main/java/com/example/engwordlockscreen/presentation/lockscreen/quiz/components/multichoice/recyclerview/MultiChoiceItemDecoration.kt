package com.example.engwordlockscreen.presentation.lockscreen.quiz.components.multichoice.recyclerview

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MultiChoiceItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) != parent.adapter?.itemCount?.minus(1)) {
            Log.d("TRANSFER","Item Decoration")
            outRect.apply {
                left += 50
                right += 50
                bottom += 50
                top += 50
            }
        }
        super.getItemOffsets(outRect, view, parent, state)
    }
}