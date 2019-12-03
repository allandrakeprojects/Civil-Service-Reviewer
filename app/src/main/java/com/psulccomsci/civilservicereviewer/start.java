package com.psulccomsci.civilservicereviewer;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class start extends AppCompatActivity {
    Cursor c = null;
    int i=0, last=0;
    SQLiteDatabase db;
    Cursor cursor, cursornames;
    String base=null;
    private static String names=null;
    UserDBhelper userDBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        userDBhelper = new UserDBhelper(this);
        db = userDBhelper.getWritableDatabase();
        cursor = db.rawQuery(" SELECT "+ UserDBhelper.col_5+" FROM "+ UserDBhelper.table_name ,null);
        cursornames = db.rawQuery(" SELECT "+ UserDBhelper.col_2+" FROM "+ UserDBhelper.table_name ,null);

        if (cursor.getCount()==0){
            ((ImageButton) findViewById(R.id.chat)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        dbhelper myDbHelper = new dbhelper(start.this);
                        try {
                            myDbHelper.createDataBase();
                        } catch (IOException ioe) {
                            throw new Error("Unable to create database");
                        }
                        try {
                            myDbHelper.openDataBase();
                        } catch (SQLException sqle) {
                            throw sqle;
                        }
                        Intent intent = new Intent(getApplicationContext(), preload.class);
                        startActivity(intent);

                }

            });
        }else {
            cursor.moveToLast();base = cursor.getString(0);
            cursornames.moveToLast();names = cursornames.getString(0);
        ((ImageButton) findViewById(R.id.chat)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String information = getHardwareAndSoftwareInfo();
                if (!base.equals(information)) {
                    dbhelper myDbHelper = new dbhelper(start.this);
                    try {
                        myDbHelper.createDataBase();
                    } catch (IOException ioe) {
                        throw new Error("Unable to create database");
                    }
                    try {
                        myDbHelper.openDataBase();
                    } catch (SQLException sqle) {
                        throw sqle;
                    }
                    Intent intent = new Intent(getApplicationContext(), preload.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Welcome back! Have a nice day!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), user_navi.class);
                    startActivity(intent);

                }
            }

        });
        }


        }
    private String getHardwareAndSoftwareInfo() {

        return getString(R.string.serial) + " " + Build.SERIAL + "\n" +
                getString(R.string.model) + " " + Build.MODEL + "\n" +
                getString(R.string.id) + " " + Build.ID + "\n" +
                getString(R.string.manufacturer) + " " + Build.MANUFACTURER + "\n" +
                getString(R.string.brand) + " " + Build.BRAND + "\n" +
                getString(R.string.type) + " " + Build.TYPE + "\n" +
                getString(R.string.user) + " " + Build.USER + "\n" +
                getString(R.string.base) + " " + Build.VERSION_CODES.BASE + "\n" +
                getString(R.string.incremental) + " " + Build.VERSION.INCREMENTAL + "\n" +
                getString(R.string.sdk) + " " + Build.VERSION.SDK + "\n" +
                getString(R.string.board) + " " + Build.BOARD + "\n" +
                getString(R.string.host) + " " + Build.HOST + "\n" +
                getString(R.string.fingerprint) + " " + Build.FINGERPRINT + "\n" +
                getString(R.string.versioncode) + " " + Build.VERSION.RELEASE;
    }


    public static String names(){
        return names;
    }


}
