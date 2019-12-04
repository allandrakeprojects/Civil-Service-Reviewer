package com.psulccomsci.civilservicereviewer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreDBHelper extends SQLiteOpenHelper {
    public static final String database_name = "Scores.db";
    public static final String table_scores = "tbl_scores";
    //for quiz
    public static final String scoreID = "ID";
    public static final String col_2 = "SCORES";
    public static final String col_3 = "ANALOGY";
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    ScoreDBHelper scoreDBHelper;
    Cursor scoreCursor;
    String score, com;
    String A="1", B="2", C="3", D="4", E="5", F="6";
    TextView first,second, third, fourth, fifth, sixth;
    int i=1;
    private static String[] list = new String[30];
    private static ArrayList<String> data = new ArrayList<String>(5);
    private static ArrayList<String> listscore = new ArrayList<String>(5);
    private static ArrayList<String> compare = new ArrayList<String>(5);
    private static ArrayList<Integer> selectedList = new ArrayList<Integer>(5);

    public ScoreDBHelper(Context context) {
        super(context, database_name, null, 1);
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

    }

    @Override
    public void onCreate(SQLiteDatabase db2) {

        db2.execSQL(" CREATE TABLE " + table_scores + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, SCORES TEXT, EXAM TEXT, USER TEXT, TYPE TEXT)");
    }

    //if there is existing database it will replace by this new created database
    @Override
    public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion) {
        db2.execSQL(" DROP TABLE IF EXISTS " + table_scores);
    }

    public Cursor getdata(SQLiteDatabase db2) {
        String[] Arr = {ScoreDBHelper.col_2};
        Cursor cursor = db2.query(table_scores, Arr, null, null, null, null, null);
        return cursor;
    }

    public boolean insertdataMATH(int score, String exam, String user, String type) {
        SQLiteDatabase db2 = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("SCORES", score);
        contentValues.put("EXAM", exam);
        contentValues.put("USER", user);
        contentValues.put("TYPE", type);
        long result = db2.insert(table_scores, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean getDatascore(int score){
        SQLiteDatabase db2 = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2, score);
        long result = db2.insert(table_scores, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

}