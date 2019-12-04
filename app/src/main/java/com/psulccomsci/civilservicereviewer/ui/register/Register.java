package com.psulccomsci.civilservicereviewer.ui.register;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.psulccomsci.civilservicereviewer.R;
import com.psulccomsci.civilservicereviewer.ScoreDBHelper;
import com.psulccomsci.civilservicereviewer.UserDBhelper;
import com.psulccomsci.civilservicereviewer.dbhelper;
import com.psulccomsci.civilservicereviewer.fabsolo;
import com.psulccomsci.civilservicereviewer.login_load;
import com.psulccomsci.civilservicereviewer.user_navi;

import org.w3c.dom.Text;

import static androidx.core.content.ContextCompat.getSystemService;

public class Register extends Fragment {
    private static String name="No input";
    private static String phoneNum="No input";
    private static String password="No input";
    private RegisterViewModel mViewModel;
    private static EditText nametext;
    private static EditText phone;
    private static EditText passtext;
    private static String names, phones, passes;
    UserDBhelper userDBhelper;
    SQLiteDatabase db;
    Cursor cursor1, cursor2;
    String sregname, spassword;
    int i, j=0;

    public static Register newInstance() {
        return new Register();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.register_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        userDBhelper = new UserDBhelper(getContext());
        db = userDBhelper.getWritableDatabase();
        cursor1 = db.rawQuery(" SELECT "+ UserDBhelper.col_2+" FROM "+ UserDBhelper.table_name ,null);
        i = cursor1.getCount();
        cursor2 = db.rawQuery(" SELECT "+ UserDBhelper.col_4+" FROM "+ UserDBhelper.table_name ,null);


        Button register = (Button)view.findViewById(R.id.register);
        Button login = (Button)view.findViewById(R.id.login);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder Builder = new AlertDialog.Builder(getContext());
                View view = getLayoutInflater().inflate(R.layout.register_inflate, null);
                Builder.setView(view);
                AlertDialog dialog =Builder.create();
                dialog.show();
                final EditText name = view.findViewById(R.id.name);
                final EditText pn = view.findViewById(R.id.pn);
                final EditText pass = view.findViewById(R.id.pass);
                Button regis = view.findViewById(R.id.reg);

                regis.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!name.getText().toString().isEmpty()&&!pn.getText().toString().isEmpty()&&!pass.getText().toString().isEmpty()){
                            names = name.getText().toString();
                            phones = pn.getText().toString();
                            passes = pass.getText().toString();
                            Intent intent = new Intent(getContext(), login_load.class);
                            startActivity(intent);
                        }else{Toast.makeText(getContext(),"Please fill-up necessary information!!!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder Builder = new AlertDialog.Builder(getContext());
                View view = getLayoutInflater().inflate(R.layout.log_in_inflate, null);
                Builder.setView(view);
                AlertDialog dialog =Builder.create();
                dialog.show();
                final EditText name = view.findViewById(R.id.name);
                final EditText pass = view.findViewById(R.id.pass);
                Button log = view.findViewById(R.id.login2);
                final String information = getHardwareAndSoftwareInfo();
                log.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(!name.getText().toString().isEmpty()&&!pass.getText().toString().isEmpty()){
                            int id = checkUser(new User(name.getText().toString(), pass.getText().toString()));
                            if(id == -1)
                            {
                                Toast.makeText(getContext(),"User Does Not Exist",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Intent intent = new Intent(getContext(), user_navi.class);
                                intent.putExtra("currentUser", name.getText().toString());
                                startActivity(intent);
                                Toast.makeText(getContext(),"Welcome back "+name.getText().toString()+"!!!",Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(),"Please fill-up necessary information!!!",Toast.LENGTH_SHORT).show();
                        }

//                        while (j<i){
//                            cursor1.moveToFirst();
//                            cursor2.moveToFirst();
//                            sregname = cursor1.getString(0);
//                            spassword = cursor2.getString(0);
//
//                            if (!name.getText().toString().isEmpty()&&!pass.getText().toString().isEmpty()){
//                                if (name.getText().toString().equals(sregname)&&pass.getText().toString().equals(spassword)){
//                                    Intent intent = new Intent(getContext(), user_navi.class);
//                                    startActivity(intent);
//                                    userDBhelper.insert(sregname, "", spassword, information);
//                                    Toast.makeText(getContext(),"Welcome back "+sregname+"!!!",Toast.LENGTH_SHORT).show();
//                                } else if(name.getText().toString().equals("user")&&pass.getText().toString().equals("1234")){
//                                    Intent intent = new Intent(getContext(), user_navi.class);
//                                    startActivity(intent);
//                                    Toast.makeText(getContext(),"Successfully Log In as Test User 1 !!!",Toast.LENGTH_SHORT).show();
//                                } else{
//                                    Toast.makeText(getContext(),"User does not exist!!!",Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
//                                Toast.makeText(getContext(),"Please fill-up necessary information!!!",Toast.LENGTH_SHORT).show();
//                            }
//
//
//                            j=j+1;
//                        }
                    }
                });
            }
        });

    }

    public int checkUser(User user) {
        return userDBhelper.checkUser(user);
    }

    public static String nameinfo(){
        return names;
    }
    public static String phoneinfo(){
        return phones;
    }
    public static String passesinfo(){
        return passes;
    }

    private String getHardwareAndSoftwareInfo() {

        return getString(R.string.serial) + " " + Build.SERIAL + "\n" +
                getString(R.string.model) + " " + Build.MODEL + "\n" +
                getString(R.string.id) + " " + Build.ID + "\n" +
                getString(R.string.manufacturer) + " " + Build.MANUFACTURER + "\n" +
                getString(R.string.brand) + " " + Build.BRAND + "\n" +
                getString(R.string.type) + " " + Build.TYPE + "\n" +
                getString(R.string.user) + " " + Build.USER + "\n" +
                getString(R.string.base) + " " + Build.VERSION_CODES.BASE + "\n" +
                getString(R.string.incremental) + " " + Build.VERSION.INCREMENTAL + "\n" +
                getString(R.string.sdk) + " " + Build.VERSION.SDK + "\n" +
                getString(R.string.board) + " " + Build.BOARD + "\n" +
                getString(R.string.host) + " " + Build.HOST + "\n" +
                getString(R.string.fingerprint) + " " + Build.FINGERPRINT + "\n" +
                getString(R.string.versioncode) + " " + Build.VERSION.RELEASE;
    }


}
