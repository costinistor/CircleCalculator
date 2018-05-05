package com.example.costi.circlecalculator

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.ActionBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSupportActionBar()?.hide()

        btnStart.setOnClickListener{
            var intent = Intent(this, CircleCalculatorActivity::class.java)
            startActivity(intent)
        }

        btnExit.setOnClickListener{
            this.finishAffinity()
        }
    }
}


