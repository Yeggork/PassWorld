package com.example.passworld;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Description extends MainActivity{
    public TextView DescriptionPlay;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String RcurrentActivity = this.getLocalClassName();
        Intent Vintent = new Intent(Description.this, MainActivity.class);
        if (RcurrentActivity.equals("Description")) {
            startActivities(new Intent[]{Vintent});
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);
        DescriptionPlay = findViewById(R.id.textViewDescriptionPlay);
        DescriptionPlay.setText("PassWorld - это развлекательная игра, в которой вам надо пройти 20 проверок для создания надежного и качественного пароля.\n");

    }
}
