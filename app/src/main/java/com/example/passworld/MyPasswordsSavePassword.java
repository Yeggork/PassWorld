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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passworld.database.DBManager;
import com.example.passworld.non.Passworddd;
import com.example.passworld.non.PasswordddAdapter;

public class MyPasswordsSavePassword extends Fragment {
    DBManager dbManager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_password, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbManager = new DBManager(getContext());
        dbManager.openDb();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPasswords);
        PasswordddAdapter.OnDeleteClickListener onDeleteClickListener = new PasswordddAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(Passworddd passworddd, int position) {
                dbManager.deletePassword(passworddd);
            }
        };
        PasswordddAdapter adapter = new PasswordddAdapter(getContext(),dbManager.getPassword(),onDeleteClickListener);
        recyclerView.setAdapter(adapter);
    }
}
