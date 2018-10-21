package com.example.circlecalculator.circleradius.presenter

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.example.circlecalculator.circleradius.R
import com.example.circlecalculator.circleradius.view.ICircleCalculator

class CircleCalculatorPresenter(mView: ICircleCalculator, context: Context): CircleCalculationMethods(mView, context){

    fun getCircleCalculation(lv: LinearLayout){
        if(!mView.inputVal.text.isNullOrEmpty()){
            val inputVal = mView.inputVal.text.toString().toDouble()

            when(mView.count){
                0 -> radiusMethod(lv, mView.spin, inputVal)
                1 -> diameterMethod(lv, mView.spin, inputVal)
                2 -> circumferenceMethod(lv, mView.spin, inputVal)
                3 -> areaMethod(lv, mView.spin, inputVal)
            }
        }else{
            mView.noInputValue()
        }
    }

    fun SelectTypeMethodToCalculate(){
        val adapter = ArrayAdapter.createFromResource(context, R.array.Drop, R.layout.spinner_select)
        adapter.setDropDownViewResource(R.layout.spinner_drop)
        mView.spin.adapter = adapter

        mView.spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mView.count = adapterView!!.selectedItemPosition
                mView.inputVal.text.clear()

//          val item = adapterView.getItemAtPosition(position)
//                if (count != null) {
//                    Toast.makeText(this@CircleCalculatorActivity, count.toString(), Toast.LENGTH_SHORT).show()
//                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }
    }



}