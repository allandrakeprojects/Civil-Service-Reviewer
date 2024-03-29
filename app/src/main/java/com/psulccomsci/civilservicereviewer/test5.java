package com.psulccomsci.civilservicereviewer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class test5 extends AppCompatActivity{

    String textdata;
    RadioGroup radioGroup;
    RadioButton radioButton;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    ScoreDBHelper scoreDBHelper;
    RadioButton first,second, third, fourth, fifth;
    npdbhelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor AA, BB, CC, DD, EE,QQ, ANS, solN;
    String A, B, C, D, E, Q, ans,com, solution;
    TextView item;
    int i=1, countScore=0;
    String temp;
    TextView quest;
    TextView hour;
    CountDownTimer countDownTimer,countDownTimermin;
    TextView timerText;
    Button next;
    private static long remin;
    private static long rem;
    private static String[] list = new String[30];
    private static String text;
    private Button submit;
    private static String keytext;
    private static String restext;
    private static int selected;
    private static ArrayList<String> data = new ArrayList<String>(5);
    private static ArrayList<String> listdata = new ArrayList<String>(5);
    private static ArrayList<String> sub = new ArrayList<String>(5);
    private static ArrayList<String> key = new ArrayList<String>(5);
    private static ArrayList<String> compare = new ArrayList<String>(5);
    private static ArrayList<Integer> selectedList = new ArrayList<Integer>(5);
    private  static String tempx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        super.onCreate(savedInstanceState);
        compare = test4.result_np();
        setContentView(R.layout.activity_main);
        timerText = findViewById(R.id.timer);
        hour = findViewById(R.id.hour);
        item = findViewById(R.id.item);
        scoreDBHelper = new ScoreDBHelper(this);

        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished>10000){
                timerText.setText(millisUntilFinished/1000+""); }else {timerText.setText("0"+millisUntilFinished/1000+"");}
                rem = millisUntilFinished;
            }
            @Override
            public void onFinish() {
                countDownTimer.start();
            }

        };
        // Shared Preference ------------
        SharedPreferences sharedPref = getSharedPreferences("mypref", 0);
        long currentTime = sharedPref.getLong("currentTime", 3600000);
        // End ------------
        countDownTimermin = new CountDownTimer(currentTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                hour.setText(millisUntilFinished/60000+" : ");
                remin = millisUntilFinished;

                int minutes = Integer.parseInt(hour.getText().toString().replace(" : ", ""));
                long millis = minutes * 60 * 1000;

                // Shared Preference ------------
                SharedPreferences sharedPref = getSharedPreferences("mypref", 0);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putLong("currentTime", millis);
                editor.commit();
                // End ------------
            }
            @Override
            public void onFinish() {
                hour.setText("00 : ");
            }
        };
        countDownTimer.start();
        countDownTimermin.start();

        radioGroup = findViewById(R.id.radioGroup);
        quest = findViewById(R.id.quest);
        first = findViewById(R.id.radioButton1);
        second = findViewById(R.id.radioButton2);
        third = findViewById(R.id.radioButton3);
        fourth = findViewById(R.id.radioButton4);
        fifth = findViewById(R.id.radioButton5);
        submit = findViewById(R.id.submit);
        openHelper = new npdbhelper(this);
        next = findViewById(R.id.next);

        databaseHelper = new npdbhelper(getApplicationContext());
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        AA = databaseHelper.getdata(sqLiteDatabase);
        db = openHelper.getWritableDatabase();

        int min = 1;
        int max = 9;
        int random = new Random().nextInt((max - min) + 1) + min;
        ANS = db.rawQuery(" SELECT "+ npdbhelper.col_clerAns+" FROM "+ npdbhelper.table_answer + " where " +npdbhelper.col_ans_1
                +" = "+random,null);
        AA = db.rawQuery(" SELECT "+ npdbhelper.col_choice_1+" FROM "+ npdbhelper.table_clerical + " where " +npdbhelper.col_choice_id
                +" = "+random,null);
        BB = db.rawQuery(" SELECT "+ npdbhelper.col_choice_2+" FROM "+ npdbhelper.table_clerical + " where " +npdbhelper.col_choice_id
                +" = "+random,null);
        CC = db.rawQuery(" SELECT "+ npdbhelper.col_choice_3+" FROM "+ npdbhelper.table_clerical + " where " +npdbhelper.col_choice_id
                +" = "+random,null);
        DD = db.rawQuery(" SELECT "+ npdbhelper.col_choice_4+" FROM "+ npdbhelper.table_clerical + " where " +npdbhelper.col_choice_id
                +" = "+random,null);
        EE = db.rawQuery(" SELECT "+ npdbhelper.col_choice_5+" FROM "+ npdbhelper.table_clerical + " where " +npdbhelper.col_choice_id
                +" = "+random,null);
        QQ = db.rawQuery(" SELECT "+ npdbhelper.col_clericalQ+" FROM "+ npdbhelper.table_quest + " where " +npdbhelper.col_quest_1
                +" = "+random,null);
        //This area is for adding the generated Math solutions, but for now temporary unavailable.
