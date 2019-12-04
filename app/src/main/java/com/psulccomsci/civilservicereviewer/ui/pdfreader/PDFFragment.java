package com.psulccomsci.civilservicereviewer.ui.pdfreader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.psulccomsci.civilservicereviewer.R;
import com.psulccomsci.civilservicereviewer.UserDBhelper;
import com.psulccomsci.civilservicereviewer.reader;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static android.app.Activity.RESULT_OK;

public class PDFFragment extends Fragment {

    private PDFViewModel pdfViewModel;
    private static String book;
    private static File file;
    UserDBhelper userDBhelper;
    SQLiteDatabase db;
    Cursor cursor;
    private String FilePath;
    private String currentPdf;
    private String currentUser;
    View view;
    ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pdfViewModel = ViewModelProviders.of(this).get(PDFViewModel.class);
        view = inflater.inflate(R.layout.books_fragment, container, false);

        ArrayList<String> list = new ArrayList<>();
//        list.add("Civil Service 2019");

        list.add("Add Reviewer");

        if (savedInstanceState == null) {
            Bundle extras = getActivity().getIntent().getExtras();
            if(extras == null) {
                currentUser= null;
            } else {
                currentUser= extras.getString("currentUser");
            }
        } else {
            currentUser= (String) savedInstanceState.getSerializable("currentUser");
        }

        userDBhelper = new UserDBhelper(getContext());
        db = userDBhelper.getWritableDatabase();
        cursor = db.rawQuery(" SELECT "+ UserDBhelper.col_6 +" FROM "+ UserDBhelper.table_name + " WHERE Name = '" + currentUser + "'" ,null);
        cursor.moveToFirst();

        currentPdf = cursor.getString(0) + "";
        if(currentPdf.equals(null)){
            currentPdf = "";
        } else {
            currentPdf += ",";
        }

        currentPdf = currentPdf.replace("null,", "");
        String[] pdfList = currentPdf.split(",");

        for(String pdf : pdfList){
            if(!pdf.equals("") && !pdf.equals(null)){
                String filename=pdf.substring(pdf.lastIndexOf("/")+1).replace(".pdf", "");
                String filenameUpperString = filename.substring(0,1).toUpperCase() + filename.substring(1);

                list.add(filenameUpperString + "                                                                                                      " + pdf);
            }
        }

        // loop
        Log.d("testtesttest", currentPdf);
//        Toast.makeText(getContext(), currentPdf, Toast.LENGTH_SHORT).show();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.listview_layout, R.id.listview_textview, list);


//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
//                android.R.layout.simple_list_item_1, android.R.id.text1, list);
        listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setBackgroundColor(Color.WHITE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub

                if(adapter.getItem(position).equals("Add Reviewer")){
                    checkPermissionsAndOpenFilePicker();
                    return;
                }

                String[] splitPdf = adapter.getItem(position).split("                                                                                                      ");


                File value= new File(splitPdf[1]);
                file=value;
                Intent intent = new Intent(getContext(), reader.class);
                startActivity(intent);

            }
        });



        return view;
    }



    private void checkPermissionsAndOpenFilePicker() {
        String permission = Manifest.permission.READ_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
                showError();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, 1);
            }
        } else {
            openFilePicker();
        }
    }

    private void showError() {
        Toast.makeText(getContext(), "Allow external storage reading", Toast.LENGTH_SHORT).show();
    }

    private void openFilePicker() {
        new MaterialFilePicker()
                .withSupportFragment(this)
                .withRequestCode(1)
                .withHiddenFiles(true)
                .withTitle("Choose PDF Reviewer")
                .start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openFilePicker();
                } else {
                    showError();
                }
            }
        }
    }

    /**
     * Handle File Path of Selected PDF
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            FilePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            File filea = new File(FilePath);

            if (filea.getAbsolutePath() != null) {
                Log.d("Path: ", filea + "");
//                Toast.makeText(getContext(), "Picked file: " + filea, Toast.LENGTH_LONG).show();
                // get file path
                // get recent books of logged in user
                // update

                userDBhelper.update(currentUser, currentPdf + filea);

                // refresh list

                currentPdf = "";
                listView.setAdapter(null);

                ArrayList<String> list = new ArrayList<>();
                list.add("Add Reviewer");

                cursor = db.rawQuery(" SELECT "+ UserDBhelper.col_6 +" FROM "+ UserDBhelper.table_name + " WHERE Name = '" + currentUser + "'" ,null);
                cursor.moveToFirst();

                currentPdf = cursor.getString(0) + "";
                if(currentPdf.equals(null)){
                    currentPdf = "";
                } else {
                    currentPdf += ",";
                }

                currentPdf = currentPdf.replace("null,", "");
                String[] pdfList = currentPdf.split(",");

                for(String pdf : pdfList){
                    if(!pdf.equals("") && !pdf.equals(null)){
                        String filename=pdf.substring(pdf.lastIndexOf("/")+1).replace(".pdf", "");
                        String filenameUpperString = filename.substring(0,1).toUpperCase() + filename.substring(1);

                        list.add(filenameUpperString + "                                                                                                      " + pdf);
                    }
                }

                // loop
                Log.d("testtesttest", currentPdf);
//                Toast.makeText(getContext(), currentPdf, Toast.LENGTH_SHORT).show();



                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        R.layout.listview_layout, R.id.listview_textview, list);


//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
//                android.R.layout.simple_list_item_1, android.R.id.text1, list);
                listView = (ListView)view.findViewById(R.id.listView);
                listView.setAdapter(adapter);
                listView.setBackgroundColor(Color.WHITE);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        // TODO Auto-generated method stub

                        if(adapter.getItem(position).equals("Add Reviewer")){
                            checkPermissionsAndOpenFilePicker();
                            return;
                        }

                        String[] splitPdf = adapter.getItem(position).split("                                                                                                      ");


                        File value= new File(splitPdf[1]);
                        file=value;
                        Intent intent = new Intent(getContext(), reader.class);
                        startActivity(intent);

                    }
                });
            }
        }
    }

    public static String title(){
        return book;
    }

    public static File file(){
        return file;
    }
}
