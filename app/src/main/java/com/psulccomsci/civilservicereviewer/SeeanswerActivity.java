package com.psulccomsci.civilservicereviewer;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class SeeanswerActivity extends AppCompatActivity{
    RadioGroup radioGroup;
    RadioButton radioButton;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    TextView item;
    RadioButton first,second, third, fourth, fifth;
    dbhelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor AA, BB, CC, DD, EE,QQ, ANS;
    String A, B, C, D, E, Q, ans,com;
    String temp;
    int i=1;
    TextView quest;
    Button next;
    Button exit;
    int SelectedId;
    private static int pos;
    private static String text;
    private static ArrayList<Integer> selectedIdList = new ArrayList<Integer>(5);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeanswer);
        item = findViewById(R.id.item2);
        radioGroup = findViewById(R.id.radioGroup);
        quest = findViewById(R.id.quest);
        first = findViewById(R.id.radioButton1);
        second = findViewById(R.id.radioButton2);
        third = findViewById(R.id.radioButton3);
        fourth = findViewById(R.id.radioButton4);
        fifth = findViewById(R.id.radioButton5);
        openHelper = new dbhelper(this);
        next = findViewById(R.id.next);
        exit = findViewById(R.id.exit);

        databaseHelper = new dbhelper(getApplicationContext());
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        AA = databaseHelper.getdata(sqLiteDatabase);
        db = openHelper.getWritableDatabase();
        i = result.position();
        i = i + 1;
        temp = ""+i+"";
        item.setText(temp);
        pos = statview.position();
        AA = db.rawQuery(" SELECT "+ dbhelper.col_choice_1+" FROM "+ dbhelper.table_choices + " where " +dbhelper.col_choice_id
                +" = "+i,null);
        BB = db.rawQuery(" SELECT "+ dbhelper.col_choice_2+" FROM "+ dbhelper.table_choices + " where " +dbhelper.col_choice_id
                +" = "+i,null);
        CC = db.rawQuery(" SELECT "+ dbhelper.col_choice_3+" FROM "+ dbhelper.table_choices + " where " +dbhelper.col_choice_id
                +" = "+i,null);
        DD = db.rawQuery(" SELECT "+ dbhelper.col_choice_4+" FROM "+ dbhelper.table_choices + " where " +dbhelper.col_choice_id
                +" = "+i,null);
        EE = db.rawQuery(" SELECT "+ dbhelper.col_choice_5+" FROM "+ dbhelper.table_choices + " where " +dbhelper.col_choice_id
                +" = "+i,null);
        QQ = db.rawQuery(" SELECT "+ dbhelper.col_quest_2+" FROM "+ dbhelper.table_quest + " where " +dbhelper.col_quest_1
                +" = "+i,null);
        AA.moveToFirst();
        BB.moveToFirst();
        CC.moveToFirst();
        DD.moveToFirst();
        EE.moveToFirst();
        QQ.moveToFirst();
        A = AA.getString(0);
        B = BB.getString(0);
        C = CC.getString(0);
        D = DD.getString(0);
        E = EE.getString(0);
        Q = QQ.getString(0);
        first.setText(A);
        second.setText(B);
        third.setText(C);
        fourth.setText(D);
        fifth.setText(E);
        quest.setText(Q);
        SelectedId = result.selected();
        radioGroup.check(SelectedId);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
                i = i + 1;
                temp = ""+i+"";
                item.setText(temp);
                pos = statview.position();
                AA = db.rawQuery(" SELECT "+ dbhelper.col_choice_1+" FROM "+ dbhelper.table_choices + " where " +dbhelper.col_choice_id
                        +" = "+i,null);
                BB = db.rawQuery(" SELECT "+ dbhelper.col_choice_2+" FROM "+ dbhelper.table_choices + " where " +dbhelper.col_choice_id
                        +" = "+i,null);
                CC = db.rawQuery(" SELECT "+ dbhelper.col_choice_3+" FROM "+ dbhelper.table_choices + " where " +dbhelper.col_choice_id
                        +" = "+i,null);
                DD = db.rawQuery(" SELECT "+ dbhelper.col_choice_4+" FROM "+ dbhelper.table_choices + " where " +dbhelper.col_choice_id
                        +" = "+i,null);
                EE = db.rawQuery(" SELECT "+ dbhelper.col_choice_5+" FROM "+ dbhelper.table_choices + " where " +dbhelper.col_choice_id
                        +" = "+i,null);
                QQ = db.rawQuery(" SELECT "+ dbhelper.col_quest_2+" FROM "+ dbhelper.table_quest + " where " +dbhelper.col_quest_1
                        +" = "+i,null);
                AA.moveToFirst();
                BB.moveToFirst();
                CC.moveToFirst();
                DD.moveToFirst();
                EE.moveToFirst();
                QQ.moveToFirst();
                A = AA.getString(0);
                B = BB.getString(0);
                C = CC.getString(0);
                D = DD.getString(0);
                E = EE.getString(0);
                Q = QQ.getString(0);
                first.setText(A);
                second.setText(B);
                third.setText(C);
                fourth.setText(D);
                fifth.setText(E);
                quest.setText(Q);
                selectedIdList = MainActivity.selectedlist();
                SelectedId=selectedIdList.get(i);
                if (SelectedId!=0){radioGroup.check(SelectedId);}else{radioGroup.clearCheck();}

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),result.class);
                startActivity(intent);
            }
        });

    }


}
