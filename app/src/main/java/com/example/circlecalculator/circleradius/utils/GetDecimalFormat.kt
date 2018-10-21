package com.example.circlecalculator.circleradius.utils

import android.content.Context
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Created by Costi on 6/19/2018.
 */

var decimalSave = "decimalSave.txt"

fun getDecimalFormat(context: Context): DecimalFormat {
    val decimalPoints = LoadSavedData(context, decimalSave, "#.##")
    val dcm = DecimalFormat(decimalPoints)
    dcm.roundingMode = RoundingMode.HALF_EVEN
    return dcm
}