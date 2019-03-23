package com.example.kaan.findthestore.Http;

import android.content.Context;

import com.example.kaan.findthestore.Core.Callback;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;

/**
 * Created by kaan on 06/05/15.
 */
public class HttpGetRequest {

    public void Get(Context context, String url,final Callback onLoad){

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(context,url,new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(String content) throws JSONException {

                super.onSuccess(content);

                onLoad.Call(content);

            }
        });

    }

}
