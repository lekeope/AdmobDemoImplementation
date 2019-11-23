package com.edgedevstudio.admobdemoimplementation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*

class MainActivity : AppCompatActivity() {

    lateinit var mInterstitialAd: InterstitialAd
    lateinit var mAdView: AdView

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this)

        mAdView = findViewById(R.id.ad_view)
        initializeAds()
        loadAds()
    }

    private fun initializeAds() {
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "mainactivity_interstitial_ad_unit_id"
        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                requestNewInterstitial()
            }

            override fun onAdFailedToLoad(p0: Int) {
                super.onAdFailedToLoad(p0)
                Log.d(TAG, "onAdFailedToLoad InterstitialAd, Error Code = $p0")
            }
        }
    }

    private fun loadAds() {
        mAdView.loadAd(getAdRequest())
        requestNewInterstitial()
    }

    private fun getAdRequest(): AdRequest = AdRequest.Builder().build()

    private fun requestNewInterstitial() = mInterstitialAd.loadAd(getAdRequest())


    private fun displayAd() {
        if (Singleton.getInstance().isItRightTimeToShowAd()) {
            if (mInterstitialAd.isLoaded)
                mInterstitialAd.show()
            else
                requestNewInterstitial()

        }
    }


}
