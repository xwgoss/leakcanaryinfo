package com.xwgoss.leakcanarytest;

import android.app.Application;
import android.content.Context;

import com.xwgoss.leakcanaryservice.InstallLeakCanary;

/**
 * Created by lenovo on 2016/11/13.
 */
public class ExampleApplication extends Application {

//    private RefWatcher refWatcher;

//    public static RefWatcher getRefWatcher(Context context){
//        ExampleApplication application= (ExampleApplication) context.getApplicationContext();
//        return application.refWatcher;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
//        if(LeakCanary.isInAnalyzerProcess(this))
//            return;
        InstallLeakCanary.startLeakCanary(this,false,"http://192.168.1.185:8080/leakcancary/upload/");

    }

}
