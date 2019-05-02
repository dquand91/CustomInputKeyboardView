package com.example.custominputkeyboardview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.custominputkeyboardview.customView.MyKeyBoardInputView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MyKeyBoardInputView.EventBetweenInputViewAndActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myKeyBoardView.setEventHandler(this@MainActivity)

    }


    override fun onEnter(value: Int) {
        tvResult.text = value.toString()
    }
}
