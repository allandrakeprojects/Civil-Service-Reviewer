package com.psulccomsci.civilservicereviewer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UserDBhelper extends SQLiteOpenHelper {
    public static final String user_db_name = "User.db";
    public static final String table_name = "User";
    public static final String col_1= "ID";
    public static final String col_2 = "Name";
    public static final String col_3 = "Phone";
    public static final String col_4 = "Password";
    public static final String col_5 = "BaseInfo";


    public UserDBhelper(Context context) {
        super(context, user_db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+table_name+" (ID INTEGER  PRIMARY KEY AUTOINCREMENT, Name TEXT, Phone TEXT, Password TEXT, BaseInfo TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table_name);
    }


    public boolean insert(String Name, String Phone, String Password, String BaseInfo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2, Name);
        contentValues.put(col_3, Phone);
        contentValues.put(col_4, Password);
        contentValues.put(col_5, BaseInfo);
        long result = db.insert(table_name, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }


    }
}
