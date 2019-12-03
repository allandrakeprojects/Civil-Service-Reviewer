package com.psulccomsci.civilservicereviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class answerless extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean player1turn=true;
    private TextView textview;
    private String texttext;
    Button bt_1;
    Button bt_2;
    Button bt_3;
    Button bt_4;
    Button bt_5;
    Button bt_6;
    Button bt_7;
    Button bt_8;
    Button bt_9;
    Button bt_0;
    private static ArrayList<Integer> value = new ArrayList<Integer>();
    private static ArrayList<String> quee = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answerless);

        bt_0 = findViewById(R.id.bt_0);
        bt_1 = findViewById(R.id.bt_1);
        bt_2 = findViewById(R.id.bt_2);
        bt_3 = findViewById(R.id.bt_3);
        bt_4 = findViewById(R.id.bt_4);
        bt_5 = findViewById(R.id.bt_5);
        bt_6 = findViewById(R.id.bt_6);
        bt_7 = findViewById(R.id.bt_7);
        bt_8 = findViewById(R.id.bt_8);
        bt_9 = findViewById(R.id.bt_9);

        bt_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int x=0;x<10;x++){
                    for (int y=0;y<10;y++){
                        if (x+y<10){
                            value.add(x+y);
                            quee.add(x + " + " + y +" = ");
                            texttext = x + " + " + y +" = ";
                            textview.setText(texttext);
                            calcutext.setTextView(textview);
                        }
                    }
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (player1turn){
            ((Button) v).getText();
        }else{
            ((Button) v).getText();
        }
    }
    public void checkingans(int value){

    }

    public static ArrayList<String> quee(){
        return quee;
    }


}
