package com.example.engwordlockscreen.presentation.utils.listeners

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(context : Context, recyclerView : RecyclerView, listener : OnItemClickListener) : RecyclerView.OnItemTouchListener
{
    var mListener = listener
    var mGestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener()
    {
        override fun onLongPress(e: MotionEvent) {
            var view = rv.findChildViewUnder(e.x, e.y)
            if (view != null && mListener != null)
            {
                mListener.onItemLongClick(view, rv.getChildAdapterPosition(view))
            }
        }
    })
    var rv = recyclerView

    interface OnItemClickListener
    {
        fun onItemClick(view : View, position : Int)

        fun onItemLongClick(view : View, position : Int)
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean
    {
        var view = rv.findChildViewUnder(e.x, e.y)
        if (view != null && mListener != null && mGestureDetector.onTouchEvent(e))
        {
            mListener.onItemClick(view, rv.getChildAdapterPosition(view))
            return true
        }
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }

}