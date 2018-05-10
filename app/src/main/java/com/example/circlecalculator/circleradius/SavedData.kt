package com.example.circlecalculator.circleradius


import android.content.Context
import android.widget.TextView

/**
 * Created by Costi on 5/8/2018.
 */
var savedResult:String = ""
val file:String = "circleSave.txt"

fun LoadSavedData(context: Context):String{

    try{
        val fi = context.openFileInput(file)
        val rb = fi.readBytes()
        fi.close()
        return String(rb)
    }catch(e: Exception){
        return ""
    }
}

fun SaveDatafile(context:Context, resultSave: TextView){
    val fo = context.openFileOutput(file, Context.MODE_PRIVATE)
    fo.write(savedResult.toByteArray())
    fo.close()
    resultSave.text = savedResult
}