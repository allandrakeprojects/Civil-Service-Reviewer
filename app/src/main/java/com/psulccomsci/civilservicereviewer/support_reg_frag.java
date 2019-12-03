package com.psulccomsci.civilservicereviewer;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class support_reg_frag extends AppCompatActivity {
    private static TextView textView;
    private static String name;
    SQLiteOpenHelper openHelper;
    dbhelper dbhelper;
    SQLiteDatabase db;
    Cursor cname;
    int i=0;
    TextView nameText;
    String names;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_reg_frag);
        dbhelper = new dbhelper(this);
        db = openHelper.getWritableDatabase();
        cname = db.rawQuery(" SELECT "+ dbhelper.col_name_1+" FROM "+ dbhelper.table_log_in + " where " +dbhelper.col_name_id
                +" = "+i,null);
        nameText = findViewById(R.id.name);
        names = (String) nameText.getText();




    }
    public static String name(){
        return name;
    }


    private void validate(String userName, String password){
        if (userName=="Admin" &&password=="1234"){
            Intent intent = new Intent(getApplicationContext(), user_navi.class);
            startActivity(intent);
        }
    }
}
