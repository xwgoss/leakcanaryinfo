package com.xwgoss.leakcanarytest;

import android.app.Activity;
import android.util.SparseArray;

/**
 * Created by lenovo on 2016/11/13.
 */
public class ActivityManager {
    private  SparseArray<Activity> cntainer=new SparseArray<Activity>();
    private int key=0;
    private static ActivityManager mInstance;
    private ActivityManager(){

    }
    public  static ActivityManager getmInstance(){
        if(mInstance==null)
            mInstance=new ActivityManager();
        return mInstance;
    }

    public void addActivity(Activity activity){
        cntainer.put(key++,activity);
    }
}
