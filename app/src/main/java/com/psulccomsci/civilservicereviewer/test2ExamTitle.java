package com.psulccomsci.civilservicereviewer;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class test2ExamTitle extends AppCompatActivity {

    CountDownTimer countDownTimer;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_title);
        textView = findViewById(R.id.extitle);
        textView.setText("Grammar");
        countDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), test2.class);
                startActivity(intent);
            }

        };


        countDownTimer.start();

    }

}
