package com.psulccomsci.civilservicereviewer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Queue;

public class preload_exam extends AppCompatActivity {
    CountDownTimer countDownTimer;
    TextToSpeech warning;
    private static int tempx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload_exam);

        warning = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status!=TextToSpeech.ERROR){

                    warning.setLanguage(Locale.CANADA);
                }
            }
        });


        countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {



            }
            @Override
            public void onFinish() {
                warning.speak("You may now Start.",TextToSpeech.QUEUE_FLUSH,null );
                Intent intent = new Intent(getApplicationContext(),ExamTitle.class);
                startActivity(intent);


            }

        };
        countDownTimer.start();

    }


}
