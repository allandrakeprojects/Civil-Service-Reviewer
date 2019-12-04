package com.psulccomsci.civilservicereviewer;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class result_np extends AppCompatActivity {

    ListView listView;
    TextView textView;
    ArrayList listItem;
    ArrayList listItem2;
    private static int i=0;
    private static int selected;
    private static ArrayList selectedList;
    int j;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        selectedList=MainActivity.selectedlist();
        fab = findViewById(R.id.fab);
        listItem = test7.result_np();
        listItem2 = test7.result_np();
        listView = findViewById(R.id.listView);
        textView= findViewById(R.id.stl);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        listView.setAdapter(adapter);
//        Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                listItem = test7.subans();
                listItem2 = test7.subans();
                // TODO Auto-generated method stub
                i=position;
                listItem=listItem2;
                selected = (int) selectedList.get(i);
                Intent intent = new Intent(getApplicationContext(), SeeanswerActivity.class);
                startActivity(intent);

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), user_navi.class);
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

    public static int selected(){
        return selected;
    }
}