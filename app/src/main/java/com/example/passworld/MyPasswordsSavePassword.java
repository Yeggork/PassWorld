package com.example.passworld;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passworld.database.DBManager;
import com.example.passworld.non.Passworddd;
import com.example.passworld.non.PasswordddAdapter;

public class MyPasswordsSavePassword extends AppCompatActivity {
    DBManager dbManager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_passwors);

        TextView textpasswordaKotoriSave = null;                                                             //скопировал из MyPasswordds
        textpasswordaKotoriSave.findViewById(R.id.TextViewPasswordSavedInDataBase);                                   //скопировал из MyPasswordds
        View view = null;                                                                                  //скопировал из MyPasswords
        ImageView copy = view.findViewById(R.id.imageViewCopyPassword);                                   //скопировал из MyPasswordds



        dbManager = new DBManager(this);
        dbManager.openDb();
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPasswords);
        PasswordddAdapter.OnDeleteClickListener onDeleteClickListener = new PasswordddAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(Passworddd passworddd, int position) {
                dbManager.deletePassword(passworddd);
            }
        };
        Toast.makeText(this,dbManager.getPasswords().size()+"",Toast.LENGTH_SHORT).show();
        PasswordddAdapter adapter = new PasswordddAdapter(this,dbManager.getPasswords(),onDeleteClickListener);
        recyclerView.setAdapter(adapter);



                        // Далее все что будет я скопировал из MyPasswordds



        copy.setOnClickListener(new View.OnClickListener() {                      //чтобы скопировать пароль сохраненный в "ваши пароли" в буфер обмена телефона
            @Override
            public void onClick(View view) {
                String coppiedtext = textpasswordaKotoriSave.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("текст скопирован:", coppiedtext);
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
                String continueplayy = textpasswordaKotoriSave.getText().toString();
                ClipboardManager clipboardd = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipp = ClipData.newPlainText("",continueplayy);
                clipboardd.setPrimaryClip(clipp);
                String AcurrentActivity = this.getLocalClassName();
                Intent Aintent = new Intent(MyPasswordsSavePassword.this, Checked.class);
                if (AcurrentActivity.equals("MyPasswordsSavePassword")) {//переход к активности Checked
                    Aintent.putExtra("ТекстДляВставки",continueplayy);
                    startActivities(new Intent[]{Aintent});
                    overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
                }
                //password.setText(continueplay.toString());                                             //вставка сохраненного пароля из "ваши мароли(MyPasswords) в окошко где вводишь пароль

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String ZcurrentActivity = this.getLocalClassName();
        Intent Uintent = new Intent(MyPasswordsSavePassword.this, MainActivity.class);
        if (ZcurrentActivity.equals("MyPasswordsSavePassword")) {
            startActivities(new Intent[]{Uintent});
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }
    }

}
