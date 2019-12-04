package com.psulccomsci.civilservicereviewer;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class npdbhelper extends SQLiteOpenHelper {

    public static final String database_name = "Database";
    public static final String table_quest = "Question";
    public static final String table_score = "Score";
    public static final String table_choices = "Choices";
    public static final String table_analogy = "AnalogyChoices";
    public static final String table_mathematics = "MathChoices"; //ok
    public static final String table_numerical = "NumericalChoices"; //ok
    public static final String table_clerical = "ClericalChoices"; //ok
    public static final String table_grammar = "GrammarChoices"; //ok
    public static final String table_readcom = "ReadcomChoices"; //ok
    public static final String table_vocabulary = "VocabularyChoices"; //ok
    public static final String table_philcons = "PhilConsChoices"; //ok
    public static final String table_log_in = "User";
    public static final String table_answer = "Answer";
    public static final String col_ans_1= "ID";
    public static final String col_ans_2= "Ans";
    public static final String col_mathAns= "MathAns";
    public static final String col_readcomAns= "ReadComAns";
    public static final String col_numAns= "NumReasAns";
    public static final String col_clerAns= "ClericalAns";
    public static final String col_vocabAns= "VocabularyAns";
    public static final String col_philconsAns= "PhilConsAns";
    public static final String col_analogyAns= "AnalogyAns";
    public static final String col_grammarAns= "GramAns";
    public static final String col_readcomQ= "ReadingCompreQ";


    //for quiz
    public static final String col_quest_1= "ID";
    public static final String col_quest_2= "Question";
    public static final String col_mathQ= "MathQ";

    public static final String col_analogyQ= "AnalogyQ";
    public static final String col_gramarQ= "GrammarQ";
    public static final String col_vocabularyQ= "VocabularyQ";
    public static final String col_clericalQ= "ClericalQ";
    public static final String col_philconsQ= "PhilConsQ";
    public static final String col_numreasQ= "NumReasQ";
    public static final String col_choice_id= "ID";
    public static final String col_choice_1= "A";
    public static final String col_choice_2= "B";
    public static final String col_choice_3= "C";
    public static final String col_choice_4= "D";
    public static final String col_choice_5= "E";
    public static final String col_solN= "Solution";
    public static final String col_choice_6= "QUEST";
    //for keeping records
    public static final String col_name_id= "ID";
    public static final String col_name_1= "User";
    public static final String col_phone= "phone";
    public static final String col_pass= "password";
    //for scores
    public static final String score_col_id= "ID";
    public static final String score_col_score = "Score";

    String DB_PATH = null;
    private static String DB_NAME = "DatabaseNP";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public npdbhelper(Context context) {
        super(context, DB_NAME, null, 10);
        this.myContext = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
        Log.e("Path 1", DB_PATH + " np");
    }


    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }






    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();

            }
    }
    public Cursor getdata(SQLiteDatabase db){
        String[] Arr = {npdbhelper.col_choice_1};
        Cursor cursor = db.query(table_choices,Arr,null,null,null,null,null);
        return cursor;
    }



    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }


}

