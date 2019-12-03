package com.psulccomsci.civilservicereviewer.ui.logout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.psulccomsci.civilservicereviewer.R;
import com.psulccomsci.civilservicereviewer.UserDBhelper;
import com.psulccomsci.civilservicereviewer.ui.books.BooksViewModel;
import com.psulccomsci.civilservicereviewer.user_navi;
import com.psulccomsci.civilservicereviewer.visitornavi;

public class Logout extends Fragment {
    private BooksViewModel mViewModel;
    public static Logout newInstance() {
        return new Logout();
    }

    Button yes;
    Button no;
    UserDBhelper userDBhelper;
    SQLiteDatabase db;
    Cursor cursor1, cursor2;
    String sregname, spassword;
    int i, j=0;
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,
                              Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_logout, container, false);
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
        userDBhelper = new UserDBhelper(getContext());
        db = userDBhelper.getWritableDatabase();

        yes = (Button)view.findViewById(R.id.yes);
        no = (Button)view.findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDBhelper.insert("-", "-", "-", "0");
                Intent intent = new Intent(getContext(), visitornavi.class);
                startActivity(intent);
            }
        });


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), user_navi.class);
                startActivity(intent);
            }
        });


    }
}
