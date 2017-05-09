package com.xwgoss.leakcanaryservice;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/4/12.
 */
public class UploadInfo implements Runnable {
    private static final String TAG="UploadInfo";
    private OkHttpClient okclient;
    private String path;
    private JSONArray infos=new JSONArray();
    public UploadInfo(String path,JSONObject info){
        this.path=path;
        infos.put(info);
        okclient=new OkHttpClient();

    }
    @Override
    public void run() {
        Request request=buildPostRequest(infos.toString());
        Call call =  okclient.newCall(request);

        call.enqueue(new Callback(){
                         @Override
                         public void onFailure(Call call, IOException e) {
                             Log.e(TAG, "onFailure: ", e.fillInStackTrace());
                         }
                         @Override
                         public void onResponse(Call call, Response response) throws IOException {
                             Log.i(TAG,"Response:"+response.message());
                             try {
                                 JSONObject jo=new JSONObject(response.body().string());
                                 if(jo.get("retcode").equals("0"))
                                     infos=new JSONArray("[]");
                             } catch (JSONException e) {
                                 Log.e(TAG,"转换返回信息时报错",e.fillInStackTrace());
                             }

                         }
                     }
        );
    }

    private Request buildPostRequest(String str) {

        RequestBody formBody =null;
            path=path+"/info";
            formBody=new FormBody.Builder().add("infos",str).build();
            Request request = new Request.Builder()
                .url(path)
                .post(formBody)
                .build();
        return request;
    }

}
