package com.psulccomsci.civilservicereviewer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;


public class PhilConsCorrect extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String x = PhilippineConstitution.getText();
        String y = PhilippineConstitution.y();
        String concat="";

        if(x.equals(y)){ concat = "Your answer is correct!!!";}else{concat = "The correct answer is "+y;}

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("");
        builder.setMessage(concat)
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();


    }




}