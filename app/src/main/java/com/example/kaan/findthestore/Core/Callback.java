package com.example.kaan.findthestore.Core;

import org.json.JSONException;

/**
 * Created by kaan on 06/05/15.
 */
public abstract class Callback {

    public abstract void Call(String response) throws JSONException;

}
