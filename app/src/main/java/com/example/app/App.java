package com.example.app;

import android.app.Application;

import com.example.app.utils.AppUtils;
import com.example.app.utils.L;

/**
 * Created by zhenjia.cheng
 * 2017/1/20
 */

public class App extends Application {

    private static App sInstance;

    private static final String DEBUG_MODE = "debugMode";

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
//        L.isDebug = AppUtils.getBooleanMetaData(this , DEBUG_MODE);
    }

    public  synchronized static  App getInstance(){
        return sInstance;
    }
}