//        solN = db.rawQuery(" SELECT "+ npdbhelper.col_solN+" FROM "+ npdbhelper.table_clerical + " where " +npdbhelper.col_choice_id
//                        +" = "+random,null);
//        solN.moveToFirst();solution = solN.getString(0);

        AA.moveToFirst();BB.moveToFirst();CC.moveToFirst();DD.moveToFirst();EE.moveToFirst();QQ.moveToFirst();ANS.moveToFirst();
        A = AA.getString(0);B = BB.getString(0);C = CC.getString(0);D = DD.getString(0);E = EE.getString(0);Q = QQ.getString(0);ans = ANS.getString(0);
        first.setText(A);second.setText(B);third.setText(C);fourth.setText(D);fifth.setText(E);quest.setText(Q);
        temp = ""+i+"";
        item.setText(temp);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int SelectedId = radioGroup.getCheckedRadioButtonId();
                selected = SelectedId;
                selectedList.add(selected);
                if (SelectedId>0){
                radioButton = findViewById(SelectedId);
                text = radioButton.getText().toString();
                textdata = radioButton.getText().toString();
                String x = radioButton.getText().toString();
                databaseHelper = new npdbhelper(getApplicationContext());
                sqLiteDatabase = databaseHelper.getReadableDatabase();
                AA = databaseHelper.getdata(sqLiteDatabase);
                db = openHelper.getWritableDatabase();
                sub.add(x);
                textdata=i+".   "+ textdata;
                text=i+".   "+ text;
                data.add(text);
                listdata.add(textdata);
                key.add(keytext);
                    if(ans.equals(x)){
                        countScore++;
                        restext=text+"  - Correct";
                        compare.add(restext);
                        keytext =ans + " and your answer is correct.";
                    }else{
                        restext=text+"  - Wrong";
                        compare.add(restext);
                        keytext =ans;
                        //+"\n\n"+"The solution is shown below."+"\n\n"+solution
                    }
                if (i<10) {
                    i++;

                    int min = 1;
                    int max = 9;
                    int random = new Random().nextInt((max - min) + 1) + min;
                    ANS = db.rawQuery(" SELECT "+ npdbhelper.col_clerAns+" FROM "+ npdbhelper.table_answer + " where " +npdbhelper.col_ans_1
                            +" = "+random,null);
                    AA = db.rawQuery(" SELECT "+ npdbhelper.col_choice_1+" FROM "+ npdbhelper.table_clerical + " where " +npdbhelper.col_choice_id
                            +" = "+random,null);
                    BB = db.rawQuery(" SELECT "+ npdbhelper.col_choice_2+" FROM "+ npdbhelper.table_clerical + " where " +npdbhelper.col_choice_id
                            +" = "+random,null);
                    CC = db.rawQuery(" SELECT "+ npdbhelper.col_choice_3+" FROM "+ npdbhelper.table_clerical + " where " +npdbhelper.col_choice_id
                            +" = "+random,null);
                    DD = db.rawQuery(" SELECT "+ npdbhelper.col_choice_4+" FROM "+ npdbhelper.table_clerical + " where " +npdbhelper.col_choice_id
                            +" = "+random,null);
                    EE = db.rawQuery(" SELECT "+ npdbhelper.col_choice_5+" FROM "+ npdbhelper.table_clerical + " where " +npdbhelper.col_choice_id
                            +" = "+random,null);
                    QQ = db.rawQuery(" SELECT "+ npdbhelper.col_clericalQ+" FROM "+ npdbhelper.table_quest + " where " +npdbhelper.col_quest_1
                            +" = "+random,null);
                AA.moveToFirst();BB.moveToFirst();CC.moveToFirst();DD.moveToFirst();EE.moveToFirst();QQ.moveToFirst();ANS.moveToFirst();
                A = AA.getString(0);B = BB.getString(0);C = CC.getString(0);D = DD.getString(0);E = EE.getString(0);Q = QQ.getString(0);ans = ANS.getString(0);
                first.setText(A);second.setText(B);third.setText(C);fourth.setText(D);fifth.setText(E);quest.setText(Q);
                }else{
//                        Toast.makeText(test5.this, "CLERICAL", Toast.LENGTH_SHORT).show();
                    // Shared Preference ------------
                    SharedPreferences sharedPref = getSharedPreferences("mypref", 0);
                    String currentUser = sharedPref.getString("currentUser", "");
                    // End ------------

                    scoreDBHelper.insertdataMATH(countScore, "CLERICAL", currentUser, "1");

                    Intent intent=new Intent(getApplicationContext(),test6ExamTitle.class);
                    startActivity(intent);
                }

                tempx = "Analogy";
                radioGroup.clearCheck();
                }else{
                    openDialognoAnswer();
                }temp = ""+i+"";
                item.setText(temp);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    selected = 0;
                    selectedList.add(selected);
                   databaseHelper = new npdbhelper(getApplicationContext());
                    sqLiteDatabase = databaseHelper.getReadableDatabase();
                    AA = databaseHelper.getdata(sqLiteDatabase);
                    db = openHelper.getWritableDatabase();
                    text=i+".   You Skipped";
                    textdata=i+".   You Skipped";
                    keytext = ans;
                    data.add(text);
                    listdata.add(textdata);
                    key.add(keytext);
                        if(keytext.equals(text)){
                            restext=text+"  - Correct";
                            compare.add(restext);
                        }else{
                            restext=text+"  - Wrong";
                            compare.add(restext);
                        }

                    if (i<10) {
                        i++;

                        int min = 1;
                        int max = 9;
                        int random = new Random().nextInt((max - min) + 1) + min;
                        ANS = db.rawQuery(" SELECT "+ npdbhelper.col_clerAns+" FROM "+ npdbhelper.table_answer + " where " +npdbhelper.col_ans_1
                                +" = "+random,null);
                        AA = db.rawQuery(" SELECT "+ npdbhelper.col_choice_1+" FROM "+ npdbhelper.table_clerical + " where " +npdbhelper.col_choice_id
                                +" = "+random,null);
                        BB = db.rawQuery(" SELECT "+ npdbhelper.col_choice_2+" FROM "+ npdbhelper.table_clerical + " where " +npdbhelper.col_choice_id
                                +" = "+random,null);
                        CC = db.rawQuery(" SELECT "+ npdbhelper.col_choice_3+" FROM "+ npdbhelper.table_clerical + " where " +npdbhelper.col_choice_id
                                +" = "+random,null);
                        DD = db.rawQuery(" SELECT "+ npdbhelper.col_choice_4+" FROM "+ npdbhelper.table_clerical + " where " +npdbhelper.col_choice_id
                                +" = "+random,null);
                        EE = db.rawQuery(" SELECT "+ npdbhelper.col_choice_5+" FROM "+ npdbhelper.table_clerical + " where " +npdbhelper.col_choice_id
                                +" = "+random,null);
                        QQ = db.rawQuery(" SELECT "+ npdbhelper.col_clericalQ+" FROM "+ npdbhelper.table_quest + " where " +npdbhelper.col_quest_1
                                +" = "+random,null);
                    AA.moveToFirst();BB.moveToFirst();CC.moveToFirst();DD.moveToFirst();EE.moveToFirst();QQ.moveToFirst();ANS.moveToFirst();
                    A = AA.getString(0);B = BB.getString(0);C = CC.getString(0);D = DD.getString(0);E = EE.getString(0);Q = QQ.getString(0);ans = ANS.getString(0);
                    first.setText(A);second.setText(B);third.setText(C);fourth.setText(D);fifth.setText(E);quest.setText(Q);


                    }else{
//                        Toast.makeText(test5.this, "CLERICAL", Toast.LENGTH_SHORT).show();
                        // Shared Preference ------------
                        SharedPreferences sharedPref = getSharedPreferences("mypref", 0);
                        String currentUser = sharedPref.getString("currentUser", "");
                        // End ------------

                        scoreDBHelper.insertdataMATH(countScore, "CLERICAL", currentUser, "1");

                        Intent intent=new Intent(getApplicationContext(),test6ExamTitle.class);
                        startActivity(intent);
                        countDownTimer.cancel();
                        countDownTimermin.cancel();

                        tempx = "Analogy";
                    }

                    radioGroup.clearCheck();
                    temp = ""+i+"";
                    item.setText(temp);
            }
        });
    }
    public void openDialognoAnswer(){
        Noanswer noanswer = new Noanswer();
        noanswer.show(getSupportFragmentManager(), "Noanswer");
    }

    public static String getText()
    {
        return text;
    }

    public static ArrayList<String> listdata()
    {
        return listdata;
    }
    public static ArrayList<String> list()
    {
        return data;
    }
    public static ArrayList<String> listanswer()
    {
        return key;
    }
    public static ArrayList<String> subans()
    {
        return sub;
    }

    public static ArrayList<String> result_np()
    {
        return compare;
    }

    public static String y(){return keytext;}
    public static String solN(){return keytext;}

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Action not allowed",Toast.LENGTH_SHORT).show();
    }

    public  static long timeremin(){
        return remin;
    }

    public  static long timerem(){
        return rem;
    }

    public  static ArrayList<Integer> selectedlist(){
        return selectedList;
    }

    public  static String temp(){
        return tempx;
    }

}
