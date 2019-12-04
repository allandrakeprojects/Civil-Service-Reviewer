package com.psulccomsci.civilservicereviewer;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.util.Locale;


public class user_navi extends AppCompatActivity {
    private boolean clickFAB = false;
    private AppBarConfiguration mAppBarConfiguration;
    TextView textW;
    TextToSpeech warning2;
    String rnames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        warning2 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status!=TextToSpeech.ERROR){

                    warning2.setLanguage(Locale.CANADA);
                }
            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_navi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textW = (TextView)findViewById(R.id.regname);
        rnames=start.names();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_pdfreader, R.id.nav_stat,
                R.id.nav_user_abouts, R.id.nav_games, R.id.nav_reset,R.id.fabcon)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        /**/
        FloatingActionButton fab1;
        fab1 = findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                warning2.speak("Are you sure to take the exam? Just click on the screen.",TextToSpeech.QUEUE_FLUSH,null );
                Intent intent = new Intent(getApplicationContext(),frontforexam.class);
                startActivity(intent);
            }
        });

       //TextView textView = (TextView)findViewById(R.id.);


        FloatingActionButton fab2;
        fab2 = findViewById(R.id.fab);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                warning2.speak("Are you sure to take the exam? Just click on the screen.",TextToSpeech.QUEUE_FLUSH,null );
                Intent intent = new Intent(getApplicationContext(),frontforexamnp.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_navi, menu);
        clickFAB=fabsolo.fabstatus();
        if (clickFAB){Intent intent = new Intent (getApplicationContext(),frontforexamnp.class);
            startActivity(intent);}
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }
}
