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
        dbManager = new DBManager(this);
        dbManager.openDb();
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPasswords);
        PasswordddAdapter.OnDeleteClickListener onDeleteClickListener = new PasswordddAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(Passworddd passworddd, int position) {
                dbManager.deletePassword(passworddd);
            }
        };
        PasswordddAdapter.OnCopyClickListener onCopyClickListener = new PasswordddAdapter.OnCopyClickListener() {
            @Override
            public void onCopyClick(Passworddd password, int position) {
                clipText(password);
            }
        };
        PasswordddAdapter.OnPlayClickListener onPlayClickListener = new PasswordddAdapter.OnPlayClickListener() {
            @Override
            public void onPlayClick(Passworddd password, int position) {
                String continueplayy = clipText(password);
                Intent Aintent = new Intent(MyPasswordsSavePassword.this, Checked.class);
                Aintent.putExtra("ТекстДляВставки", continueplayy);
                startActivity(Aintent);
                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
            }
        };
        Toast.makeText(this, dbManager.getPasswords().size() + "", Toast.LENGTH_SHORT).show();
        PasswordddAdapter adapter = new PasswordddAdapter(this, dbManager.getPasswords(), onDeleteClickListener, onCopyClickListener, onPlayClickListener);
        recyclerView.setAdapter(adapter);
    }

    public String clipText(Passworddd passworddd) {
        String coppiedtext = passworddd.getTextpassword();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("текст скопирован:", coppiedtext);
        clipboard.setPrimaryClip(clip);
        return coppiedtext;
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
