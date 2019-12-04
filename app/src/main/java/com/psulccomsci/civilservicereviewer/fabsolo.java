package com.psulccomsci.civilservicereviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;


public class fabsolo extends AppCompatActivity {
    private static boolean clickFAB=false;
    private static FloatingActionButton fab1;
    private static FloatingActionButton fab2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabsolo);
        fab1 = findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Yes",Toast.LENGTH_SHORT).show();
                clickFAB=true;
            }
        });
        fab2 = findViewById(R.id.fab);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Yes",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Mathematics.class);
                startActivity(intent);
                clickFAB=true;
            }
        });


    }

    public static Boolean fabstatus(){

        return clickFAB;
    }
    public static FloatingActionButton fab1(){

        return fab1;
    }
}
