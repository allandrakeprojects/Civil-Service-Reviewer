package com.psulccomsci.civilservicereviewer;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Locale;

public class frontforexamnp extends AppCompatActivity {
    TextToSpeech warning1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontforexamnp);
        warning1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status!=TextToSpeech.ERROR){

                    warning1.setLanguage(Locale.CANADA);
                }
            }
        });



        ((ImageButton) findViewById(R.id.chat)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                warning1.speak("Please wait for a moment.",TextToSpeech.QUEUE_FLUSH,null );
                dbhelper myDbHelper = new dbhelper(frontforexamnp.this);
                try {
                    myDbHelper.createDataBase();
                } catch (IOException ioe) {
                    throw new Error("Unable to create database");
                }
                try {
                    myDbHelper.openDataBase();
                } catch (SQLException sqle) {
                    throw sqle;
                }
                Intent intent = new Intent(getApplicationContext(),preload_examnp.class);
                startActivity(intent);
            }
        });

    }
}
