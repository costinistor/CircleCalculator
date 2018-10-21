package com.example.circlecalculator.circleradius.presenter

import android.content.Context
import android.widget.LinearLayout
import android.widget.Spinner
import com.example.circlecalculator.circleradius.ResultViewBuilder
import com.example.circlecalculator.circleradius.utils.savedResult
import com.example.circlecalculator.circleradius.utils.getDecimalFormat
import com.example.circlecalculator.circleradius.utils.getDecimalDot
import com.example.circlecalculator.circleradius.view.ICircleCalculator

open class CircleCalculationMethods(val mView: ICircleCalculator, val context: Context)
{
    var dcm = getDecimalFormat(context)
    var resultBuilder = ResultViewBuilder(context)

    fun radiusMethod(lv: LinearLayout, spin: Spinner, value: Double) {
        val resultArea = dcm.format(Math.PI * Math.pow(value, 2.0))
        val resultCircumference = dcm.format(2 * Math.PI * value)
        val resultDiameter = dcm.format(value * 2)

        lv.removeAllViews()
        resultBuilder.ResultView(lv, mView.circumferenceName(), resultCircumference)
        resultBuilder.ResultView(lv, mView.diameterName(), resultDiameter)
        resultBuilder.ResultView(lv, mView.areaName(), resultArea)

        savedResult = spin.selectedItem.toString() + ": " + value + "\n" + "\n" +
                mView.circumferenceName() + " = " + resultCircumference.getDecimalDot() + "\n" +
                mView.diameterName() + " = " + resultDiameter.getDecimalDot() + "\n" +
                mView.areaName() + " = " + resultArea.getDecimalDot()
    }

    fun diameterMethod(lv: LinearLayout, spin: Spinner, value: Double){
        val resultRadius = dcm.format(value / 2)
        val resultArea = dcm.format(Math.PI * Math.pow(value / 2, 2.0))
        val resultCircumference = dcm.format(Math.PI * value)

        lv.removeAllViews()
        resultBuilder.ResultView(lv, mView.radiusName(), resultRadius)
        resultBuilder.ResultView(lv, mView.areaName(), resultArea)
        resultBuilder.ResultView(lv, mView.circumferenceName(), resultCircumference)

        savedResult = spin.selectedItem.toString() + ": " + value + "\n" + "\n" +
                mView.radiusName() + " = " + resultRadius.getDecimalDot() + "\n" +
                mView.areaName() + " = " + resultArea.getDecimalDot() + "\n" +
                mView.circumferenceName() + " = " + resultCircumference.getDecimalDot()
    }

    fun circumferenceMethod(lv: LinearLayout, spin: Spinner, value: Double){
        val radius = value / (2 * Math.PI)
        val resultDiameter = dcm.format(radius * 2)
        val resultArea = dcm.format(Math.PI * Math.pow(radius, 2.0))
        val resultRadius = dcm.format(radius)

        lv.removeAllViews()
        resultBuilder.ResultView(lv, mView.radiusName(), resultRadius)
        resultBuilder.ResultView(lv, mView.diameterName(), resultDiameter)
        resultBuilder.ResultView(lv, mView.areaName(), resultArea)

        savedResult = spin.selectedItem.toString() + ": " + value + "\n" + "\n" +
                mView.radiusName() + " = " + resultRadius.getDecimalDot() + "\n" +
                mView.diameterName() + " = " + resultDiameter.getDecimalDot() + "\n" +
                mView.areaName() + " = " + resultArea.getDecimalDot()
    }

    fun areaMethod(lv: LinearLayout, spin: Spinner, value: Double){
        val radius = Math.sqrt(value / Math.PI)
        val resultDiameter = dcm.format(radius * 2)
        val resultCircumference = dcm.format(2 * Math.PI * radius)
        val resultRadius = dcm.format(radius)

        lv.removeAllViews()
        resultBuilder.ResultView(lv, mView.radiusName(), resultRadius)
        resultBuilder.ResultView(lv, mView.diameterName(), resultDiameter)
        resultBuilder.ResultView(lv, mView.circumferenceName(), resultCircumference)

        savedResult = spin.selectedItem.toString() + ": " + value + "\n" + "\n" +
                mView.radiusName() + " = " + resultRadius.getDecimalDot() + "\n" +
                mView.diameterName() + " = " + resultDiameter.getDecimalDot() + "\n" +
                mView.circumferenceName() + " = " + resultCircumference.getDecimalDot()
    }
}