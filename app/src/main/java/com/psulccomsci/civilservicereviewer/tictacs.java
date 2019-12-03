package com.psulccomsci.civilservicereviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class tictacs extends AppCompatActivity implements View.OnClickListener{
    private Button[][] buttons = new Button[3][3];
    private boolean player1turn=true;
    private int roundCount = 0;
    private int player1Points = 0;
    private int player2Points = 0;

    private TextView textViewplayer1;
    private TextView textViewplayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictacs);

        textViewplayer1 = findViewById(R.id.player1);
        textViewplayer2 = findViewById(R.id.player2);

        for (int i=0;i<3;i++){
            for (int j = 0;j<3;j++) {
                String buttonID = "btn_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);

            }
        }
        Button Reset = findViewById(R.id.reset);
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetboard();
                textViewplayer1.setText("Player 1 : 0");
                textViewplayer2.setText("Player 2 : 0");

            }
        });

    }


    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")){
            return;
        }
        if (player1turn){
            ((Button) v).setText("X");
            v.setBackgroundColor(Color.CYAN);

        }else{
            ((Button) v).setText("O");
            v.setBackgroundColor(Color.GREEN);
        }

        roundCount++;

        if (checking()){
            if(player1turn){
                player1wins();
            }else{
                player2wins();
            }
        }else if (roundCount == 9){
            draw();
        }else{
            player1turn = !player1turn;
        }


    }


    private Boolean checking(){

        String[][] field = new String[3][3];


        for (int i = 0;i<3;i++){
            for (int j = 0;j<3;j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0;i<3;i++){
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")){
                return true;
            }
        }

        for (int i = 0;i<3;i++){
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true;
            }
        }

        for (int i = 0;i<3;i++){
            if (field[0][0].equals(field[1][1])
                    && field[0][0].equals(field[2][2])
                    && !field[0][0].equals("")){
                return true;
            }
        }

        for (int i = 0;i<3;i++){
            if (field[0][2].equals(field[1][1])
                    && field[0][2].equals(field[2][0])
                    && !field[0][2].equals("")){
                return true;
            }
        }
        return false;


    }

    private void player1wins(){
        player1Points++;
        updatePointsText();
        resetboard();

    }

    private void player2wins(){
        player2Points++;
        updatePointsText();
        resetboard();

    }

    private void draw(){
        Toast.makeText(this, "Draw!!!",Toast.LENGTH_SHORT).show();
        resetboard();
    }

    private void resetboard(){
        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1turn=true;


    }

    private void updatePointsText(){
        textViewplayer1.setText("Player 1 : "+player1Points);
        textViewplayer2.setText("Player 2 : "+player2Points);

    }
}
