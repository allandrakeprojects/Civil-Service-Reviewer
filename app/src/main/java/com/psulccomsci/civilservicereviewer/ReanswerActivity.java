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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class ReanswerActivity extends AppCompatActivity{
    RadioGroup radioGroup;
    RadioButton radioButton2;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    private Button submit;
    RadioButton first,second, third, fourth, fifth;
    dbhelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor AA, BB, CC, DD, EE,QQ, ANS;
    String A, B, C, D, E, Q, ans,com;
    int i=1, j=0;
    TextView quest;
    TextView hour;
    CountDownTimer countDownTimer,countDownTimermin;
    TextView timerText;
    Button next;
    private static String textdata;
    private static int pos;
    private static String text;
    private static String check;
    private static String restext;
    private static ArrayList<String> data = new ArrayList<String>(5);
    private static ArrayList<String> subans = new ArrayList<String>(5);
    private static ArrayList<String> key = new ArrayList<String>(5);
    private static ArrayList<String> compare = new ArrayList<String>(5);
    private static ArrayList<String> listItem = new ArrayList<String>(5);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reanswer);
        timerText = findViewById(R.id.timer);
        hour = findViewById(R.id.hour);

        long remtime= statview.remtime();
        countDownTimer = new CountDownTimer(remtime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished>10000){
                timerText.setText(millisUntilFinished/1000+"");}else {timerText.setText("0"+millisUntilFinished/1000+"");}
            }
            @Override
            public void onFinish() {
                countDownTimer.start();
            }

        };
        long remtimemin=statview.remintime();
        countDownTimermin = new CountDownTimer(remtimemin, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                hour.setText(millisUntilFinished/60000+" : ");
            }
            @Override
            public void onFinish() {
                hour.setText("00 : ");
            }
        };
        countDownTimer.start();
        countDownTimermin.start();

        radioGroup = findViewById(R.id.radioGroup2);
        quest = findViewById(R.id.quest);
        first = findViewById(R.id.radioButtona);
        second = findViewById(R.id.radioButtonb);
        third = findViewById(R.id.radioButtonc);
        fourth = findViewById(R.id.radioButtond);
        fifth = findViewById(R.id.radioButtone);
        submit = findViewById(R.id.submit);
        openHelper = new dbhelper(this);
        next = findViewById(R.id.next);

        databaseHelper = new dbhelper(getApplicationContext());
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        AA = databaseHelper.getdata(sqLiteDatabase);
        db = openHelper.getWritableDatabase();
        i=statview.position();
        listItem = MainActivity.result();
        j=i;
        i=i+1;
        pos= statview.position();
        ANS = db.rawQuery(" SELECT "+ dbhelper.col_ans_2+" FROM "+ dbhelper.table_answer + " where " +dbhelper.col_ans_1
                +" = "+i,null);
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
        AA.moveToFirst();BB.moveToFirst();CC.moveToFirst();DD.moveToFirst();EE.moveToFirst();QQ.moveToFirst();ANS.moveToFirst();
        A = AA.getString(0);B = BB.getString(0);C = CC.getString(0);D = DD.getString(0);E = EE.getString(0);Q = QQ.getString(0);ans = ANS.getString(0);
        first.setText(A);second.setText(B);third.setText(C);fourth.setText(D);fifth.setText(E);quest.setText(Q);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int SelectedId = radioGroup.getCheckedRadioButtonId();
                if (SelectedId>0){
                radioButton2 = findViewById(SelectedId);
                String x = radioButton2.getText().toString();
                textdata = radioButton2.getText().toString();
                text = radioButton2.getText().toString();
                textdata=i+".   "+ textdata;
                if (x.equals(ans)){check=" - Correct";}else{check = " - Wrong";}
                text=i+".   "+ text+check;
                data.add(text);
                Intent intent = new Intent(getApplicationContext(),statview.class);
                startActivity(intent);
                }else{
                    openDialognoAnswer();
                }
            }
        });

    }

    public void openDialognoAnswer(){
        Noanswer noanswer = new Noanswer();
        noanswer.show(getSupportFragmentManager(), "Noanswer");
    }
    public static String getText()
    {
        return textdata;
    }

    public static String getText2()
    {
        return text;
    }


    public static int pos(){

        return pos;
    }
    public static ArrayList<String> subans()
    {
        return listItem;
    }
    public static ArrayList<String> datalist()
    {
        return data;
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Action not allowed.",Toast.LENGTH_SHORT).show();
    }
}
