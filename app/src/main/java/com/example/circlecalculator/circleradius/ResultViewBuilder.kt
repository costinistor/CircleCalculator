package com.example.circlecalculator.circleradius

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.circlecalculator.circleradius.utils.getDecimalDot

/**
 * Created by Costi on 5/29/2018.
 */
class ResultViewBuilder(context: Context) : View(context) {
    //constructor(context: Context) : super(context)

    fun ResultView(lv: LinearLayout, nameId: String, result: String ){
        val paddingH = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, resources.displayMetrics).toInt()
        val paddingV = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, resources.displayMetrics).toInt()
        val widthLine = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics).toInt()

        val lvContainer = LinearLayout(context)
        lvContainer.orientation = LinearLayout.HORIZONTAL
        lv.addView(lvContainer)

        val tvName = TextView(context)
        tvName.text = nameId
        tvName.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f)
        tvName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        tvName.setTextColor(Color.parseColor("#3b3b34"))
        tvName.setPadding(paddingH, paddingV, paddingH, paddingV)
        lvContainer.addView(tvName)

        val tvLine = TextView(context)
        tvLine.layoutParams = LinearLayout.LayoutParams(widthLine, ViewGroup.LayoutParams.MATCH_PARENT)
        tvLine.setBackgroundColor(Color.parseColor("#ffffff"))
        lvContainer.addView(tvLine)

        val tvResult = TextView(context)
        tvResult.text = result.getDecimalDot()
        tvResult.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f)
        tvResult.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        tvResult.setTextColor(Color.parseColor("#3b3b34"))
        tvResult.setPadding(paddingH, paddingV, paddingH, paddingV)
        lvContainer.addView(tvResult)

    }

    fun myView(nameId: String, result: String ): LinearLayout{
        val paddingH = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, resources.displayMetrics).toInt()
        val paddingV = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, resources.displayMetrics).toInt()
        val widthLine = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics).toInt()

        val lvContainer = LinearLayout(context)
        lvContainer.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lvContainer.orientation = LinearLayout.HORIZONTAL


        val tvName = TextView(context)
        tvName.text = nameId
        tvName.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f)
        tvName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        tvName.setTextColor(Color.parseColor("#3b3b34"))
        tvName.setPadding(paddingH, paddingV, paddingH, paddingV)
        lvContainer.addView(tvName)

        val tvLine = TextView(context)
        tvLine.layoutParams = LinearLayout.LayoutParams(widthLine, ViewGroup.LayoutParams.MATCH_PARENT)
        tvLine.setBackgroundColor(Color.parseColor("#ffffff"))
        lvContainer.addView(tvLine)

        val tvResult = TextView(context)
        tvResult.text = result.getDecimalDot()
        tvResult.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f)
        tvResult.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        tvResult.setTextColor(Color.parseColor("#3b3b34"))
        tvResult.setPadding(paddingH, paddingV, paddingH, paddingV)
        lvContainer.addView(tvResult)

        return lvContainer

    }

}