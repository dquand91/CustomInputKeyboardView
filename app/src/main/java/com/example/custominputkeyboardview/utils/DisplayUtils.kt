package com.example.custominputkeyboardview.utils

import android.app.Activity
import android.graphics.Point
import android.support.v4.app.FragmentActivity

class DisplayUtils private constructor() {

    companion object {

        fun getScreenWidth(context: Activity): Int {
            val display = context.windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            val width = size.x
            return width
        }
    }
}