package com.example.passworld;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    public EditText password;
    public boolean flag1 = true;                   //флаги чтобы анимация проигралась один раз
    public boolean flag2 = true;                   //флаги чтобы анимация проигралась один раз
    public boolean flag3 = true;

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
                    boolean Bbukva = false;                        //Bbukva - большая буква
                    for (int i = 0; i < password.length(); i++) {                    //проверка на большую букву
                        if(Character.isUpperCase(passwordString.charAt(i))){
                            Bbukva = true;
                            break;
                        }
                    }
                    if (password.length() > 7) {         //первая проверка
                        if (Bbukva == true) {                         //следующая проврека + если задание было неправильным и стало правильным то красный сменится на зеленый

                        }
                        else (Bbukva == false | flag1 = true) {      //анимация вывода нового задания и отключение чтобы задание еще раз выше не выехало
                            flag1 = false;
                            }
                    }
                    else if (flag1 == false | password.length()<8){          //оповещение что это задание не выполнено (фон фрагмента с этим заданием станет красным

                    }
                    else {                    // вдруг понадобится
                        return;
                    }
                }
        });
    }
}