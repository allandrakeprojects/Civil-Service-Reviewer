package com.psulccomsci.civilservicereviewer;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class calcutext extends AppCompatActivity {
    private static TextView textView;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calcutext);

        textView = findViewById(R.id.answerless);
    }

    public static void setTextView(TextView textView) {
        calcutext.textView = textView;
    }
}
