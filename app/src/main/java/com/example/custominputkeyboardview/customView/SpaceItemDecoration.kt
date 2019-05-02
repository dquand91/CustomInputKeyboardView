package com.example.custominputkeyboardview.customView

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class SpaceItemDecoration(
    private var spacing: Int,
    private var isBound: Boolean = false
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (isBound) {
            outRect.set(spacing, spacing, spacing, spacing)
        } else {
            outRect.set(spacing, 0, spacing, 0)

        }
    }
}