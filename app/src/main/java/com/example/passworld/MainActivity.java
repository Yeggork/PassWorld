package com.example.passworld;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    public EditText password;
    public boolean flag1 = true;
    public boolean flag2 = true;
    public boolean flag3 = true;
    public boolean flag4 = true;
    public boolean flag5 = true;
    public boolean flag6 = true;
    public boolean flag7 = true;
    public boolean flag8 = true;
    public boolean flag9 = true;
    public boolean flag10 = true;

    boolean isUpperCase(char ch) {
        return false;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        password = findViewById(R.id.editTextPassword);
        password.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String passwordString = password.getText().toString();
                    boolean Bbukva = false;
                    for (int i = 0; i < password.length(); i++) {
                        if(Character.isUpperCase(passwordString.charAt(i))){
                            Bbukva = true;
                            break;
                        }
                    }
                    if (password.length() > 7) {         //Следующая проверка
                        if (Bbukva == true) {

                        }
                        else (Bbukva == false | flag1 = true) {      //вывод нового задания
                            flag1 = false;
                            }
                    }
                    else if (flag1 == false | password.length()<8){          //оповещение что это задание не выполнено

                    }
                    else {
                        return;
                    }
                }
        });
    }
}