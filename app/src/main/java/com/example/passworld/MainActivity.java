package com.example.passworld;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.passworld.non.Passworddd;


public class MainActivity extends AppCompatActivity {
    public Button play;
    public Button MyPassword;
    public Button Description;
    public Button ButtonExitGame;
    private static final int REQUEST_BATTERY_PERMISSION = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        play = findViewById(R.id.buttonStartPlay);
        MyPassword = findViewById(R.id.buttonDBPasswords);
        Description = findViewById(R.id.buttonDescription);
        ButtonExitGame = findViewById(R.id.buttonExit);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Passworddd p = new Passworddd();
                Intent Eintent = new Intent(MainActivity.this, Checked.class);
                Eintent.putExtra(Passworddd.class.getSimpleName(),p);
                startActivities(new Intent[]{Eintent});
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });


        ButtonExitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Cintent = new Intent(MainActivity.this, Description.class);
                startActivities(new Intent[]{Cintent});
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
        MyPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Lintent = new Intent(MainActivity.this, MyPasswordsSavePassword.class);
                startActivities(new Intent[]{Lintent});
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
    }
}