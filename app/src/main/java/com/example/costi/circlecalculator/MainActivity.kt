package com.example.costi.circlecalculator

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

//    private fun writeToFile(data: String, context: Context) {
//        try {
//            val outputStreamWriter = OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE))
//            outputStreamWriter.write(data)
//            outputStreamWriter.close()
//            Toast.makeText(this, "Salvat", Toast.LENGTH_SHORT).show()
//        } catch (e: Exception) {
//            Toast.makeText(this, "No salvat", Toast.LENGTH_SHORT).show()
//        }
//    }

//    fun Rebut(){
//            try{
//                val filename = "myfile.txt"
//                val fileContents = "Hello world!"
//                val file = File(filesDir, filename)
//                openFileOutput(filename, Context.MODE_PRIVATE).use {
//                    it.write(fileContents.toByteArray())}
//            }catch(e: Exception){
//
//            }

//        val file = File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES), "Rosana")
//        if (!file?.mkdirs()) {
//            file.mkdir()
//            Toast.makeText(this, file.mkdir().toString(), Toast.LENGTH_SHORT).show()
//        }
//    }


