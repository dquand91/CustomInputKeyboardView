package com.example.custominputkeyboardview.viewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.custominputkeyboardview.R
import com.example.custominputkeyboardview.adapter.MyKeyboardInputAdapter
import com.example.custominputkeyboardview.customView.WarningInfoView

class KeyBoardNoticeHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var receiptItem: WarningInfoView = view.findViewById(R.id.keyNotice)

    fun bindView(key: MyKeyboardInputAdapter.KeyBoardItem) {
        if (key.hidden) {
            receiptItem.visibility = View.INVISIBLE
        } else {
            receiptItem.setupWarningMessage(key.name)
        }
    }


}