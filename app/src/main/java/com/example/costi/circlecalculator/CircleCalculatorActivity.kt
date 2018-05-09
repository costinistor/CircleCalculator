package com.example.costi.circlecalculator

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_circle_calculator.*
import android.widget.Toast
import android.widget.AdapterView.OnItemSelectedListener
import java.math.RoundingMode
import java.text.DecimalFormat


class CircleCalculatorActivity : AppCompatActivity() {

    var count = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle_calculator)

        SelectTypeMethodToCalculate()

        resultSaved.text = LoadSavedData(this)

        btnClearAll.setOnClickListener { inputValue.text = null }
        btnCalculate.setOnClickListener { GetCalculations() }
        btnSave.setOnClickListener { SaveData() }
        btnClearSaved.setOnClickListener { ClearSavedData() }
    }

    fun SelectTypeMethodToCalculate(){
        var adapter = ArrayAdapter.createFromResource(this, R.array.Drop, R.layout.spinner_select)
        adapter.setDropDownViewResource(R.layout.spinner_drop)
        spinMethod.adapter = adapter

        spinMethod.onItemSelectedListener = object : OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {
                //val item = adapterView.getItemAtPosition(position)
                count = adapterView.selectedItemPosition
                inputValue.text = null
//                if (count != null) {
//                    Toast.makeText(this@CircleCalculatorActivity, count.toString(), Toast.LENGTH_SHORT).show()
//                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
                // TODO Auto-generated method stub
            }
        }
    }

    fun SaveData(){
        SaveDatafile(this, resultSaved)
    }

    fun ClearSavedData(){
        savedResult = ""
        SaveDatafile(this, resultSaved)
    }

    fun GetCalculations(){
        CalculateCircle()
    }

    fun CalculateCircle(){
        var dcm = DecimalFormat("#.##")
        dcm.roundingMode = RoundingMode.HALF_EVEN

        if(!inputValue.text.isNullOrEmpty()){
            var value: Double = inputValue.text.toString().toDouble()


            when(count){
                0 -> {
                    var resultArea = dcm.format(Math.PI * Math.pow(value, 2.0))
                    var resultCircumference = dcm.format(2 * Math.PI * value)
                    var resultDiameter = dcm.format(value * 2)
                    var totalResult = resultCircumference.toString() + "\n" + resultDiameter.toString() + "\n" + resultArea.toString()


                    outTextCalculation.text = circumferenceName() + "\n" + diameterName() + "\n" + areaName();
                    resultCalculation.text = totalResult

                    savedResult = spinMethod.selectedItem.toString() + ": " + inputValue.text + "\n" + "\n" +
                            circumferenceName() + " = " + resultCircumference.convertResult() + "\n" +
                            diameterName() + " = " + resultDiameter.convertResult() + "\n" +
                            areaName() + " = " + resultArea.convertResult()
                }
            }


        }else{
            Toast.makeText(this@CircleCalculatorActivity, "E gol", Toast.LENGTH_SHORT).show()
        }

    }

    fun radiusName():String = getString(R.string.radiusCircle)
    fun circumferenceName():String = getString(R.string.circumferenceCircle)
    fun diameterName():String = getString(R.string.diameterCircle)
    fun areaName():String = getString(R.string.areaCircle)


}
