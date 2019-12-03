package com.psulccomsci.civilservicereviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class graph extends AppCompatActivity {
    SQLiteDatabase db;
    ScoreDBHelper scoreDBHelper;
    Cursor cursor;
    String value;
    int i=1;
    private static ArrayList<String> scorelist2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int j=3;
        db = scoreDBHelper.getReadableDatabase();
        while (i<j){
            j= cursor.getColumnCount();
        cursor = db.rawQuery(" SELECT "+ scoreDBHelper.col_2+" FROM "+ scoreDBHelper.table_scores+ " where " +scoreDBHelper.scoreID
                +" = "+i,null);
        cursor.moveToFirst();
        value = cursor.getString(0);
        scorelist2.add(value);}


    }

    public static ArrayList<String> scorelist2(){ return scorelist2;}

}
