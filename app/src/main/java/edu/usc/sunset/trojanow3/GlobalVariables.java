package edu.usc.sunset.trojanow3;

import android.app.Application;

/**
 * Created by atahmasebi on 4/30/15.
 */
public class GlobalVariables {

    private String mUserId = null;

    private GlobalVariables() {};

    private static GlobalVariables mInstance = null;

    public synchronized static GlobalVariables getInstance(){
        if (mInstance == null){
            mInstance = new GlobalVariables();
        }

        return mInstance;
    }

    public void setUserId(final String pUserId){
        mUserId = pUserId;
    }

    public String getUserId(){
        return mUserId;
    }

}
