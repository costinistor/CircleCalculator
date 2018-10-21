package com.example.circlecalculator.circleradius.view

import android.widget.EditText
import android.widget.Spinner

/**
 * Created by Costi on 6/19/2018.
 */
interface ICircleCalculator {
    fun radiusName():String
    fun circumferenceName(): String
    fun diameterName():String
    fun areaName():String

    fun noInputValue()

    val spin: Spinner
    val inputVal: EditText
    var count: Int
}