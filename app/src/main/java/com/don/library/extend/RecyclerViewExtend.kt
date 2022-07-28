package com.don.library.extend

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager


fun RecyclerView.setNestedScrollingEnabled(): RecyclerView {
    return apply {
        isNestedScrollingEnabled = false
    }
}

fun RecyclerView.removeDefaultAnimator(): RecyclerView {
    return apply { //        if (itemAnimator is SimpleItemAnimator) {
        //            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        //        }
        itemAnimator = null
    }
}

fun RecyclerView.initLinearLayoutManager(orientation: Int = RecyclerView.VERTICAL): RecyclerView {
    return apply {
        var manager = MyLinearLayoutManager(context)
        manager.orientation = orientation
        manager.isSmoothScrollbarEnabled = true
        layoutManager = manager
    }
}

fun RecyclerView.initGridLayoutManager(
    spanCount: Int = 1,
    spanSizeLookup: GridLayoutManager.SpanSizeLookup? = null
): RecyclerView {
    return apply {
        var manager = GridLayoutManager(context, spanCount)
        manager.isSmoothScrollbarEnabled = true
        spanSizeLookup?.let {
            manager.spanSizeLookup = spanSizeLookup
        }
        layoutManager = manager
    }
}

fun RecyclerView.initStaggeredGridManager(
    spanCount: Int = 2,
    orientation: Int = StaggeredGridLayoutManager.VERTICAL
): RecyclerView {
    return apply {
        var manager = StaggeredGridLayoutManager(spanCount, orientation)
        layoutManager = manager
    }
}

fun RecyclerView.addItemDecoration(rect: Rect): RecyclerView {
    return apply {
        addItemDecoration(DividerItemDecoration {
            rect
        })
    }
}

fun RecyclerView.addItemDecoration(
    left: Int = 0,
    top: Int = 0,
    right: Int,
    bottom: Int
): RecyclerView {
    return apply {
        addItemDecoration(DividerItemDecoration {
            Rect(left, top, right, bottom)
        })
    }
}

fun RecyclerView.addItemDecoration(listener: ((position: Int) -> Rect)): RecyclerView {
    return apply {
        addItemDecoration(DividerItemDecoration(listener))
    }
}

fun RecyclerView.addGridItemDecoration(
    spanCount: Int,
    includeEdge: Boolean,
    spacing: Int,
    verticalSpacing: Int = 0,
    fromPosition: Int = 0
): RecyclerView {
    return apply {
        addItemDecoration(
            GridItemDecoration(
                spanCount,
                includeEdge,
                spacing,
                verticalSpacing,
                fromPosition
            )
        )
    }
}

class DividerItemDecoration constructor(@NonNull var mListener: ((position: Int) -> Rect)) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        var position = (view?.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
        var rect = mListener.invoke(position)
        outRect?.set(rect)
    }
}

class GridItemDecoration(
    private val spanCount: Int,
    private val includeEdge: Boolean,
    private val spacing: Int,
    private val verticalSpacing: Int = 0,
    private val fromPosition: Int = 0
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        var position = parent.getChildAdapterPosition(view)
        if (position < fromPosition) {
            return
        }

        position -= fromPosition
        val column = position % spanCount
        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount
            if (position < spanCount) {
                outRect.top = if (verticalSpacing == 0) spacing else verticalSpacing
            }
            outRect.bottom = if (verticalSpacing == 0) spacing else verticalSpacing
        } else {
            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount
            if (position >= spanCount) {
                outRect.top = if (verticalSpacing == 0) spacing else verticalSpacing
            }
        }
    }
}

class MyLinearLayoutManager(context: Context) : LinearLayoutManager(context) {
    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}