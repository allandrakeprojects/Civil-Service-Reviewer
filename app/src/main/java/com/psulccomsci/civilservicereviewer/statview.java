package com.psulccomsci.civilservicereviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class statview extends AppCompatActivity {

    ListView listView;
    TextView textView;
    ArrayList listItem;
    private static int i=0;
    private static long remin;
    private static long rem;
    int j;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statview);
        fab = findViewById(R.id.fab);
        remin = MainActivity.timeremin();
        rem = MainActivity.timerem();
        listItem = MainActivity.list();
        listView = findViewById(R.id.listView);
        textView= findViewById(R.id.stl);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        listView.setAdapter(adapter);
        j=ReanswerActivity.pos();


        if (j>0){
            listItem.set(j, ReanswerActivity.getText());
        }
        j=0;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                i=position;
                listItem = MainActivity.listdata();
                Intent intent = new Intent(getApplicationContext(), ReanswerActivity.class);
                startActivity(intent);

            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), result.class);
                startActivity(intent);} }
        );
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Action not allowed.",Toast.LENGTH_SHORT).show();
    }

    public static int position() {

        return i;
    }
    public static long remtime(){
        return rem;
    }

    public static long remintime(){
        return remin;
    }



}