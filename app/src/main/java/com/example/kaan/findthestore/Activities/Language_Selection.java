package com.example.kaan.findthestore.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kaan.findthestore.Data.Statics;
import com.example.kaan.findthestore.Model.Dictionary;
import com.example.kaan.findthestore.R;

/**
 * Created by kaan on 06/05/15.
 */
public class Language_Selection extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.language_selection);

        Dictionary.Fill();

        ((Button) findViewById(R.id.btnTurkish)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Statics.selectedLanguage = 0;

                Intent intent = new Intent(getApplicationContext(),Store_Selection.class);
                startActivity(intent);

            }

        });

        ((Button) findViewById(R.id.btnEnglish)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Statics.selectedLanguage = 1;

                Intent intent = new Intent(getApplicationContext(),Store_Selection.class);
                startActivity(intent);

            }

        });

    }

}
