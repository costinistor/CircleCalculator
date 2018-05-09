package com.example.costi.circlecalculator

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.annotation.RequiresApi
import android.support.v7.app.ActionBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

import java.io.*


class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getSupportActionBar()?.hide()

//        val file = File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES), "Rosana")
//        if (!file?.mkdirs()) {
//            file.mkdir()
//            Toast.makeText(this, file.mkdir().toString(), Toast.LENGTH_SHORT).show()
//        }


        btnStart.setOnClickListener{
            var intent = Intent(this, CircleCalculatorActivity::class.java)
            startActivity(intent)
//            var name: String = readFile("config.txt")
//
//            Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
        }

        btnExit.setOnClickListener{
            //this.finishAffinity()
            writeToFile("Bureb", this)
            writeFile("config.txt", "La soare vara")
            }
//            try{
//                val filename = "myfile.txt"
//                val fileContents = "Hello world!"
//                val file = File(filesDir, filename)
//                openFileOutput(filename, Context.MODE_PRIVATE).use {
//                    it.write(fileContents.toByteArray())}
//            }catch(e: Exception){
//
//            }
    }

    fun writeFile(file: String, data:String){
        var fos = openFileOutput(file, Context.MODE_PRIVATE)
        fos.write(data.toByteArray())
        fos.close()
    }


    private fun writeToFile(data: String, context: Context) {
        try {
            val outputStreamWriter = OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE))
            outputStreamWriter.write(data)
            outputStreamWriter.close()
            Toast.makeText(this, "Salvat", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "No salvat", Toast.LENGTH_SHORT).show()
        }
    }


    fun readFile(file:String):String{
        //var text = "vb"
        try{
            var fis = openFileInput(file)
            var ase = fis.readBytes()
            fis.close()
            return String(ase)
        }catch(e:Exception){
            return "bn"
        }


    }
}


