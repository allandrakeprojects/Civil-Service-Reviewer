package com.psulccomsci.civilservicereviewer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.psulccomsci.civilservicereviewer.ui.register.User;


public class UserDBhelper extends SQLiteOpenHelper {
    public static final String user_db_name = "User.db";
    public static final String table_name = "User";
    public static final String col_1= "ID";
    public static final String col_2 = "Name";
    public static final String col_3 = "Phone";
    public static final String col_4 = "Password";
    public static final String col_5 = "BaseInfo";
    public static final String col_6 = "Books";


    public UserDBhelper(Context context) {
        super(context, user_db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+table_name+" (ID INTEGER  PRIMARY KEY AUTOINCREMENT, Name TEXT, Phone TEXT, Password TEXT, BaseInfo TEXT, Books TEXT)");

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

    public boolean update(String Name, String Books){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_6, Books);
        long result = db.update(table_name, contentValues, "Name= + '" + Name + "'", null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int checkUser(User us)
    {
        int id=-1;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT id FROM user WHERE name=? AND password=?",new String[]{us.getName(),us.getPassword()});
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            id=cursor.getInt(0);
            cursor.close();
        }
        return id;
    }

}
