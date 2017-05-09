package com.xwgoss.leakcanaryservice;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.squareup.leakcanary.internal.DisplayLeakActivity;
import com.squareup.leakcanary.internal.LeakCanaryInternals;

import java.util.concurrent.TimeUnit;

/**
 * Created by lenovo on 2017/4/17.
 */
public class InstallLeakCanary {
    public static void startLeakCanary(Application o,boolean flag,String path){
        RefWatcher refWatcher= LeakCanary.
                refWatcher(o).watchDelay(5, TimeUnit.SECONDS).listenerServiceClass(LeakUploadService.class)
                .buildAndInstall();
        LeakUploadService.init(path);
        LeakCanaryInternals.setEnabled(o, DisplayLeakActivity.class, flag);
    }
}
