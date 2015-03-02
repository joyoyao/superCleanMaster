package com.yzy.supercleanmaster.base;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {
    private static BaseApplication mInstance;


    private Context mContext;


    // private Gson mG;
    public static BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;


        MyCrashHandler myCrashHandler = MyCrashHandler.getInstance();
        myCrashHandler.init(getApplicationContext());
        Thread.currentThread().setUncaughtExceptionHandler(myCrashHandler);
    }


    @Override
    public void onLowMemory() {
        // TODO Auto-generated method stub

        android.os.Process.killProcess(android.os.Process.myPid());
        super.onLowMemory();

    }

}
