package com.example.circlecalculator.circleradius

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.example.circlecalculator.circleradius.Interfaces.PassValue
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), dialogPolicy.OnOptionSelected {

    //var mInterstitialAd: InterstitialAd? = null

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener{
            val intent = Intent(this, CircleCalculatorActivity::class.java)
            startActivity(intent)

//            if (mInterstitialAd!!.isLoaded)
//            {
//                mInterstitialAd?.show()
//            }
        }

        btnExitApp.setOnClickListener {
            this.finishAffinity()
        }

        //mInterstitialAd = InterstitialAd(this)
        //mInterstitialAd?.adUnitId = getString(R.string.interstitial_ad)

        openDialogPolicy()
    }

    fun openDialogPolicy(){
        var isPolicy = LoadSavedData(this, "policy.txt", "false").toBoolean()
        if(!isPolicy){
            val transaction = supportFragmentManager.beginTransaction()
            val policy = dialogPolicy()
            policy.show(transaction, "policy")
        }
    }

//    override fun onResume() {
//        super.onResume()
//        if (!mInterstitialAd!!.isLoaded)
//        {
//            RequestNewInterstitial()
//        }
//    }

//    fun RequestNewInterstitial(){
//        mInterstitialAd?.loadAd(AdRequest.Builder().build())
//    }

    override fun onPolicyAccept(isAccepted: Boolean) {
        if(isAccepted) {
            policy = "true"
            SavePolicy(this, "policy.txt")
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onPolicyDecline(isDeclined: Boolean) {
        if(isDeclined)
            this.finishAffinity()
    }
}




