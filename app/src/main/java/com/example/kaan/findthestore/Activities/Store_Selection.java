package com.example.kaan.findthestore.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kaan.findthestore.Data.Statics;
import com.example.kaan.findthestore.Location.Locator;
import com.example.kaan.findthestore.R;
import com.example.kaan.findthestore.com.example.kaan.findthestore.UI.Store_Selection_Spinner;

/**
 * Created by kaan on 06/05/15.
 */
public class Store_Selection extends Activity implements SensorEventListener {

    private float lastX,lastY,lastZ;

    private float deltaX,deltaY,deltaZ;

    private Spinner spinner;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private boolean loaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.store_selection);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){

            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);

        }

        final Spinner[] spinner = {(Spinner) findViewById(R.id.spn)};

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                spinner[0] = Store_Selection_Spinner.Generate(getApplicationContext(), spinner[0]);

                Button btnRoute = (Button)findViewById(R.id.btnRoute);

                btnRoute.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Statics.selectedStore = Statics.Stores.get(spinner[0].getSelectedItemPosition());

                        Intent intent = new Intent(getApplicationContext(),Result.class);
                        startActivity(intent);

                    }

                });

                TextView tvLocation = (TextView) findViewById(R.id.tvLocation);

                Locator location = new Locator(getApplicationContext());

                tvLocation.setText(location.getLatitude() + " " + location.getLongitude());

            }
        },2000);
    }

    @Override
    public void onConfigurationChanged(Configuration config){

        super.onConfigurationChanged(config);

        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE){

            Statics.selectedLanguage = 0;

        } else if (config.orientation == Configuration.ORIENTATION_PORTRAIT){

            Statics.selectedLanguage = 1;

        }

        Intent i = new Intent(getApplicationContext(),Store_Selection.class);
        startActivity(i);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        deltaX = Math.abs(lastX - event.values[0]);
        deltaY = Math.abs(lastY - event.values[1]);
        deltaZ = Math.abs(lastZ - event.values[2]);

        if (deltaX < 2)
            deltaX = 0;

        if(deltaY < 2)
            deltaY = 0;

        if(deltaZ < 2)
            deltaZ = 0;

        lastX = event.values[0];
        lastY = event.values[1];
        lastZ = event.values[2];

        if (deltaX > 7 || deltaZ > 12 || deltaY > 7){

            Statics.selectedStore = Statics.Stores.get(((int) (Math.random() * 1000)) % (Statics.Stores.size() - 1));

            Intent i = new Intent(getApplicationContext(),Result.class);
            startActivity(i);

        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
