package com.example.kaan.findthestore.com.example.kaan.findthestore.UI;

import android.content.Context;
import android.content.Intent;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kaan.findthestore.Core.Callback;
import com.example.kaan.findthestore.Data.Statics;
import com.example.kaan.findthestore.Http.HttpGetRequest;
import com.example.kaan.findthestore.Model.Mall;
import com.example.kaan.findthestore.Model.Store;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kaan on 06/05/15.
 */
public class Store_Selection_Spinner {

    public static Spinner Generate(final Context context, final Spinner spinner){

        final ArrayList<String> list = new ArrayList<>();

        new HttpGetRequest().Get(context,"http://fts.emeroglu.com/Api/AllStores",new Callback() {

            @Override
            public void Call(String response) throws JSONException {

                JSONArray jsonArray = new JSONArray(response);

                JSONObject json;

                Store store;

                Statics.Stores = new ArrayList<Store>();

                for (int i= 0;i<jsonArray.length();i++){

                    json = jsonArray.getJSONObject(i);

                    store = new Store();

                    store.Location = Integer.parseInt(json.get("location").toString());
                    store.Floor = Integer.parseInt(json.get("floor").toString());
                    store.Name = json.get("name").toString();
                    store.ID = Integer.parseInt(json.get("id").toString());
                    store.Number = Integer.parseInt(json.get("number").toString());

                    store.Mall = new Mall();
                    store.Mall.ID = Integer.parseInt(json.getJSONObject("mall").get("id").toString());
                    store.Mall.Name = json.getJSONObject("mall").get("name").toString();



                    Statics.Stores.add(store);

                }

                for (int i=0;i < Statics.Stores.size();i++){

                    list.add(i,Statics.Stores.get(i).Name);

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,list);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(adapter);

            }
        });

        return spinner;

    }

}
