package com.psulccomsci.civilservicereviewer;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.format.Formatter;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.psulccomsci.civilservicereviewer.ui.register.Register;

public class login_load extends AppCompatActivity {
    CountDownTimer countDownTimer;
    UserDBhelper userDBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload2);
        userDBhelper = new UserDBhelper(this);

        final String names = Register.nameinfo();
        final String phone = Register.phoneinfo();
        final String pass = Register.passesinfo();
        final String information = getHardwareAndSoftwareInfo();
        countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {

                userDBhelper.insert(names, phone, pass, information);
                Intent intent = new Intent(getApplicationContext(),user_navi.class);
                startActivity(intent);
            }

        };
        countDownTimer.start();

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
