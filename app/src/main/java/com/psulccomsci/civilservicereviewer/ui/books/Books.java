package com.psulccomsci.civilservicereviewer.ui.books;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.psulccomsci.civilservicereviewer.R;
import com.psulccomsci.civilservicereviewer.reader;
import com.psulccomsci.civilservicereviewer.reader_pdf_home;

import java.io.File;
import java.util.ArrayList;

public class Books extends Fragment {
    private BooksViewModel mViewModel;
    private static String book;
    private static File file;

    public static Books newInstance() {
        return new Books();
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,
                              Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.books_fragment, container, false);

        ArrayList<String> list = new ArrayList<>();
        list.add("Civil Service 2019");
        list.add("Civil Service Reviewer");


        //listItem = getResources().getStringArray(R.array.array_technology);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, list);
        ListView listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setBackgroundColor(Color.WHITE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                String value=adapter.getItem(position);
                book=value;
                Intent intent = new Intent(getContext(), reader_pdf_home.class);
                startActivity(intent);

            }
        });



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BooksViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static String title(){
        return book;

    }

    public static File file(){
        return file;

    }


}
