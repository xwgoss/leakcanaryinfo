package com.xwgoss.leakcanaryservice;

import android.util.Log;

import com.squareup.leakcanary.AnalysisResult;
import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.HeapDump;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lenovo on 2016/11/13.
 */
public class LeakUploadService extends DisplayLeakService {
    private final static String TAG="LeakUploadService";
    private static ExecutorService mFixedThreadExecutor;
    private static String servicePath;
    private static boolean uploadFileFlag=false;


    public static void init(String path){
        init(path,false,5);
    }
    private static void init(String path,boolean flag,int count){
        servicePath=path;
        uploadFileFlag=flag;
        mFixedThreadExecutor= Executors.newFixedThreadPool(count);
    }
    @Override
    protected void afterDefaultHandling(HeapDump heapDump, AnalysisResult result, String leakInfo) {
        Log.i(TAG, "servicePath: "+servicePath);
        Log.i(TAG, "uploadFileFlag: "+uploadFileFlag);
        if(result.excludedLeak||!result.leakFound) {
            return ;
        }
        if(servicePath==null&&servicePath.equals("")){
            return;
        }
       JSONObject info=new JSONObject();
        try {
            info.put("leakinfo",leakInfo);
            mFixedThreadExecutor.execute(new UploadInfo(servicePath,info));
        } catch (JSONException e) {
            Log.e(TAG,e.getLocalizedMessage());
        }
    }

}
