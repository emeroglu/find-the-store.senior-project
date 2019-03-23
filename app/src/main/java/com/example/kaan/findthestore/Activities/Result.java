package com.example.kaan.findthestore.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import com.example.kaan.findthestore.Data.Statics;
import com.example.kaan.findthestore.Model.Dictionary;
import com.example.kaan.findthestore.Model.Store;
import com.example.kaan.findthestore.R;

/**
 * Created by kaan on 06/05/15.
 */
public class Result extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.result);

        Store store = Statics.selectedStore;

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);

        TextView tv = (TextView) findViewById(R.id.tvResult);

        tvTitle.setText(store.Name);

        if(store.Floor != -1){

            if(store.Floor > -1 ){

                tv.setText(String.format(Dictionary.getTranslation("*GoUp", Statics.selectedLanguage),store.Floor));

            }else{
                tv.setText(String.format(Dictionary.getTranslation("*GoDown",Statics.selectedLanguage),store.Floor));
            }

            if(store.Location == 0){

                tv.setText(tv.getText() + Dictionary.getTranslation("*GoLeftWithElevator",Statics.selectedLanguage));

            }else if(store.Location == 1){

                tv.setText(tv.getText() + Dictionary.getTranslation("*GoStraight",Statics.selectedLanguage));

            }else{
                tv.setText(tv.getText() + Dictionary.getTranslation("*GoRightWithElevator",Statics.selectedLanguage));
            }

        } else {

            if(store.Location == 0){

                tv.setText(Dictionary.getTranslation("*GoLeft",Statics.selectedLanguage));

            }else if(store.Location == 1){

                tv.setText(Dictionary.getTranslation("*GoStraight",Statics.selectedLanguage));

            }else{
                tv.setText(Dictionary.getTranslation("*GoRight",Statics.selectedLanguage));
            }
        }

    }

    @Override
    public void onConfigurationChanged(Configuration config){

        super.onConfigurationChanged(config);

        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE){

            Statics.selectedLanguage = 0;

        } else if (config.orientation == Configuration.ORIENTATION_PORTRAIT){

            Statics.selectedLanguage = 1;

        }

        Intent i = new Intent(getApplicationContext(),Result.class);
        startActivity(i);

    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }
}
