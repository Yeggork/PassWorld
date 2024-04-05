package com.example.passworld;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    public Button play;
    public Button MyPassword;
    public Button Description;
    public Button ButtonExitGame;

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
                Intent Eintent = new Intent(MainActivity.this, Checked.class);
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
                Intent Lintent = new Intent(MainActivity.this, MyPasswords.class);
                startActivities(new Intent[]{Lintent});
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
    }
}