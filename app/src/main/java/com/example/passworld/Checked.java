package com.example.passworld;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.util.regex.Pattern;

public class Checked extends MainActivity {
    public EditText password;
    public boolean flag1 = true;                   //флаги чтобы анимация проигралась один раз
    public boolean flag2 = true;                   //флаги чтобы анимация проигралась один раз
    public boolean flag3 = true;
    public boolean flag4 = true;
    public boolean flag5 = true;

    boolean isUpperCase(char ch) {
        return false;
    }
    boolean CharInPassword = false;
    boolean IntInPassword = false;
    boolean RimIntInPassword = false;
    boolean isDigit(char ch) {
        return false;
    }
    int SumNumberInPassword = 0;


    Pattern RimIntPattern = Pattern.compile("^[IVXLCDM]+$");


    @Override
    public void onBackPressed() {                  //эту ошибку исправлять не надо
        String ccurrentActivity = this.getLocalClassName();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Вы уверены, что хотите покинуть текущую игру?");
        builder.setCancelable(true);
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (ccurrentActivity.equals("Checked")) {
                    Intent Bintent = new Intent(Checked.this,MainActivity.class);
                    startActivities(new Intent[]{Bintent});
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                    finish();
                }
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
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
                for (int i = 0; i < password.length(); i++) {                    //проверка на большую букву
                    if(Character.isUpperCase(passwordString.charAt(i))){
                        Bbukva = true;
                        break;
                    }
                    else{
                        Bbukva = false;
                        break;
                    }
                } //проверка на наличие большой буквы
                for (int i = 0; i < password.length(); i++) {
                    if (!passwordString.isEmpty()) {
                        CharInPassword = true;
                    }                        //проверка на символы
                    else {
                        CharInPassword = false;
                    }
                } //проверка на символы
                for (int i = 0; i < password.length(); i++) {
                    if(Character.isDigit(passwordString.charAt(i))){
                        IntInPassword = true;
                        break;
                    }
                    else{
                        IntInPassword = false;
                        break;
                    }
                } //проверка на цифры
                for (int i = 0; i < password.length(); i++) {
                    if (RimIntPattern.matcher(passwordString).matches()) {
                        RimIntInPassword = true;
                        break;
                    } else {
                        RimIntInPassword = false;
                        break;
                    }
                } //проверка на римские цифры
                for (int i = 0; i < password.length(); i++) {
                    char DDDDDDDD = passwordString.charAt(i);
                    if (Character.isDigit(DDDDDDDD)){
                        int FFFFFF = Character.getNumericValue(DDDDDDDD);
                        SumNumberInPassword += FFFFFF;
                    }
                } //проверка на сумму чисел = 31


                if (password.length() > 7) {         //1 проверка
                    if (Bbukva == true & flag1 == true) {                         //анимация вывода нового задания и отключение чтобы задание еще раз выше не выехало
                        flag1 = false;
                    } else if (Bbukva == true) {                                        //2 проврека + если задание было неправильным и стало правильным то красный сменится на зеленый

                        if (IntInPassword == true & flag2 == true) {
                            flag2 = false;
                        } else if (IntInPassword == true) {

                            if (CharInPassword == true & flag3 == true) {
                                flag3 = false;
                            } else if (CharInPassword == true) {

                                if (RimIntInPassword == true & flag4 == true){
                                    flag4 = false;
                                } else if (RimIntInPassword == true) {

                                    if (SumNumberInPassword == 31 & flag5 == true){
                                        flag5 = false;
                                    } else if (SumNumberInPassword == 31){


                                    }
                                    else{

                                    }
                                }
                                else{

                                }
                            }
                            else {

                            }
                        }
                        else {

                        }
                    }
                    else {

                    }
                }
                else{

                }

            }
        });
    }

}

