package com.example.passworld;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.ContentInfo;
import android.view.OnReceiveContentListener;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    public EditText password;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        password = findViewById(R.id.editTextPassword);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            password.setOnReceiveContentListener(new OnReceiveContentListener() {
                @Nullable
                @Override
                public ContentInfo onReceiveContent(@NonNull EditText view, @NonNull ContentInfo payload) {
                    Snackbar.make(view, "dfd", Snackbar.LENGTH_LONG).show();
                    return null;
                }
            });
        }
    }
}