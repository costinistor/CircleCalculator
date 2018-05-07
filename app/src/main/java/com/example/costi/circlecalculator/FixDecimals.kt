package com.example.costi.circlecalculator

import java.text.DecimalFormatSymbols
import java.util.*

/**
 * Created by Costi on 5/7/2018.
 */

fun String.convertDecimal(): String{
    var decimalSign = DecimalFormatSymbols(Locale.getDefault())
    var co: Char = decimalSign.decimalSeparator

    var v = this
    if(co == ',') v = v.replace('.', ',')
    return v
}

fun String.convertResult(): String{
    var v = this
    if(v.contains(',')) v = v.replace(',', '.')
    return  v
}