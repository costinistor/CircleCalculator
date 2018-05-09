package com.example.circlecalculator.circleradius

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.ads.InterstitialAd


class MainActivity : AppCompatActivity() {

    var mInterstitialAd: InterstitialAd? = null

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener{
            var intent = Intent(this, CircleCalculatorActivity::class.java)
            startActivity(intent)

            if (mInterstitialAd!!.isLoaded)
            {
                mInterstitialAd?.show()
            }
        }

        btnExitApp.setOnClickListener {
            this.finishAffinity()
        }

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd?.adUnitId = getString(R.string.interstitial_ad)
    }

    override fun onResume() {
        super.onResume()
        if (!mInterstitialAd!!.isLoaded)
        {
            RequestNewInterstitial()
        }
    }

    fun RequestNewInterstitial(){
        mInterstitialAd?.loadAd(AdRequest.Builder().build())
    }
}




