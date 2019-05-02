package com.example.custominputkeyboardview.adapter

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.custominputkeyboardview.R
import com.example.custominputkeyboardview.utils.DisplayUtils
import com.example.custominputkeyboardview.viewHolder.KeyBoardNoticeHolder
import com.example.custominputkeyboardview.viewHolder.KeyboardItemHolder

class MyKeyboardInputAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var keyList: ArrayList<KeyBoardItem> = ArrayList()
    var column = 1
    lateinit var context: Context
    var keyHandler: HandlerBetweenAdapterAndView? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val screenWidth = DisplayUtils.getScreenWidth(context as FragmentActivity)
        return when (viewType) {
            KeyType.NOTICE.ordinal -> {
                val view = layoutInflater.inflate(R.layout.item_keyboard_notice, viewGroup, false)
                val llParam = view.layoutParams
                llParam.width = screenWidth / column * 3
                return KeyBoardNoticeHolder(view)
            }
            KeyType.CLEAR.ordinal -> KeyboardItemHolder(
                layoutInflater.inflate(
                    R.layout.item_keyboard_clear,
                    viewGroup,
                    false
                )
            )
            KeyType.ENTER.ordinal -> KeyboardItemHolder(
                layoutInflater.inflate(
                    R.layout.item_keyboard_enter,
                    viewGroup,
                    false
                )
            )
            KeyType.DOUBLE_SIZE.ordinal -> {
                val view = layoutInflater.inflate(R.layout.item_keyboard_key, viewGroup, false)
                val llParam = view.layoutParams
                llParam.width = screenWidth / column * 2
                return KeyboardItemHolder(view)
            }
            KeyType.EMPTY.ordinal -> {
                val view = layoutInflater.inflate(R.layout.item_keyboard_key, viewGroup, false)
                val layoutParams = view.layoutParams
                layoutParams.width = 0
                return KeyboardItemHolder(view)
            }
            KeyType.OPERATION.ordinal -> {
                val view = layoutInflater.inflate(R.layout.item_keyboard_key, viewGroup, false)
                view.setBackgroundColor(ResourcesCompat.getColor(context.resources, android.R.color.black, null))
                return KeyboardItemHolder(view)
            }
            else -> KeyboardItemHolder(
                layoutInflater.inflate(
                    R.layout.item_keyboard_key,
                    viewGroup,
                    false
                )
            )
        }
    }


    override fun getItemCount(): Int {
        return keyList.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val keyItem = keyList[position]
        when (viewHolder) {
            is KeyBoardNoticeHolder -> {
                viewHolder.bindView(keyItem)
            }
            is KeyboardItemHolder -> {
                viewHolder.bindView(keyItem)
            }
        }
        viewHolder.itemView.setOnClickListener{
            keyHandler?.onKeyPressed(keyList[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return keyList[position].keytype.ordinal
    }


    enum class KeyType {

        ENTER, // nút Enter
        CLEAR, // nút Clear
        DOUBLE_SIZE, // nút với 2 khoảng cách ở chiều ngang
        NOTICE, // nút Notice
        NORMAL, // nút số
        EMPTY, // nút rỗng
        OPERATION // nút phép toán

    }

    class KeyBoardItem(var name: String, var keytype: KeyType, var hidden: Boolean = false)


    interface HandlerBetweenAdapterAndView {

        /**
         * Callback when have key pressed
         */
        fun onKeyPressed(keyItem: KeyBoardItem)
    }
}