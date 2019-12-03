package com.psulccomsci.civilservicereviewer;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListOfScore extends AppCompatActivity{


    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    ScoreDBHelper scoreDBHelper;
    Cursor scoreCursor;
    String score, com;
    Cursor AA, BB, CC, DD, EE,FF;
    String A="1", B="2", C="3", D="4", E="5", F="6";
    TextView first,second, third, fourth, fifth, sixth;
    int i=1;
    private static String[] list = new String[30];
    private static ArrayList<String> data = new ArrayList<String>(5);
    private static ArrayList<String> listscore = new ArrayList<String>(5);
    private static ArrayList<String> compare = new ArrayList<String>(5);
    private static ArrayList<Integer> selectedList = new ArrayList<Integer>(5);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        super.onCreate(savedInstanceState);
        i=1;
        scoreDBHelper = new ScoreDBHelper(this);
        db = scoreDBHelper.getWritableDatabase();
        db = openHelper.getWritableDatabase();
        AA = db.rawQuery(" SELECT "+ scoreDBHelper.col_2+" FROM "+ scoreDBHelper.table_scores + " where " +scoreDBHelper.scoreID
                +" = "+i,null);
        BB = db.rawQuery(" SELECT "+ scoreDBHelper.col_2+" FROM "+ scoreDBHelper.table_scores + " where " +scoreDBHelper.scoreID
                +" = "+i,null);
        CC = db.rawQuery(" SELECT "+ scoreDBHelper.col_2+" FROM "+ scoreDBHelper.table_scores + " where " +scoreDBHelper.scoreID
                +" = "+i,null);
        DD = db.rawQuery(" SELECT "+ scoreDBHelper.col_2+" FROM "+ scoreDBHelper.table_scores + " where " +scoreDBHelper.scoreID
                +" = "+i,null);
        EE = db.rawQuery(" SELECT "+ scoreDBHelper.col_2+" FROM "+ scoreDBHelper.table_scores + " where " +scoreDBHelper.scoreID
                +" = "+i,null);
        FF = db.rawQuery(" SELECT "+ scoreDBHelper.col_2+" FROM "+ scoreDBHelper.table_scores + " where " +scoreDBHelper.scoreID
                +" = "+i,null);
        A = AA.getString(0);B = BB.getString(0);C = CC.getString(0);D = DD.getString(0);E = EE.getString(0);F = FF.getString(0);
        listscore.add(A);listscore.add(B);listscore.add(C);listscore.add(D);listscore.add(E);listscore.add(F);
        TextView textView= findViewById(R.id.stl);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, list);
        ListView listView = (ListView)findViewById(R.id.listView);
        textView.setText(A);


        listView.setAdapter(adapter);




    }

    public static ArrayList<String> list()
    {
        return data;
    }

    public static ArrayList<String> result()
    {
        return compare;
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Action not allowed",Toast.LENGTH_SHORT).show();
    }
    public  static ArrayList<Integer> selectedlist(){
        return selectedList;
    }



}
