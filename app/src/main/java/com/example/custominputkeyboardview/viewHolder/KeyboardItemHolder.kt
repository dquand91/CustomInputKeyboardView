package com.example.custominputkeyboardview.viewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.custominputkeyboardview.R
import com.example.custominputkeyboardview.adapter.MyKeyboardInputAdapter

class KeyboardItemHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var tvKey: TextView = view.findViewById(R.id.tvKey)

    fun bindView(key: MyKeyboardInputAdapter.KeyBoardItem) {
        tvKey.text = key.name
    }

}