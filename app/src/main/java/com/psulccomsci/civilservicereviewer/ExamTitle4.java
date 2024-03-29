package com.psulccomsci.civilservicereviewer;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExamTitle4 extends AppCompatActivity {

    CountDownTimer countDownTimer;
    private static String tempx;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_title);
        tempx = Vocabulary.temp();
        textView = findViewById(R.id.extitle);
        textView.setText(tempx);
        countDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), Grammar.class);
                startActivity(intent);
            }

        };


        countDownTimer.start();

    }

}
