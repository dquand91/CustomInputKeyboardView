package com.example.custominputkeyboardview.customView

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.custominputkeyboardview.R
import com.example.custominputkeyboardview.adapter.MyKeyboardInputAdapter

class MyKeyBoardInputView : ConstraintLayout, MyKeyboardInputAdapter.HandlerBetweenAdapterAndView {

    companion object {

        private const val KEYBOARD_COLUMN = 4
    }

    private lateinit var keyboardInputAdapter : MyKeyboardInputAdapter
    private lateinit var rvKeyboard: RecyclerView
    private var currentOperation = "+"
    private var eventHandler: EventBetweenInputViewAndActivity? = null

    constructor(context: Context?) : super(context) {
        initView(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, attrs)
    }


    private fun initView(context: Context?, attrs: AttributeSet? = null) {
        context?.let {
            val inflater = it.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.view_custom_keyboard_input, this, true)

            keyboardInputAdapter = MyKeyboardInputAdapter().apply {
                this.context = getContext()
                keyList = getKeyList()
                column = KEYBOARD_COLUMN
                keyHandler = this@MyKeyBoardInputView
            }
            rvKeyboard = view.findViewById<RecyclerView>(R.id.rvKeyBoard).apply {
                addItemDecoration(SpaceItemDecoration(1, true))
                adapter = keyboardInputAdapter
                layoutManager = StaggeredGridLayoutManager(KEYBOARD_COLUMN, StaggeredGridLayoutManager.VERTICAL)
            }
        }
    }


    private fun getKeyList() : ArrayList<MyKeyboardInputAdapter.KeyBoardItem>{
        return ArrayList<MyKeyboardInputAdapter.KeyBoardItem>().apply {
            add(
                MyKeyboardInputAdapter.KeyBoardItem(
                    "!!!Warning...",
                    MyKeyboardInputAdapter.KeyType.NOTICE
                )
            )
            add(MyKeyboardInputAdapter.KeyBoardItem("0", MyKeyboardInputAdapter.KeyType.EMPTY))
            add(MyKeyboardInputAdapter.KeyBoardItem("0", MyKeyboardInputAdapter.KeyType.EMPTY))

            add(
                MyKeyboardInputAdapter.KeyBoardItem(
                    "!!!Warning...",
                    MyKeyboardInputAdapter.KeyType.CLEAR
                )
            )

            add(MyKeyboardInputAdapter.KeyBoardItem("7", MyKeyboardInputAdapter.KeyType.NORMAL))
            add(MyKeyboardInputAdapter.KeyBoardItem("8", MyKeyboardInputAdapter.KeyType.NORMAL))
            add(MyKeyboardInputAdapter.KeyBoardItem("9", MyKeyboardInputAdapter.KeyType.NORMAL))
            add(MyKeyboardInputAdapter.KeyBoardItem("-", MyKeyboardInputAdapter.KeyType.OPERATION))
            add(MyKeyboardInputAdapter.KeyBoardItem("4", MyKeyboardInputAdapter.KeyType.NORMAL))
            add(MyKeyboardInputAdapter.KeyBoardItem("5", MyKeyboardInputAdapter.KeyType.NORMAL))
            add(MyKeyboardInputAdapter.KeyBoardItem("6", MyKeyboardInputAdapter.KeyType.NORMAL))
            add(MyKeyboardInputAdapter.KeyBoardItem("+", MyKeyboardInputAdapter.KeyType.OPERATION))
            add(MyKeyboardInputAdapter.KeyBoardItem("1", MyKeyboardInputAdapter.KeyType.NORMAL))
            add(MyKeyboardInputAdapter.KeyBoardItem("2", MyKeyboardInputAdapter.KeyType.NORMAL))
            add(MyKeyboardInputAdapter.KeyBoardItem("3", MyKeyboardInputAdapter.KeyType.NORMAL))

            add(
                MyKeyboardInputAdapter.KeyBoardItem(
                    "Enter",
                    MyKeyboardInputAdapter.KeyType.ENTER
                )
            )

            add(MyKeyboardInputAdapter.KeyBoardItem("0", MyKeyboardInputAdapter.KeyType.DOUBLE_SIZE))
            add(MyKeyboardInputAdapter.KeyBoardItem("0", MyKeyboardInputAdapter.KeyType.EMPTY))
            add(MyKeyboardInputAdapter.KeyBoardItem("00", MyKeyboardInputAdapter.KeyType.NORMAL))
        }
    }


    fun hideNoticeButton(){
        keyboardInputAdapter.keyList[0].hidden = true
        keyboardInputAdapter.notifyDataSetChanged()
    }

    override fun onKeyPressed(keyItem: MyKeyboardInputAdapter.KeyBoardItem) {
        when(keyItem.keytype){
            MyKeyboardInputAdapter.KeyType.OPERATION -> {
                val lastStr = currentOperation[currentOperation.length - 1].toString()
                if ("+" == lastStr || "-" == lastStr) {
                    currentOperation = currentOperation.dropLast(1)
                }
                currentOperation += keyItem.name
            }
            MyKeyboardInputAdapter.KeyType.CLEAR -> {
                currentOperation = "+"
            }
            MyKeyboardInputAdapter.KeyType.ENTER -> {
                eventHandler?.onEnter(convertOperationToValue(currentOperation))
                currentOperation = "+"
            }
            MyKeyboardInputAdapter.KeyType.NOTICE -> {

            }
            else -> {
                currentOperation += keyItem.name
            }
        }
    }


    private fun convertOperationToValue(operation: String): Int {
        if (operation.isEmpty() || operation == "+" || operation == "-") {
            return 0
        }
        var mulVal = if (operation[operation.length - 1].toString() == "-") -1 else 1
        var fragOperation = "0"
        var totalValue = 0
        for (s in operation) {
            val str = s.toString()
            when (str) {
                "-" -> {
                    totalValue += fragOperation.toInt() * mulVal
                    fragOperation = "0"
                    mulVal = -1
                }
                "+" -> {
                    totalValue += fragOperation.toInt() * mulVal
                    fragOperation = "0"
                    mulVal = 1
                }
                else -> fragOperation += str
            }
        }
        totalValue += fragOperation.toInt() * mulVal
        return totalValue
    }


    fun setEventHandler(eventHandler: EventBetweenInputViewAndActivity) {
        this.eventHandler = eventHandler
    }


    interface EventBetweenInputViewAndActivity {

        fun onEnter(value: Int)
    }

}