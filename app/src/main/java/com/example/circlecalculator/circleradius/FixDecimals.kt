package com.example.circlecalculator.circleradius

import java.text.DecimalFormatSymbols
import java.util.*

/**
 * Created by Costi on 5/7/2018.
 */

fun String.getDecimalComma(): String{
    val decimalSign = DecimalFormatSymbols(Locale.getDefault())
    val co: Char = decimalSign.decimalSeparator

    var v = this
    if(co == ',') v = v.replace('.', ',')
    return v
}

fun String.getDecimalPoint(): String{
    var v = this
    if(v.contains(',')) v = v.replace(',', '.')
    return  v
}