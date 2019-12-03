package com.psulccomsci.civilservicereviewer.ui.pdfreader;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.psulccomsci.civilservicereviewer.R;
import com.psulccomsci.civilservicereviewer.reader;

import java.util.ArrayList;

public class PDFFragment extends Fragment {

    private PDFViewModel pdfViewModel;
    private static String book;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pdfViewModel = ViewModelProviders.of(this).get(PDFViewModel.class);
        View view = inflater.inflate(R.layout.books_fragment, container, false);

        ArrayList<String> list = new ArrayList<>();
        list.add("Civil Service 2019");

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
                Intent intent = new Intent(getContext(), reader.class);
                startActivity(intent);

            }
        });



        return view;
    }

    public static String title(){
        return book;

    }
}
