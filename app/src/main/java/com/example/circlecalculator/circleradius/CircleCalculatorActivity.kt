package com.example.circlecalculator.circleradius

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import kotlinx.android.synthetic.main.activity_circle_calculator.*
import android.widget.AdapterView.OnItemSelectedListener
import java.math.RoundingMode
import java.text.DecimalFormat
import android.content.Intent
import android.widget.*
import com.example.circlecalculator.circleradius.Interfaces.PassValue
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd


class CircleCalculatorActivity : AppCompatActivity() {


    var count = 0
    var mInterstitialAd: InterstitialAd? = null
    var decimalSave = "decimalSave.txt"
    val fileSave:String = "circleSave.txt"
    var resultBuilder: ResultViewBuilder? = null

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle_calculator)
        resultBuilder = ResultViewBuilder(this)

        SelectTypeMethodToCalculate()

        resultSaved.text = LoadSavedData(this, fileSave, "")

        btnExitApp.setOnClickListener { openDialogSetting() }
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

    fun openDialogSetting(){
        val transaction = fragmentManager.beginTransaction()
        val setting = dialogSetting()
        setting.show(transaction, "setting")
    }

    fun SelectTypeMethodToCalculate(){
        val adapter = ArrayAdapter.createFromResource(this, R.array.Drop, R.layout.spinner_select)
        adapter.setDropDownViewResource(R.layout.spinner_drop)
        spinMethod.adapter = adapter

        spinMethod.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //val item = adapterView.getItemAtPosition(position)

                    count = adapterView!!.selectedItemPosition
                    inputValue.text.clear()


//                if (count != null) {
//                    Toast.makeText(this@CircleCalculatorActivity, count.toString(), Toast.LENGTH_SHORT).show()
//                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }
    }

    fun SaveData(){
        SaveDatafile(this, resultSaved, fileSave)
    }

    fun ClearSavedData(){
        savedResult = ""
        SaveDatafile(this, resultSaved, fileSave)
    }

    fun GetCalculations(){
        CalculateCircle()
    }

    fun CalculateCircle(){
        val decimalPoints = LoadSavedData(this, decimalSave, "#.##")
        val dcm = DecimalFormat(decimalPoints)
        dcm.roundingMode = RoundingMode.HALF_EVEN

        if(!inputValue.text.isNullOrEmpty()){
            val value = inputValue.text.toString().toDouble()

            when(count){
                0 -> radiusMethod(dcm, value)
                1 -> diameterMethod(dcm, value)
                2 -> circumferenceMethod(dcm, value)
                3 -> areaMethod(dcm, value)
            }

        }else{
            Toast.makeText(this@CircleCalculatorActivity, "Please provide a value", Toast.LENGTH_SHORT).show()
        }
    }

    private fun radiusMethod(dcm:DecimalFormat, value:Double) {
        val resultArea = dcm.format(Math.PI * Math.pow(value, 2.0))
        val resultCircumference = dcm.format(2 * Math.PI * value)
        val resultDiameter = dcm.format(value * 2)

        resultLayout.removeAllViews()
        resultBuilder!!.ResultView(resultLayout, circumferenceName(), resultCircumference)
        resultBuilder!!.ResultView(resultLayout, diameterName(), resultDiameter)
        resultBuilder!!.ResultView(resultLayout, areaName(), resultArea)

        savedResult = spinMethod.selectedItem.toString() + ": " + inputValue.text + "\n" + "\n" +
                circumferenceName() + " = " + resultCircumference.getDecimalDot() + "\n" +
                diameterName() + " = " + resultDiameter.getDecimalDot() + "\n" +
                areaName() + " = " + resultArea.getDecimalDot()
    }

    private fun diameterMethod(dcm:DecimalFormat, value:Double){
        val resultRadius = dcm.format(value / 2)
        val resultArea = dcm.format(Math.PI * Math.pow(value / 2, 2.0))
        val resultCircumference = dcm.format(Math.PI * value)

        resultLayout.removeAllViews()
        resultBuilder!!.ResultView(resultLayout, radiusName(), resultRadius)
        resultBuilder!!.ResultView(resultLayout, areaName(), resultArea)
        resultBuilder!!.ResultView(resultLayout, circumferenceName(), resultCircumference)

        savedResult = spinMethod.selectedItem.toString() + ": " + inputValue.text + "\n" + "\n" +
                radiusName() + " = " + resultRadius.getDecimalDot() + "\n" +
                areaName() + " = " + resultArea.getDecimalDot() + "\n" +
                circumferenceName() + " = " + resultCircumference.getDecimalDot()
    }

    private fun circumferenceMethod(dcm:DecimalFormat, value:Double){
        val radius = value / (2 * Math.PI)
        val resultDiameter = dcm.format(radius * 2)
        val resultArea = dcm.format(Math.PI * Math.pow(radius, 2.0))
        val resultRadius = dcm.format(radius)

        resultLayout.removeAllViews()
        resultBuilder!!.ResultView(resultLayout, radiusName(), resultRadius)
        resultBuilder!!.ResultView(resultLayout, diameterName(), resultDiameter)
        resultBuilder!!.ResultView(resultLayout, areaName(), resultArea)

        savedResult = spinMethod.selectedItem.toString() + ": " + inputValue.text + "\n" + "\n" +
                radiusName() + " = " + resultRadius.getDecimalDot() + "\n" +
                diameterName() + " = " + resultDiameter.getDecimalDot() + "\n" +
                areaName() + " = " + resultArea.getDecimalDot()
    }

    private fun areaMethod(dcm:DecimalFormat, value:Double){
        val radius = Math.sqrt(value / Math.PI)
        val resultDiameter = dcm.format(radius * 2)
        val resultCircumference = dcm.format(2 * Math.PI * radius)
        val resultRadius = dcm.format(radius)

        resultLayout.removeAllViews()
        resultBuilder!!.ResultView(resultLayout, radiusName(), resultRadius)
        resultBuilder!!.ResultView(resultLayout, diameterName(), resultDiameter)
        resultBuilder!!.ResultView(resultLayout, circumferenceName(), resultCircumference)

        savedResult = spinMethod.selectedItem.toString() + ": " + inputValue.text + "\n" + "\n" +
                radiusName() + " = " + resultRadius.getDecimalDot() + "\n" +
                diameterName() + " = " + resultDiameter.getDecimalDot() + "\n" +
                circumferenceName() + " = " + resultCircumference.getDecimalDot()
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
