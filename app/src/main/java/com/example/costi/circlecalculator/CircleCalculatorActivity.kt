package com.example.costi.circlecalculator

import android.app.Application
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_circle_calculator.*
import android.widget.Toast
import android.widget.AdapterView.OnItemSelectedListener
import java.text.DecimalFormatSymbols
import java.util.*


class CircleCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle_calculator)
        getSupportActionBar()?.hide()

        var adapter = ArrayAdapter.createFromResource(this, R.array.Drop, R.layout.spinner_select)
        adapter.setDropDownViewResource(R.layout.spinner_drop)
        spinMethod.adapter = adapter

        spinMethod.onItemSelectedListener = object : OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {
                val item = adapterView.getItemAtPosition(position)
                if (item != null) {
                    //Toast.makeText(this@CircleCalculatorActivity, item.toString(), Toast.LENGTH_SHORT).show()
                }
                //Toast.makeText(this@CircleCalculatorActivity, "Selected", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
                // TODO Auto-generated method stub
            }
        }


    }

    fun MySpinner(){
        spinMethod.onItemSelectedListener = object : OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>, view: View,
                                        position: Int, id: Long) {
                val item = adapterView.getItemAtPosition(position)
                if (item != null) {
                    Toast.makeText(this@CircleCalculatorActivity, item.toString(),
                            Toast.LENGTH_SHORT).show()
                }
                Toast.makeText(this@CircleCalculatorActivity, "Selected",
                        Toast.LENGTH_SHORT).show()

            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
                // TODO Auto-generated method stub

            }
        }

        var name: String = getString(R.string.circle_info)
    }
}
