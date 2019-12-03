package com.psulccomsci.civilservicereviewer;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class scoreMain extends AppCompatActivity{


    SQLiteDatabase db;
    ScoreDBHelper scoreDBHelper;
    Cursor scoreCursor;
    String score, com;
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

        scoreDBHelper = new ScoreDBHelper(this);
        db = scoreDBHelper.getWritableDatabase();
        scoreCursor = db.rawQuery(" SELECT "+ scoreDBHelper.col_2+" FROM "+ scoreDBHelper.table_scores + " where " +scoreDBHelper.scoreID
                +" = "+i,null);
        scoreCursor.moveToFirst();
        score = scoreCursor.getString(0);

        for (i=1;i<7;i++){
            scoreCursor = db.rawQuery(" SELECT "+ scoreDBHelper.col_2+" FROM "+ scoreDBHelper.table_scores + " where " +scoreDBHelper.scoreID
                    +" = "+i,null);
            scoreCursor.moveToFirst();
            score = scoreCursor.getString(0);
            listscore.add(score);
        }




    }

    public static ArrayList<String> listscore()
    {
        return listscore;
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
