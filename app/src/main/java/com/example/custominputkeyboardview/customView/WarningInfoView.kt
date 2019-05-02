package com.example.custominputkeyboardview.customView

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.example.custominputkeyboardview.R

class WarningInfoView : ConstraintLayout {

    private lateinit var ivIcon: ImageView
    private lateinit var tvWarning: TextView
    private lateinit var clContainer: ConstraintLayout
    private val warningMessage = "Warning..."

    constructor(context: Context?) : super(context) {
        initViews(context, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initViews(context, attrs)
    }

    private fun initViews(context: Context?, attrs: AttributeSet?) {
        context?.let {
            val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater.inflate(R.layout.view_warning_info, this, true)

            ivIcon = view.findViewById(R.id.ivIcon)
            tvWarning = view.findViewById(R.id.tvWarningInfo)
            clContainer = view.findViewById(R.id.clContainer)


                setupWarningMessage(warningMessage)


        }
    }


    /**
     * Set Warning Message
     */
    fun setupWarningMessage(warningMessage: String?) {
        warningMessage?.let {
            tvWarning.text = it
        }
    }


}