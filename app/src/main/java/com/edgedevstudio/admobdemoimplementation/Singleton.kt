package com.edgedevstudio.admobdemoimplementation

/**
 * Created by Olorunleke Opeyemi on 2019-11-23.
 **/

class Singleton private constructor() {

    private var actionCount = 0


    companion object{
        private var sInstance: Singleton? = null
        fun getInstance(): Singleton {
            if (sInstance == null)
                sInstance = Singleton()

            return sInstance!!
        }
    }


    fun isItRightTimeToShowAd(): Boolean{
        actionCount++ // increment actionCount
        var isNumberOfActionsReached = (actionCount % 4) == 0
        return isNumberOfActionsReached
    }
}