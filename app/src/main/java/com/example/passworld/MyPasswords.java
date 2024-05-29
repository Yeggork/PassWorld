package com.example.passworld;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MyPasswords extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_passwors);
        TextView textpasswordaKotoriSave = null;
        textpasswordaKotoriSave.findViewById(R.id.TextViewPasswordSavedInDataBase);
        View view = null;
        ImageView copy = view.findViewById(R.id.imageViewCopyPassword);
        copy.setOnClickListener(new View.OnClickListener() {                      //чтобы скопировать пароль сохраненный в "ваши пароли" в буфер обмена телефона
            @Override
            public void onClick(View v) {
                String coppiedText = textpasswordaKotoriSave.getText().toString();
                ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Text",coppiedText);
                clipboard.setPrimaryClip(clip);
            }
        });                                                                        //чтобы скопировать пароль сохраненный в "ваши пароли" в буфер обмена телефона
        ImageView continueplay = view.findViewById(R.id.imageViewContinuePlay);
        continueplay.setOnClickListener(new View.OnClickListener() {               //чтобы перейти обратно к игре и чтобы пароль сразу вставился в окошко с ним и игра продолжалась
            private String localClassName;

            public void setLocalClassName(String localClassName) {
                this.localClassName = localClassName;
            }

            public String getLocalClassName() {
                return localClassName;
            }

            @Override
            public void onClick(View v) {
                String continuePlaying = textpasswordaKotoriSave.getText().toString();
                String OcurrentActivity = this.getLocalClassName();
                Intent Ointent = new Intent(MyPasswords.this, Checked.class);
                if (OcurrentActivity.equals("MyPasswords")) {                                          //переход к активности Checked
                    startActivities(new Intent[]{Ointent});
                    overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
                }
                //password.setText(continueplay.toString());                                             //вставка сохраненного пароля из "ваши мароли(MyPasswords) в окошко где вводишь пароль

            }
        });
    }
}
