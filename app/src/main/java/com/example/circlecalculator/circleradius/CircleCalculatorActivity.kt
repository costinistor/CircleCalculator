package com.example.circlecalculator.circleradius

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_circle_calculator.*
import android.widget.Toast
import android.widget.AdapterView.OnItemSelectedListener
import java.math.RoundingMode
import java.text.DecimalFormat
import android.content.Intent
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd


class CircleCalculatorActivity : AppCompatActivity() {

    var count = 0
    var totalResult = ""
    var mInterstitialAd: InterstitialAd? = null

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle_calculator)

        SelectTypeMethodToCalculate()

        resultSaved.text = LoadSavedData(this)

        btnExitApp.setOnClickListener { this.finishAffinity() }
        btnClearAll.setOnClickListener { inputValue.text = null }
        btnCalculate.setOnClickListener { GetCalculations() }
        btnSave.setOnClickListener { SaveData() }
        btnClearSaved.setOnClickListener { ClearSavedData() }

        btnRate.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, android.net.Uri.parse("market://details?id=com.circlecalculator.circleradius")))
        }

        //MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")

        adsView.loadAd(AdRequest.Builder().build())

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd?.adUnitId = getString(R.string.interstitial_ad)

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
        val dcm = DecimalFormat("#.##")
        dcm.roundingMode = RoundingMode.HALF_EVEN

        if(!inputValue.text.isNullOrEmpty()){
            val value = inputValue.text.toString().toDouble()

            when(count){
                0 -> radiusMethod(dcm, value)
                1 -> diameterMethod(dcm, value)
                2 -> circumferenceMethod(dcm, value)
                3 -> areaMethod(dcm, value)
            }

            resultCalculation.text = totalResult.getDecimalPoint()

        }else{
            Toast.makeText(this@CircleCalculatorActivity, "Please provide a value", Toast.LENGTH_SHORT).show()
        }
    }

    private fun radiusMethod(dcm:DecimalFormat, value:Double) {
        val resultArea = dcm.format(Math.PI * Math.pow(value, 2.0))
        val resultCircumference = dcm.format(2 * Math.PI * value)
        val resultDiameter = dcm.format(value * 2)

        totalResult = resultCircumference + "\n" + resultDiameter + "\n" + resultArea
        outTextCalculation.text = circumferenceName() + "\n" + diameterName() + "\n" + areaName()

        savedResult = spinMethod.selectedItem.toString() + ": " + inputValue.text + "\n" + "\n" +
                circumferenceName() + " = " + resultCircumference.getDecimalPoint() + "\n" +
                diameterName() + " = " + resultDiameter.getDecimalPoint() + "\n" +
                areaName() + " = " + resultArea.getDecimalPoint()
    }

    private fun diameterMethod(dcm:DecimalFormat, value:Double){
        val resultRadius = dcm.format(value / 2)
        val resultArea = dcm.format(Math.PI * Math.pow(value / 2, 2.0))
        val resultCircumference = dcm.format(Math.PI * value)

        totalResult = resultRadius + "\n" + resultArea + "\n" + resultCircumference
        outTextCalculation.text = radiusName() + "\n" + areaName() + "\n" + circumferenceName()

        savedResult = spinMethod.selectedItem.toString() + ": " + inputValue.text + "\n" + "\n" +
                radiusName() + " = " + resultRadius.getDecimalPoint() + "\n" +
                areaName() + " = " + resultArea.getDecimalPoint() + "\n" +
                circumferenceName() + " = " + resultCircumference.getDecimalPoint()
    }

    private fun circumferenceMethod(dcm:DecimalFormat, value:Double){
        val radius = value / (2 * Math.PI)
        val resultDiameter = dcm.format(radius * 2)
        val resultArea = dcm.format(Math.PI * Math.pow(radius, 2.0))
        val resultRadius = dcm.format(radius)

        totalResult = resultRadius + "\n" + resultDiameter + "\n" + resultArea
        outTextCalculation.text = radiusName() + "\n" + diameterName() + "\n" + areaName()

        savedResult = spinMethod.selectedItem.toString() + ": " + inputValue.text + "\n" + "\n" +
                radiusName() + " = " + resultRadius.getDecimalPoint() + "\n" +
                diameterName() + " = " + resultDiameter.getDecimalPoint() + "\n" +
                areaName() + " = " + resultArea.getDecimalPoint()
    }

    private fun areaMethod(dcm:DecimalFormat, value:Double){
        val radius = Math.sqrt(value / Math.PI)
        val resultDiameter = dcm.format(radius * 2)
        val resultCircumference = dcm.format(2 * Math.PI * radius)
        val resultRadius = dcm.format(radius)

        totalResult = resultRadius + "\n" + resultDiameter + "\n" + resultCircumference
        outTextCalculation.text = radiusName() + "\n" + diameterName() + "\n" + circumferenceName()

        savedResult = spinMethod.selectedItem.toString() + ": " + inputValue.text + "\n" + "\n" +
                radiusName() + " = " + resultRadius.getDecimalPoint() + "\n" +
                diameterName() + " = " + resultDiameter.getDecimalPoint() + "\n" +
                circumferenceName() + " = " + resultCircumference.getDecimalPoint()
    }

    private fun radiusName():String = getString(R.string.radiusCircle)
    private fun circumferenceName():String = getString(R.string.circumferenceCircle)
    private fun diameterName():String = getString(R.string.diameterCircle)
    private fun areaName():String = getString(R.string.areaCircle)

    override fun onBackPressed() {
        super.onBackPressed()
        if (mInterstitialAd!!.isLoaded)
        {
            mInterstitialAd?.show()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!mInterstitialAd!!.isLoaded)
        {
            RequestNewInterstitial()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (adsView != null)
        {
            adsView.destroy()
        }
    }

    fun RequestNewInterstitial(){
        mInterstitialAd?.loadAd(AdRequest.Builder().build())
    }

}
