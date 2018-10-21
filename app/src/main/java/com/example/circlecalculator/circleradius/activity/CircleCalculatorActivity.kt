package com.example.circlecalculator.circleradius.activity

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_circle_calculator.*
import android.content.Intent
import android.widget.*
import com.example.circlecalculator.circleradius.*
import com.example.circlecalculator.circleradius.dialogs.dialogSetting
import com.example.circlecalculator.circleradius.presenter.CircleCalculatorPresenter
import com.example.circlecalculator.circleradius.utils.LoadSavedData
import com.example.circlecalculator.circleradius.utils.SaveDatafile
import com.example.circlecalculator.circleradius.utils.getDecimalFormat
import com.example.circlecalculator.circleradius.utils.savedResult
import com.example.circlecalculator.circleradius.view.ICircleCalculator
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd


class CircleCalculatorActivity : AppCompatActivity(), ICircleCalculator, dialogSetting.OnDecimalSelected {

    override val inputVal: EditText get() = inputValue
    override val spin: Spinner get() = spinMethod

    override var count = 0

    override fun radiusName():String = getString(R.string.radiusCircle)
    override fun circumferenceName():String = getString(R.string.circumferenceCircle)
    override fun diameterName():String = getString(R.string.diameterCircle)
    override fun areaName():String = getString(R.string.areaCircle)

    var mInterstitialAd: InterstitialAd? = null
    val fileSave:String = "circleSave.txt"
    private var presenter: CircleCalculatorPresenter? = null

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle_calculator)

        presenter = CircleCalculatorPresenter(this, this)

        presenter?.SelectTypeMethodToCalculate()

        resultSaved.text = LoadSavedData(this, fileSave, "")

        btnExitApp.setOnClickListener { openDialogSetting() }
        btnClearAll.setOnClickListener { inputValue.text = null }
        btnCalculate.setOnClickListener { presenter?.getCircleCalculation(resultLayout) }
        btnSave.setOnClickListener { SaveData() }
        btnClearSaved.setOnClickListener { ClearSavedData() }

        btnRate.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, android.net.Uri.parse("market://details?id=com.circlecalculator.circleradius")))
            }catch(e:Exception){
                startActivity(Intent(Intent.ACTION_VIEW, android.net.Uri.parse("https://play.google.com/store/apps/details?id=com.circlecalculator.circleradius")))
            }

        }

        //MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")
        adsView.loadAd(AdRequest.Builder().build())

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd?.adUnitId = getString(R.string.interstitial_ad)

    }

    fun openDialogSetting(){
        val transaction = supportFragmentManager.beginTransaction()
        val setting = dialogSetting()
        setting.show(transaction, "setting")
    }

    fun SaveData(){
        SaveDatafile(this, resultSaved, fileSave)
    }

    fun ClearSavedData(){
        savedResult = ""
        SaveDatafile(this, resultSaved, fileSave)
    }


    override fun noInputValue() {
        Toast.makeText(this@CircleCalculatorActivity, "Please provide a value", Toast.LENGTH_SHORT).show()
    }

    // Admob interstitials --------------------------------------------------------------------------------------------------

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

    override fun onDecimalSelected() {
        presenter?.dcm = getDecimalFormat(this)
    }

}
