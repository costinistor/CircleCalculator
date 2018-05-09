package com.example.costi.circlecalculator


import android.app.Application
import android.os.Environment
import android.content.Context
import android.widget.TextView
import java.io.File

/**
 * Created by Costi on 5/8/2018.
 */
var savedResult:String = ""
var file:String = "circleSave.txt"

fun LoadSavedData(context: Context):String{

    try{
        var fis = context.openFileInput(file)
        var ase = fis.readBytes()
        fis.close()
        return String(ase)
    }catch(e: Exception){
        return "null"
    }
}

fun SaveDatafile(context:Context, resultSave: TextView){
    var fos = context.openFileOutput(file, Context.MODE_PRIVATE)
    fos.write(savedResult.toByteArray())
    fos.close()
    resultSave.text = savedResult
}