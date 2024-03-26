package com.example.passworld;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Checked extends AppCompatActivity {
    public EditText password;
    public boolean flag1 = true;                   //флаги чтобы анимация проигралась один раз
    public boolean flag2 = true;                   //флаги чтобы анимация проигралась один раз
    public boolean flag3 = true;

    boolean isUpperCase(char ch) {
        return false;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String currentActivity = this.getLocalClassName();
        Intent Bintent = new Intent(Checked.this, MainActivity.class);


        if (currentActivity.equals("Checked")) {
            startActivities(new Intent[]{Bintent});
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }
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
                boolean Bbukva = false;                        //Bbukva - большая буква
                for (int i = 0; i < password.length(); i++) {                    //проверка на большую букву
                    if(Character.isUpperCase(passwordString.charAt(i))){
                        Bbukva = true;
                        break;
                    }
                    else{
                        Bbukva = false;
                        break;
                    }
                }



                if (password.length() > 7) {         //1 проверка
                    if (Bbukva == true & flag1 == true) {                         //анимация вывода нового задания и отключение чтобы задание еще раз выше не выехало
                        flag1 = false;
                    }
                    else if(Bbukva == true){                              //2 проврека + если задание было неправильным и стало правильным то красный сменится на зеленый

                    }
                }
                else {
                }
            }
        });
    }

}
