package com.example.circlecalculator.circleradius


import android.content.Context
import android.widget.TextView

/**
 * Created by Costi on 5/8/2018.
 */
var savedResult:String = ""
var saveDecimal:String = "#.##"
var policy = ""
//val file:String = "circleSave.txt"

fun LoadSavedData(context: Context, file: String, default: String):String{

    try{
        val fi = context.openFileInput(file)
        val rb = fi.readBytes()
        fi.close()
        return String(rb)
    }catch(e: Exception){
        return default
    }
}

fun SaveDatafile(context:Context, resultSave: TextView, file: String){
    val fo = context.openFileOutput(file, Context.MODE_PRIVATE)
    fo.write(savedResult.toByteArray())
    fo.close()
    resultSave.text = savedResult
}

fun SaveDecimalPoints(context:Context, file: String){
    val fo = context.openFileOutput(file, Context.MODE_PRIVATE)
    fo.write(saveDecimal.toByteArray())
    fo.close()
}

fun SavePolicy(context:Context, file: String){
    val fo = context.openFileOutput(file, Context.MODE_PRIVATE)
    fo.write(policy.toByteArray())
    fo.close()
}